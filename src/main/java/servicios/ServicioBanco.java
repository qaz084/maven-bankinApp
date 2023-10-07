package servicios;

import entidades.Cuenta;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServicioBanco {

    /*PATRON SINGLETON PARA GENERAR UNA INSTANCIA UNICA DEL SERVICIO
     Y MANTENER UN SOLO HASHMAP A LO LARGO DE
     TODOS LOS SERVICIOS */
    private static final ServicioBanco instanciaSB=new ServicioBanco();
    private Map<String, Cuenta> conjuntoDeCuentas = new HashMap<String, Cuenta>();

    public ServicioBanco() {
        Cuenta cuenta01 = new Cuenta("mechi", "123", 0.0);
        Cuenta cuenta02 = new Cuenta("Quiryat", "321", 0.0);
        Cuenta cuenta03 = new Cuenta("Ana", "567", 0.0);
        Cuenta cuenta04 = new Cuenta("adminJon", "000", 0.0);
        conjuntoDeCuentas.put(cuenta01.getUsuario(), cuenta01);
        conjuntoDeCuentas.put(cuenta02.getUsuario(), cuenta02);
        conjuntoDeCuentas.put(cuenta03.getUsuario(), cuenta03);
        //CUENTA ADMIN
        conjuntoDeCuentas.put(cuenta04.getUsuario(), cuenta04);
    }
    public static ServicioBanco getInstancia(){
        return instanciaSB;
    }

    Scanner leer = new Scanner(System.in).useDelimiter("\n");


    //METODOS PRINCIPALES
    public void retirarDinero(Cuenta cuenta1) {
        System.out.println("---INGRESO A RETIRAR---");
        double montoARetirar = 0.0;
        double balance = cuenta1.getBalance();

        try {
            System.out.println("Ingrese cuánto dinero quiere retirar: ");
            montoARetirar = (leer.nextDouble());

            if (montoARetirar <= balance) {
                cuenta1.setBalance(balance - montoARetirar);
                System.out.println("Saldo disponible: $" + cuenta1.getBalance());
            } else {
                System.out.println("Saldo insuficiente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Valor incorrecto, ingrese un número: ");
            leer.next();
        }
    }

    public void verCuentas() {
        for (String nombreUsuario : conjuntoDeCuentas.keySet()) {
            Cuenta usuario = conjuntoDeCuentas.get(nombreUsuario);
            System.out.println("usuario='" + usuario.getUsuario() + "', password='" + usuario.getPassword() +
                    "', balance=" + usuario.getBalance() + "\n");
        }
    }

    public void verSaldo(Cuenta cuenta) {
        System.out.println("Tu saldo actual es de: " + cuenta.getBalance());
    }

    public void depositar(Cuenta cuenta1) {
        System.out.println("---INGRESO A DEPOSITAR---");
        double montoADepositar = 0.0;
        double balance = cuenta1.getBalance();

        try {
            System.out.println("Ingrese cuánto dinero quiere depositar: ");
            montoADepositar = (leer.nextDouble());

            if (montoADepositar != 0) {
                cuenta1.setBalance(balance + montoADepositar);
                System.out.println("Saldo disponible: $" + cuenta1.getBalance());
            } else {
                System.out.println("Ingrese un monton mayor a CERO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Valor incorrecto, ingrese un monto correcto: ");
            leer.next();
        }
    }

    public void transferir(Cuenta cuentaOrigen) {
        System.out.println("---INGRESO A TRANSFERIR---");
        double montoATransferir = 0.0;
        double balance = cuentaOrigen.getBalance();
        String cuentaATransferir = "";

        try {
            System.out.println("Ingrese cuánto dinero quiere transferir: ");
            montoATransferir = (leer.nextDouble());

            if (montoATransferir != 0 && montoATransferir <= balance) {
                System.out.println("Ingrese la cuenta a la cual quiere transferir: ");
                cuentaATransferir = leer.next();
                Cuenta cuentaDestino = validarCuenta(cuentaATransferir);

                if (cuentaDestino != null) {
                    double balanceDestino = cuentaDestino.getBalance();
                    cuentaDestino.setBalance(balanceDestino + montoATransferir);
                    System.out.println("Transferencia realizada con éxito");
                    restarDinero(cuentaOrigen, montoATransferir);
                } else {
                    System.out.println("---Cuenta no encontrada---");
                }

            } else if (montoATransferir == 0) {
                System.out.println("Ingrese un monton mayor a CERO");
                System.out.println("");
            } else {
                System.out.println("Saldo insuficiente");
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cuenta incorrecta, ingrese una nueva cuenta: ");
            leer.next();
        }
    }

    //METODOS AUXILIARES
    private Cuenta validarCuenta(String user) {
        if (conjuntoDeCuentas.containsKey(user)) {
            Cuenta cuenta = conjuntoDeCuentas.get(user);
            System.out.println("---Cuenta encontrada---");
            return cuenta;
        }
        return null;
    }

    private void restarDinero(Cuenta cuenta, double dineroARestar) {
        double dineroActual = cuenta.getBalance();
        double dineroFinal = dineroActual - dineroARestar;
        cuenta.setBalance(dineroFinal);
        System.out.println("Saldo actual: " + dineroFinal);
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
        return user.contains("admin");
    }

    public void almacenarCuenta(Cuenta cuenta1) {
        System.out.println("ALMACEN");
        conjuntoDeCuentas.put(cuenta1.getUsuario(), cuenta1);
    }

}
