package unlar.edu.ar.ui;

import unlar.edu.ar.model.CuentaBancaria;
import unlar.edu.ar.service.CajeroService;
import unlar.edu.ar.util.Formato;

import java.util.Scanner;

public class Consola {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        CajeroService cajero = new CajeroService();


        boolean salir = false;

// CREAMOS CUENTAS DE PRUEBA (AL MENOS 3)

        CuentaBancaria c1 = new CuentaBancaria("1001", "Juan Pérez", "1234", 50000);
        CuentaBancaria c2 = new CuentaBancaria("1002", "María García", "5678", 30000);
        CuentaBancaria c3 = new CuentaBancaria("1003", "Carlos López", "9012", 15000);

        cajero.agregarCuenta(c1);
        cajero.agregarCuenta(c2);
        cajero.agregarCuenta(c3);

// SIMULAMOS EL MENU DE OPERACIONES (al menos 15 operaciones de prueba)
        System.out.println("Bienvenido al Cajero Automático");

try{
// CUENTA N1

System.out.println("\n========== OPERACIONES DE LA CUENTA 1001 ==========");
System.out.println();
System.out.println("Saldo inicial cuenta 1001: " + Formato.formatearMoneda(cajero.consultarSaldo("1001", "1234")));

        System.out.println("\n--- DEPÓSITO CUENTA 1001 ---");
        cajero.depositar("1001", "1234", 1000);

        System.out.println("\n--- EXTRACCIÓN CUENTA 1001 ---");
        cajero.extraer("1001", "1234", 500);

        System.out.println("\n--- TRANSFERENCIA CUENTA 1001 --> CUENTA 1002 ---");
        cajero.transferir("1001", "1002", "1234", 200);

// CUENTA N2
System.out.println("\n========== OPERACIONES DE LA CUENTA 1002 ==========");
System.out.println();
System.out.println("Saldo inicial cuenta 1002: " + Formato.formatearMoneda(cajero.consultarSaldo("1002", "5678")));

        System.out.println("\n--- DEPÓSITO CUENTA 1002 ---");
        cajero.depositar("1002", "5678", 1000);

        System.out.println("\n--- EXTRACCIÓN CUENTA 1002 ---");
        cajero.extraer("1002", "5678", 500);

        System.out.println("\n--- TRANSFERENCIA CUENTA 1002 --> CUENTA 1003 ---");
        cajero.transferir("1002", "1003", "5678", 200);

// CUENTA N3
System.out.println("\n========== OPERACIONES DE LA CUENTA 1003 ==========");
System.out.println();
System.out.println("Saldo inicial cuenta 1003: " + Formato.formatearMoneda(cajero.consultarSaldo("1003", "9012")));

        System.out.println("\n--- DEPÓSITO CUENTA 1003 ---");
        cajero.depositar("1003", "9012", 1000);

        System.out.println("\n--- EXTRACCIÓN CUENTA 1003 ---");
        cajero.extraer("1003", "9012", 500);

        System.out.println("\n--- TRANSFERENCIA CUENTA 1003 --> CUENTA 1001 ---");
        cajero.transferir("1003", "1001", "9012", 200);

// OPERACIONES MIXTAS
System.out.println("\n========== SIMULACION DE OPERACIONES MIXTAS ==========");

        System.out.println("\n--- DEPÓSITO CUENTA 1001 ---");
        cajero.depositar("1001", "1234", 500);

        System.out.println("\n--- EXTRACCIÓN CUENTA 1002 ---");
        cajero.extraer("1002", "5678", 300);

        System.out.println("\n--- TRANSFERENCIA CUENTA 1003 --> CUENTA 1002 ---");
        cajero.transferir("1003", "1002", "9012", 400);

        System.out.println("\n--- DEPÓSITO CUENTA 1002 ---");
        cajero.depositar("1002", "5678", 700);

        System.out.println("\n--- EXTRACCIÓN CUENTA 1001 ---");
        cajero.extraer("1001", "1234", 200);

        System.out.println("\n--- TRANSFERENCIA CUENTA 1001 --> CUENTA 1003 ---");
        cajero.transferir("1001", "1003", "1234", 300);

         } catch (Exception e) {
                System.out.println("Error en la simulación: " + e.getMessage());
            }

// PRUEBA DE EXCEPCIONES (al menos 2 casos)

        try {

            System.out.println("\n========== PRUEBA DE EXCEPCIONES ==========");
            cajero.extraer("1001", "1234", 999999); // saldo insuficiente
                } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
                }

        try {
             cajero.extraer("1001", "9999", 1000); // PIN incorrecto
                } catch (Exception e) {
             System.out.println("Excepción: " + e.getMessage());
                }

// MOSTRAMOS EL HISTORIAL DE TRANSACCIONES DE UNA CUENTA
        try{
            System.out.println("\n========== HISTORIAL CUENTA 1001 ==========");
System.out.println();
                cajero.mostrarUltimasTransacciones("1001", "1234");
        } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());

        }


// MENU INTERACTIVO

        while (!salir){
            mostrarMenu();

            try {

                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                case 1 -> {
                    try {
                        System.out.print("Ingrese el numero de cuenta: ");
                        String cuenta = scanner.nextLine();
                        System.out.print("Ingrese el PIN: ");
                        String pin = scanner.nextLine();

                        double saldo = cajero.consultarSaldo(cuenta, pin);
                        System.out.println("Saldo actual: " + Formato.formatearMoneda(saldo));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Ingrese el numero de cuenta: ");
                        String cuenta = scanner.nextLine();
                        System.out.print("Ingrese el PIN: ");
                        String pin = scanner.nextLine();
                        System.out.print("Ingrese el monto a depositar: ");
                        double monto = scanner.nextDouble();
                        scanner.nextLine(); 

                        cajero.depositar(cuenta, pin, monto);
                        System.out.println("Depósito exitoso. Nuevo saldo: " + Formato.formatearMoneda(cajero.consultarSaldo(cuenta, pin)));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        scanner.nextLine();
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("Ingrese el numero de cuenta: ");
                        String cuenta = scanner.nextLine();
                        System.out.print("Ingrese el PIN: ");
                        String pin = scanner.nextLine();
                        System.out.print("Ingrese el monto a extraer: ");
                        double monto = scanner.nextDouble();
                        scanner.nextLine(); 

                        cajero.extraer(cuenta, pin, monto);
                        System.out.println("Extracción exitosa. Nuevo saldo: " + Formato.formatearMoneda(cajero.consultarSaldo(cuenta, pin)));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        scanner.nextLine();
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Ingrese el numero de cuenta origen: ");
                        String origen = scanner.nextLine();
                        System.out.print("Ingrese el numero de cuenta destino: ");
                        String destino = scanner.nextLine();
                        System.out.print("Ingrese el PIN: ");
                        String pin = scanner.nextLine();
                        System.out.print("Ingrese el monto a transferir: ");
                        double monto = scanner.nextDouble();
                        scanner.nextLine(); 

                        cajero.transferir(origen, destino, pin, monto);
                        System.out.println("Transferencia exitosa. Nuevo saldo cuenta origen: " + Formato.formatearMoneda(cajero.consultarSaldo(origen, pin)));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        scanner.nextLine();
                    }
                }
                case 5 -> {
                    try {
                        System.out.print("Ingrese el numero de cuenta: ");
                        String cuenta = scanner.nextLine();
                        System.out.print("Ingrese el PIN: ");
                        String pin = scanner.nextLine();

                        System.out.println("Últimas transacciones:");
                        cajero.mostrarUltimasTransacciones(cuenta, pin);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 0 -> {

                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    
                }
                default -> System.out.println("Opción inválida.");
            } 
            }
            
            catch (Exception e) {
                System.out.println("Entrada invalida. Debe ingresar un numero: ");
                scanner.nextLine(); // Limpiar el buffer
            }      

}

scanner.close();
    }
        private static void mostrarMenu() {
        System.out.println("\n========== CAJERO AUTOMATICO ==========");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar");
        System.out.println("3. Extraer");
        System.out.println("4. Transferir");
        System.out.println("5. Ver historial");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        }
    }

