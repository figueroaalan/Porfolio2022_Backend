package alan.web.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

public class Usuario {
    @Getter @Setter private Integer idUsuario;
    @Getter @Setter private String nombre;
    @Getter @Setter private String apellido;
    @Getter @Setter private String email;
    @Getter @Setter private String password;


    public Usuario(Integer idUsuario, String nombre, String apellido, String email, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }
}
