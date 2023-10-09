package servicios;

import entidades.Cuenta;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RetirarDineroTest {
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
    public void pruebaRetirarDinero() {
        double saldoOriginal = cuenta01.getBalance();
        double dineroARetirar = 500;
        sB.retirarDinero(cuenta01, dineroARetirar);
        double nuevoSaldo = cuenta01.getBalance();

        assertEquals(500, nuevoSaldo, 0);
        assertEquals(500, cuenta01.getBalance(), 0);
        assertNotEquals(saldoOriginal, nuevoSaldo, 0.0);
    }


    @Test
    public void pruebaRetirarDineroConSaldoInsuficiente() {

        double dineroARetirar = 1500;

        sB.retirarDinero(cuenta01, dineroARetirar);
        assertEquals("Saldo insuficiente", outputCaptor.toString().trim());
    }

    @Test
    public void pruebaRetirarDineroConValorNegativo() {

        double dineroARetirar = -100;

        sB.retirarDinero(cuenta01, dineroARetirar);
        assertEquals("Ingrese un monto mayor a 0.0", outputCaptor.toString().trim());
    }
    @Test
    public void pruebaRetirarDineroConValorCERO() {

        double dineroARetirar = 0;

        sB.retirarDinero(cuenta01, dineroARetirar);
        assertEquals("Ingrese un monto mayor a 0.0", outputCaptor.toString().trim());
    }
    @Test
    public void pruebaRetirarDineroConCaracteresNoValidos() {

        double dineroARetirar = 0;

        sB.retirarDinero(cuenta01, dineroARetirar);
        assertEquals("Ingrese un monto mayor a 0.0", outputCaptor.toString().trim());
    }
}