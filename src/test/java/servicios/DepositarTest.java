package servicios;

import entidades.Cuenta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DepositarTest {

    private Cuenta cuenta01;
    ServicioBanco sB;
    private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @Before
    public void setUp() throws Exception {
        cuenta01 = new Cuenta("mechi", "123", 1000);
        sB = ServicioBanco.getInstancia();
        System.setOut(new PrintStream(outputCaptor));
    }

    @After
    public void tearDown() throws Exception {
        // Restaurar la salida estándar original después de cada prueba
        System.setOut(originalSystemOut);
    }

    @Test
    public void testDepositarDinero() {
        double saldoOriginal = cuenta01.getBalance();
        double dineroADepositar = 1000;
        sB.depositar(cuenta01, dineroADepositar);
        double dineroLuegoDeDeposito = cuenta01.getBalance();

        assertEquals(dineroADepositar, dineroLuegoDeDeposito - saldoOriginal, 0);
    }

    @Test
    public void testDepositarCero() {
        double saldoOriginal = cuenta01.getBalance();
        double dineroADepositar = 0;
        sB.depositar(cuenta01, dineroADepositar);
        double dineroLuegoDeDeposito = cuenta01.getBalance();

        assertEquals(0, dineroADepositar, 0);
        assertEquals(saldoOriginal, dineroLuegoDeDeposito, 0);
        assertEquals("Ingrese un monto mayor a 0.0", outputCaptor.toString().trim());
    }

    @Test
    public void testDepositarCero1() {
        double saldoOriginal = cuenta01.getBalance();
        double dineroADepositar = 0;
        sB.depositar(cuenta01, dineroADepositar);
        double dineroLuegoDeDeposito = cuenta01.getBalance();

        assertEquals(0, dineroADepositar, 0);
        assertEquals(saldoOriginal, dineroLuegoDeDeposito, 0);

    }
    @Test
    public void testDepositarValornegativo() {
        double dineroADepositar = -100;
        sB.depositar(cuenta01, dineroADepositar);
        assertEquals("Ingrese un monto mayor a 0.0", outputCaptor.toString().trim());
    }

    @Test(expected = Exception.class)
    public void testDepositarCaracterVacio() throws Exception {
        String dineroADepositar = " ";
        sB.depositar(cuenta01, Double.parseDouble(dineroADepositar));
    }
    @Test(expected = Exception.class)
    public void testDepositarCaracterLetra() throws Exception {
        String dineroADepositar = "s";
        sB.depositar(cuenta01, Double.parseDouble(dineroADepositar));
        assertEquals("Valor incorrecto, ingrese un monto correcto:", outputCaptor.toString().trim());
    }

}