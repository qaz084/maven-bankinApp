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
    public void testDepositarDinero(){

    }

}