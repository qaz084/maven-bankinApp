package servicios;

import entidades.Cuenta;
import java.util.Scanner;

public class ServicioMenu {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    //SERVICIOS
    ServicioBanco sB = ServicioBanco.getInstancia() ;
    ServicioCuenta sC;

    //VARIBLES GLOBALES A LA CLASE
    boolean estadoValidacion = false;
    Cuenta cuenta1 = new Cuenta();

    public void login() {
        System.out.println("----LOGIN----");
        do {
            menuLogin();
        }
        while (!estadoValidacion);
    }
    public void menuLogin() {
        System.out.println("Ingrese su usuario:");
        cuenta1.setUsuario(leer.next());
        System.out.println("Ingrese su password: ");
        cuenta1.setPassword(leer.next());
        estadoValidacion = sB.validarCredenciales(cuenta1.getUsuario(), cuenta1.getPassword());
        if (estadoValidacion) {
            if (sB.chequearAdmin(cuenta1.getUsuario())) {
                menuAdmin();
            } else {
                menuNoAdmin();
            }
        }
        System.out.println("Login FALLIDO");
    }

    public void menuAdmin() {
        sC = new ServicioCuenta();
        int opcion = 0;
        System.out.println("---MENU ADMIN---");
        System.out.println("");
        do {
            try {
                System.out.println("1) Crear cuenta:");
                System.out.println("2) Ver cuentas:");
                System.out.println("3) Salir:");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        sC.crearCuenta();
                        break;
                    case 2:
                        sB.verCuentas();
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Valor incorrecto, por favor, ingresa una opcion valida: ");
                leer.next();
                opcion = -1;
            }
        } while (opcion != 3);
    }

    public void menuNoAdmin() {

        sC = new ServicioCuenta();
        Integer opcion = 0;
        do {
            try {
                System.out.println("1) Retirar dinero:");
                System.out.println("2) Depositar:");
                System.out.println("3) Transferir:");
                System.out.println("4) Ver saldo:");
                System.out.println("5) Salir:");

                opcion = Integer.valueOf(leer.next());

                switch (opcion) {
                    case 1:
                        System.out.println("---INGRESO A RETIRAR---");
                        System.out.println("Ingrese cuánto dinero quiere retirar: ");
                        double dineroAretirar = (leer.nextDouble());
                        sB.retirarDinero(cuenta1,dineroAretirar);
                        break;
                    case 2:
                        System.out.println("---INGRESO A DEPOSITAR---");
                        System.out.println("Ingrese cuánto dinero quiere depositar: ");
                        double montoADepositar = (leer.nextDouble());
                        sB.depositar(cuenta1,montoADepositar);
                        break;
                    case 3:
                        System.out.println("---INGRESO A TRANSFERIR---");
                        System.out.println("Ingrese cuánto dinero quiere transferir: ");
                        double montoATransferir = (leer.nextDouble());
                        sB.transferir(cuenta1,montoATransferir);
                        break;
                    case 4:
                        sB.verSaldo(cuenta1);
                        break;
                    case 5:
                        System.out.println("Saliendo, no olvides retirar tu tarjeta");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Valor incorrecto, por favor, ingresa una opcion valida: ");
                leer.next();
                opcion = -1;
            }
        } while (opcion != 5);
    }

}
