package alan.web.portfolio.repository;

import alan.web.portfolio.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.security.auth.message.AuthException;
import java.sql.PreparedStatement;

@Repository
public class UsuarioRepository {
    private static final String SQL_CREAR_USUARIO = "INSERT INTO b9ospx2mjitpbclrryhg.usuarios(nombre, apellido, email, password) VALUES(?, ?, ?, ?)";
    private static final String SQL_ENCONTRAR_EMAIL = "SELECT * FROM b9ospx2mjitpbclrryhg.usuarios WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(String nombre, String apellido, String email, String password) {
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_CREAR_USUARIO);
                    ps.setString(1, nombre);
                    ps.setString(2, apellido);
                    ps.setString(3, email);
                    ps.setString(4, password);
                    return ps;
                }
        );
    }

    public boolean encontrarYValidar(String email, String password) {
        Usuario user = jdbcTemplate.queryForObject(SQL_ENCONTRAR_EMAIL, userRowMapper, email);
        return password == user.getPassword();
    }

    private final RowMapper<Usuario> userRowMapper = ((rs, rowNum) ->
            new Usuario(rs.getInt("idUsuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("password")));
}
