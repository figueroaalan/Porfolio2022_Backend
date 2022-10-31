package alan.web.portfolio.repository;


import alan.web.portfolio.domain.Educacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class EducacionRepository {
    private static final String SQL_CREAR = "INSERT INTO portfolio.educacion(titulo, descripcion,fecha_inicio) values(?,?,?)";
    private static final String SQL_ENCONTRAR_POR_ID = "SELECT * FROM portfolio.educacion WHERE ID = ?";
    private static final String SQL_INACTIVAR_EDUCACION = "UPDATE portfolio.educacion SET titulo = ?, descripcion = ?, fecha_inicio = ? where id = ?";;
    private static final String SQL_ACTUALIZAR_EDUCACION = "UPDATE portfolio.educacion SET activo = 0 where id = ?";;


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

    private final RowMapper<Educacion> educacionRowMapper = ((rs, rowNUm) ->
            new Educacion (rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha_inicio")));
}
