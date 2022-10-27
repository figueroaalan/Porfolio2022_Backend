package alan.web.portfolio.repository;

import alan.web.portfolio.domain.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;

@Repository
public class ExperienciaRepository {
    private static final String SQL_CREAR = "INSERT INTO portfolio.experiencia(titulo, descripcion,fecha_inicio) values(?,?,?)";
    private static final String SQL_ENCONTRAR_POR_ID = "SELECT * FROM portfolio.experiencia WHERE ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(String titulo, String descripcion, String fecha) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREAR);
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setString(3, fecha);
            return ps;
            }
        );
    }

    public Experiencia encontrarPorID(Integer ID) {
        return jdbcTemplate.queryForObject(SQL_ENCONTRAR_POR_ID, experienciaRowMapper, ID);
    }

    private final RowMapper<Experiencia> experienciaRowMapper = ((rs, rowNUm) ->
            new Experiencia (rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha_inicio")));
}
