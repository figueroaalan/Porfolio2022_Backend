package alan.web.portfolio.repository;

import alan.web.portfolio.domain.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

@Repository
public class ExperienciaRepository {
    private static final String SQL_CREAR = "INSERT INTO b9ospx2mjitpbclrryhg.experiencia(titulo, descripcion,fecha_inicio) values(?,?,?)";
    private static final String SQL_ENCONTRAR_POR_ID = "SELECT * FROM b9ospx2mjitpbclrryhg.experiencia WHERE ID = ?";
    private static final String SQL_TODAS_ACTIVAS = "SELECT * FROM b9ospx2mjitpbclrryhg.experiencia WHERE activo = 1";
    private static final String SQL_ACTUALIZAR_EXPERIENCIA = "UPDATE b9ospx2mjitpbclrryhg.experiencia SET titulo = ?, descripcion = ?, fecha_inicio = ? where id = ?";
    private static final String SQL_INACTIVAR_EXPERIENCIA = "UPDATE b9ospx2mjitpbclrryhg.experiencia SET activo = 0 where id = ?";

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

    public void actualizar(Integer experienciaId,Experiencia experiencia) {
        jdbcTemplate.update(SQL_ACTUALIZAR_EXPERIENCIA,
                experiencia.getTitulo(), experiencia.getDescripcion(),
                experiencia.getFecha_inicio(), experienciaId);
    }

    public List<Experiencia> obtenerTodasActivas() {
        return jdbcTemplate.query(SQL_TODAS_ACTIVAS, experienciaRowMapper);
    }

    private final RowMapper<Experiencia> experienciaRowMapper = ((rs, rowNUm) ->
            new Experiencia (
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha_inicio")));

    public void eliminar(Integer experienciaId) {
        jdbcTemplate.update(SQL_INACTIVAR_EXPERIENCIA, experienciaId);
    }
}
