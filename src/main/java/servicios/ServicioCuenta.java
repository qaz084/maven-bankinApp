package servicios;

import entidades.Cuenta;

import java.util.Scanner;

public class ServicioCuenta {

    private Scanner leer;
    private ServicioBanco sB;
    private Cuenta cuenta1;

    public ServicioCuenta() {
        sB = ServicioBanco.getInstancia();
        leer = new Scanner(System.in).useDelimiter("\n");
    }

    public void crearCuenta() {

        System.out.println("---Creacion de cuentas---");

        try {
            String usuario = obtenerInput("Ingrese nuevo usuario: ");
            String password = obtenerInput("Ingrese nuevo password: ");

            if (esInputValido(usuario) && esInputValido(password)) {
                if (sB.existeUsuario(usuario)) {
                    System.out.println("El usuario ya existe, cuenta no creada");
                } else {
                    cuenta1 = new Cuenta();
                    cuenta1.setUsuario(usuario);
                    cuenta1.setPassword(password);
                    sB.almacenarCuenta(cuenta1);
                    System.out.println("Cuenta creada con éxito");
                }
            } else {
                System.out.println("Caracteres no válidos,cuenta no creada");

            }
        } catch (Exception e) {
            System.out.println("Ingrese valores correctos");
        }
    }

    private String obtenerInput(String mensaje) {
        System.out.println(mensaje);
        return leer.next().trim();
    }

    private boolean esInputValido(String input) {
        return input.length() >= 3 && !input.trim().isEmpty();
    }

}
