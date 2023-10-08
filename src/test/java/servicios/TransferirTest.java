package servicios;

import entidades.Cuenta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TransferirTest {

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
    public void testTransferirConSaldoSuficiente() throws Exception {
        //TIENE DE SALDO $1000;
        sB.transferir(cuenta01, 500.0, "Ana");
        assertEquals(500.0, cuenta01.getBalance(), 0);
    }

    @Test
    public void testTransferirConSaldoInsuficiente() throws Exception {
        cuenta01.setBalance(0.0);
        sB.transferir(cuenta01, 500.0, "Ana");
        assertEquals("Saldo insuficiente", outputCaptor.toString().trim());
    }

    @Test
    public void testTransferirCero() throws Exception {
        cuenta01.setBalance(0.0);
        sB.transferir(cuenta01, 0, "Ana");
        assertEquals("Ingrese un monto mayor a $0.0", outputCaptor.toString().trim());
    }

    @Test
    public void testTransferirMontoNegativo() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, -100, "Ana");
        assertEquals("Ingrese un monto mayor a $0.0", outputCaptor.toString().trim());
    }

    @Test(expected = Exception.class)
    public void testTransferirCaracterVacio() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, Double.parseDouble(" "), "Ana");
        assertEquals("Ingrese un monto correcto", outputCaptor.toString().trim());
    }
    @Test(expected = Exception.class)
    public void testTransferirCaracterLetra() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, Double.parseDouble("s"), "Ana");
        assertEquals("Ingrese un monto correcto", outputCaptor.toString().trim());
    }
    @Test
    public void testTransferirAPropiaCuenta() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, 500, "mechi");
        assertEquals(1000, cuenta01.getBalance(),0);
    }
    @Test
    public void testTransferirCuentaIncorrecta() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, 500, "Mechi");
        assertEquals("---Cuenta no encontrada---", outputCaptor.toString().trim());
    }
    @Test
    public void testTransferirCuentaCaracterIncorrecto() throws Exception {
        cuenta01.setBalance(1000);
        sB.transferir(cuenta01, 500, " ");
        assertEquals("---Cuenta no encontrada---", outputCaptor.toString().trim());
    }
}