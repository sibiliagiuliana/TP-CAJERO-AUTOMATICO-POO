package unlar.edu.ar.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Formato {

    private static final NumberFormat formatter 
    = NumberFormat.getCurrencyInstance(Locale.of("es", "AR"));

    public static String formatearMoneda(double monto) {
        return formatter.format(monto);
    }

}
