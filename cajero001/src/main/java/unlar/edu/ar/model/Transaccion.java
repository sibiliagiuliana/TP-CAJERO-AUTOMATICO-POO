package unlar.edu.ar.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import unlar.edu.ar.util.Formato;

public class Transaccion {

    private TipoTransaccion tipo;
    private double monto;
    private LocalDateTime fechaHora;
    private String descripcion;
    private double saldoFinal;

    private static final DateTimeFormatter formatter 
    = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaccion(TipoTransaccion tipo, double monto, String descripcion, double saldoFinal) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
        this.saldoFinal = saldoFinal;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }
    public double getMonto() {
        return monto;
    }
    public double getSaldoFinal() {
        return saldoFinal;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public String getDescripcion() {
        return descripcion;
    }

   @Override
public String toString() {

return "[" + fechaHora.format(formatter) + "] " +
        tipo + ": " +
        Formato.formatearMoneda(monto) +
        " | Saldo: " +
        Formato.formatearMoneda(saldoFinal) +
        " | " + descripcion;
    

}
}
