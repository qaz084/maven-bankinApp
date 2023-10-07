package servicios;

import entidades.Cuenta;

import java.util.Scanner;

public class ServicioCuenta {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ServicioBanco sB = new ServicioBanco();
    Cuenta cuenta1;

    public void crearCuenta() {
        cuenta1 = new Cuenta();
        String usuario = "";
        String password = "";

        System.out.println("---Creacion de cuentas---");

        String[] caracteresNoValidos = {"", " "};
        int longitudMinima = 3;

        try {
            System.out.println("Ingrese nuevo usuario: ");
            usuario = leer.next();
            System.out.println("Ingrese nuevo password: ");
            password = leer.next();
            if (usuario.contains(String.valueOf(caracteresNoValidos)) || password.contains(String.valueOf(caracteresNoValidos))) {
                System.out.println("Caracteres no válidos,cuenta no creada");
            } else if (usuario.length() >= longitudMinima && password.length() >= longitudMinima) {
                cuenta1.setUsuario(usuario);
                cuenta1.setPassword(password);
                try {
                    System.out.println(cuenta1.toString());
                    sB.almacenarCuenta(cuenta1);
                    System.out.println("VER CUENTAS:");
                    sB.verCuentas();
                    System.out.println("Cuenta creada con éxito");
                } catch (Exception e) {
                    System.out.println("Error al crear la cuenta, intente nuevamente");
                }
            }

        } catch (Exception e) {
            System.out.println("Ingrese valores correctos");
        }


    }


}
