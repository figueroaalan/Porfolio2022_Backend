package alan.web.portfolio.repository;


import alan.web.portfolio.domain.Educacion;
import alan.web.portfolio.domain.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class EducacionRepository {
    private static final String SQL_CREAR = "INSERT INTO b9ospx2mjitpbclrryhg.educacion(titulo, descripcion,fecha_inicio) values(?,?,?)";
    private static final String SQL_ENCONTRAR_POR_ID = "SELECT * FROM b9ospx2mjitpbclrryhg.educacion WHERE ID = ?";
    private static final String SQL_ACTUALIZAR_EDUCACION = "UPDATE b9ospx2mjitpbclrryhg.educacion SET titulo = ?, descripcion = ?, fecha_inicio = ? where id = ?";;
    private static final String SQL_INACTIVAR_EDUCACION = "UPDATE b9ospx2mjitpbclrryhg.educacion SET activo = 0 where id = ?";;
    private static final String SQL_TODAS_ACTIVAS = "SELECT * FROM b9ospx2mjitpbclrryhg.educacion WHERE activo = 1";


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

    public Educacion encontrarPorID(Integer ID) {
        return jdbcTemplate.queryForObject(SQL_ENCONTRAR_POR_ID, educacionRowMapper, ID);
    }

    public void actualizar(Integer educacionId,Educacion educacion) {
        jdbcTemplate.update(SQL_ACTUALIZAR_EDUCACION,
                educacion.getTitulo(), educacion.getDescripcion(),
                educacion.getFecha_inicio(), educacionId);
    }

    public List<Educacion> obtenerTodasActivas() {
        return jdbcTemplate.query(SQL_TODAS_ACTIVAS, educacionRowMapper);
    }
    public void eliminar(Integer educacionId) {
        jdbcTemplate.update(SQL_INACTIVAR_EDUCACION, educacionId);
    }

    private final RowMapper<Educacion> educacionRowMapper = ((rs, rowNUm) ->
            new Educacion (
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha_inicio")));
}
