package entidades;
// adminMechi Validación

//Admin  menu 1) crearCuentas

// no admin 1) retirar 2) depositar 3) transferir

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@RequiredArgsConstructor

public class Cuenta {
    private String usuario;
    private String password;
    private double balance=0.0;

}
