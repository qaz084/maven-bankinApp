package servicios;

import entidades.Cuenta;

import java.util.Scanner;

public class ServicioCuenta {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ServicioBanco sB = new ServicioBanco();

    public void crearCuenta() {
        Cuenta cuenta1 = new Cuenta();
        System.out.println("---Creacion de cuentas---");
        System.out.println("");
        System.out.println("Ingrese nuevo usuario: ");
        cuenta1.setUsuario(leer.next());
        System.out.println("Ingrese nuevo password: ");
        cuenta1.setPassword(leer.next());
        sB.almacenarCuenta(cuenta1);
    }

}
