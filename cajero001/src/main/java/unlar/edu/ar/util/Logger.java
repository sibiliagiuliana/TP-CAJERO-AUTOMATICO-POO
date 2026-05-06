package unlar.edu.ar.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logger {

    private static final DateTimeFormatter formatter 
    = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String generarLog(String tipo, double monto, double saldoFinal) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(formatter.format(LocalDateTime.now()));
        sb.append("] ");
        sb.append(tipo);
        sb.append(": ");
        sb.append(Formato.formatearMoneda(monto));
        sb.append(" | Saldo: ");
        sb.append(Formato.formatearMoneda(saldoFinal));

        return sb.toString();
    }

}
