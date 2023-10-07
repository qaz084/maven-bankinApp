package servicios;

import entidades.Cuenta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RetirarDineroTest {
    private Cuenta cuenta01;
    ServicioBanco sB ;

    @Before
    public void setUp() throws Exception {
         cuenta01 = new Cuenta("mechi", "123", 1000);
         sB = ServicioBanco.getInstancia();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void pruebaRetirarDinero() {
            double saldoOriginal= cuenta01.getBalance();
            double dineroARetirar= 500;
            sB.retirarDinero(cuenta01,dineroARetirar);
             double nuevoSaldo= cuenta01.getBalance();
            assertEquals(500,nuevoSaldo,0);

    }
    @Test
    public void pruebaRetirarDineroCOnSaldoInsuficiente() {
        double saldoOriginal= cuenta01.getBalance();
        double dineroARetirar= 500;
        sB.retirarDinero(cuenta01,dineroARetirar);
        double nuevoSaldo= cuenta01.getBalance();
        assertEquals(500,nuevoSaldo,0);

    }
}