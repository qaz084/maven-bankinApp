package entidades;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta {
    private String usuario;
    private String password;
    private double balance=0.0;
    @Override
    public String toString() {
        return
                "usuario='" + usuario  +
                ", password='" + password +
                ", balance=" + balance +"\n";

    }
}
