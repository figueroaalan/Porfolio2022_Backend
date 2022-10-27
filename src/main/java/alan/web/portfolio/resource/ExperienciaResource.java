package alan.web.portfolio.resource;

import alan.web.portfolio.domain.Experiencia;
import alan.web.portfolio.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/experiencia")
public class ExperienciaResource {

    @Autowired
    ExperienciaService experienciaService;

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearExperiencia(@RequestBody Map<String, Object> experienciaMap) {
        String titulo = (String) experienciaMap.get("titulo");
        String descripcion = (String) experienciaMap.get("descripcion");
        String fecha = (String) experienciaMap.get("fecha");
        experienciaService.crearExperiencia(titulo, descripcion, fecha);
        return new ResponseEntity("PIOLA PA",HttpStatus.OK);
    }
}
