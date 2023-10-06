package servicios;

import entidades.Cuenta;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServicioBanco {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    Map<String, Cuenta> conjuntoDeCuentas = new HashMap<String, Cuenta>();

    Cuenta cuenta01 = new Cuenta("mechi", "123", 0.0);
    Cuenta cuenta02 = new Cuenta("Quiryat", "321", 0.0);
    Cuenta cuenta03 = new Cuenta("Ana", "567", 0.0);
    Cuenta cuenta04 = new Cuenta("adminJon", "000", 0.0);

    //CUENTAS HARDCODEADAS
    {
        conjuntoDeCuentas.put(cuenta01.getUsuario(), cuenta01);
        conjuntoDeCuentas.put(cuenta02.getUsuario(), cuenta02);
        conjuntoDeCuentas.put(cuenta03.getUsuario(), cuenta03);
        conjuntoDeCuentas.put(cuenta04.getUsuario(), cuenta04);
    }

    public void almacenarCuenta(Cuenta cuenta1) {
        conjuntoDeCuentas.put(cuenta1.getUsuario(), cuenta1);
    }

    public boolean validarCredenciales(String user, String password) {
        if (conjuntoDeCuentas.containsKey(user)) {
            Cuenta cuenta = conjuntoDeCuentas.get(user);
            chequearAdmin(user);
            System.out.println("---Login exitoso---");
            return cuenta.getPassword().equals(password);
        }
        System.out.println("---Fallo el login---");
        return false;
    }

    public boolean chequearAdmin(String user) {
        if (user.contains("admin")) {
            return true;
        }
        return false;
    }

    public void verCuentas() {
        System.out.println(conjuntoDeCuentas.toString());
    }
}
