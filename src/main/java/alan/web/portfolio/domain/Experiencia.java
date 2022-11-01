package alan.web.portfolio.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Experiencia {
    @Getter @Setter private Integer id;
    @Getter @Setter private String titulo;
    @Getter @Setter private String descripcion;
    @Getter @Setter private String fecha_inicio;

    public Experiencia(Integer id, String titulo, String descripcion, String fecha_inicio) {
        this.id =id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
    }
}
