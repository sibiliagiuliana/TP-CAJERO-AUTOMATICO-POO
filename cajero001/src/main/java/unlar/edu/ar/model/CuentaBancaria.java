package unlar.edu.ar.model;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {

    private final String numeroCuenta;
    private double saldo;
    private String titular;
    private String pin;
    private boolean activa;
    private List<Transaccion> transacciones;

    public CuentaBancaria(String numeroCuenta, String titular, String pin, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.activa = true;
        this.transacciones = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getTitular() {
        return titular;
    }
    public String getPin() {
        return pin;
    }
    public boolean isActiva() {
        return activa;
    }
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void depositar(double monto) {
        if (monto > 0 && activa) {
            saldo += monto;
        }
    }

    public void extraer(double monto) {
        if (monto > 0 && monto <= saldo && activa) {
            saldo -= monto;
        }
    }

    public void agregarTransaccion(Transaccion t) {
        transacciones.add(t);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", titular='" + titular + '\'' +
                ", activa=" + activa +
                '}';
    }
}
