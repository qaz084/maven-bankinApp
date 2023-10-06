package servicios;

import entidades.Cuenta;


import java.io.IOException;
import java.util.Scanner;

public class ServicioMenu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    //SERVICIOS
    ServicioBanco sB;
    ServicioCuenta sC;

    //VARIBLES GLOBALES A LA CLASE
    boolean estadoValidacion = false;
    Cuenta cuenta1 = new Cuenta();


    public void login() {
        sB = new ServicioBanco();
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
            }
        } else {
            menuNoAdmin();
        }
    }

    public void menuAdmin() {
        sC = new ServicioCuenta();
        int opcion = 0;
        System.out.println("Hola admin");
        do {
            try{
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
            }catch(Exception e){
                System.out.println("Valor incorrecto, por favor, ingresa una opcion valida: ");
                leer.next();
                opcion= -1;
            }
        } while (opcion != 3);
    }

    public void menuNoAdmin() {
        System.out.println("no soy admin :( ");
        sC = new ServicioCuenta();
        int opcion = 0;
        do {
            try{
                System.out.println("1) Retirar dinero:");
                System.out.println("2) Depositar:");
                System.out.println("3) Transferir:");
                System.out.println("4) Salir:");
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
            }catch(Exception e){
                System.out.println("Valor incorrecto, por favor, ingresa una opcion valida: ");
                leer.next();
                opcion= -1;
            }
        } while (opcion != 4);
    }

}
