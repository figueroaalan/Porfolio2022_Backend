package alan.web.portfolio.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Experiencia {
    @Getter @Setter private String titulo;
    @Getter @Setter private String descripcion;
    @Getter @Setter private Data fecha_inicio;

    public Experiencia(String titulo, String descripcion, Data fecha_inicio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
    }
}
