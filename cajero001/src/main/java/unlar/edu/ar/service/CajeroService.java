package unlar.edu.ar.service;

import unlar.edu.ar.exception.CuentaInactivaException;
import unlar.edu.ar.exception.LimiteExtraccionExcedidoException;
import unlar.edu.ar.exception.PinInvalidoException;
import unlar.edu.ar.exception.SaldoInsuficienteException;
import unlar.edu.ar.model.*;
import unlar.edu.ar.util.Logger;
import java.util.HashMap;

public class CajeroService {

    private HashMap<String, CuentaBancaria> cuentas;
    private static final double LIMITE_EXTRACCION = 10000.0;
    
    public CajeroService() {
        cuentas = new HashMap<>();
    }

    // METODOS BASICOS
    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    // METODOS PRIVADOS DE VALIDACION

    private void validarPin(CuentaBancaria cuenta, String pinIngresado) 
    throws PinInvalidoException {
    if (!cuenta.getPin().equals(pinIngresado)) {
        throw new PinInvalidoException("PIN incorrecto.");
    }
}

    private CuentaBancaria obtenerCuenta(String numeroCuenta)  {
        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new RuntimeException("Cuenta no encontrada.");
        }
        return cuenta;
    }

    private void validarCuentaActivaYPin(CuentaBancaria cuenta, String pin) 
        throws CuentaInactivaException, PinInvalidoException {

    if (!cuenta.isActiva()) {
        throw new CuentaInactivaException("La cuenta está inactiva.");
    }

    validarPin(cuenta, pin);
}

    private void validarMonto(double monto) {
    if (monto <= 0) {
        throw new IllegalArgumentException("Monto inválido.");
    }
}

// OPERACIONES PRINCIPALES

public void depositar(String numeroCuenta, String pin, double monto) 
   throws CuentaInactivaException, PinInvalidoException {
    
    CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
    validarCuentaActivaYPin(cuenta, pin);
    validarMonto(monto);

    cuenta.depositar(monto);

    Transaccion t = new Transaccion(
            TipoTransaccion.DEPOSITO,
            monto,
            "Depósito realizado",
            cuenta.getSaldo()
    );

    cuenta.agregarTransaccion(t);

    System.out.println(Logger.generarLog("DEPÓSITO", monto, cuenta.getSaldo()));
}


public void extraer(String numeroCuenta, String pin, double monto)
        throws CuentaInactivaException, SaldoInsuficienteException, LimiteExtraccionExcedidoException, PinInvalidoException {

    CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
    validarCuentaActivaYPin(cuenta, pin);
    validarMonto(monto);

    //límite de extracción (ej: 10000)
    if (monto > LIMITE_EXTRACCION) {
        throw new LimiteExtraccionExcedidoException("Supera el límite permitido.");
    }

    //saldo insuficiente
    if (cuenta.getSaldo() < monto) {
        throw new SaldoInsuficienteException("Saldo insuficiente.");
    }

    cuenta.extraer(monto);

    Transaccion t = new Transaccion(
            TipoTransaccion.EXTRACCION,
            monto,
            "Extracción realizada",
            cuenta.getSaldo()

    );

    cuenta.agregarTransaccion(t);

    System.out.println(Logger.generarLog("EXTRACCIÓN", monto, cuenta.getSaldo()));
}

public void transferir(String origen, String destino, String pin, double monto)
        throws CuentaInactivaException, SaldoInsuficienteException, LimiteExtraccionExcedidoException, PinInvalidoException {


    CuentaBancaria cuentaOrigen = obtenerCuenta(origen);
    CuentaBancaria cuentaDestino = obtenerCuenta(destino);

    if (origen.equals(destino)) {
        throw new IllegalArgumentException("No se puede transferir a la misma cuenta.");
    }

    validarCuentaActivaYPin(cuentaOrigen, pin);

    if (!cuentaDestino.isActiva()) {
        throw new CuentaInactivaException("La cuenta destino está inactiva.");
    }
    validarMonto(monto);

    if (monto > LIMITE_EXTRACCION) {
        throw new LimiteExtraccionExcedidoException("Supera el límite permitido.");
    }

    if (cuentaOrigen.getSaldo() < monto) {
        throw new SaldoInsuficienteException("Saldo insuficiente.");
    }

    // resta en origen
    cuentaOrigen.extraer(monto);

    System.out.println(Logger.generarLog("TRANSFERENCIA ENVIO", monto, cuentaOrigen.getSaldo()));

    // suma en destino
    cuentaDestino.depositar(monto);

    // registrar transacción en origen
    Transaccion tOrigen = new Transaccion(
            TipoTransaccion.TRANSFERENCIA,
            monto,
            "Transferencia enviada a cuenta " + destino,
            cuentaOrigen.getSaldo()

    );

    cuentaOrigen.agregarTransaccion(tOrigen);

    // registrar transacción en destino
    Transaccion tDestino = new Transaccion(
            TipoTransaccion.TRANSFERENCIA,
            monto,
            "Transferencia recibida de cuenta " + origen,
            cuentaDestino.getSaldo()
    );

    cuentaDestino.agregarTransaccion(tDestino);

    System.out.println(Logger.generarLog("TRANSFERENCIA RECEPCION", monto, cuentaDestino.getSaldo()));
}

// OPERACIONES SECUNDARIAS

public double consultarSaldo(String numeroCuenta, String pin) 
        throws CuentaInactivaException, PinInvalidoException {

    CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
    validarCuentaActivaYPin(cuenta, pin);

    return cuenta.getSaldo();
}

public void mostrarUltimasTransacciones(String numeroCuenta, String pin) 
        throws CuentaInactivaException, PinInvalidoException {

    CuentaBancaria cuenta = obtenerCuenta(numeroCuenta);
    validarCuentaActivaYPin(cuenta, pin);

    System.out.println("Últimas transacciones de la cuenta " + numeroCuenta + ":");

    if (cuenta.getTransacciones().isEmpty()) {
    System.out.println("No hay transacciones registradas.");
    return;
}

    int size = cuenta.getTransacciones().size();
    int inicio = Math.max(0, size - 10);

    for (int i = inicio; i < size; i++) {
        System.out.println(cuenta.getTransacciones().get(i));
    }
}


        }
