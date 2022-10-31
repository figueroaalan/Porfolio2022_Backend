package alan.web.portfolio.resource;

import alan.web.portfolio.domain.Experiencia;
import alan.web.portfolio.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("")
    public ResponseEntity<List<Experiencia>> obtenerTodasExperiencias(HttpServletRequest request) {
        List<Experiencia> experiencias = experienciaService.obtenerTotasExperiencias();
        return  new ResponseEntity<>(experiencias, HttpStatus.OK);
    }

    @PutMapping("/{experienciaId}")
    public ResponseEntity<Map<String, Boolean>> actualizarExperiencia(HttpServletRequest request,
                                                                      @PathVariable("experienciaId") Integer experienciaId,
                                                                      @RequestBody Experiencia experiencia) {
        experienciaService.actualizarExperiencia(experienciaId, experiencia);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{experienciaId}")
    public ResponseEntity<Map<String, Boolean>> actualizarExperiencia(HttpServletRequest request,
                                                                      @PathVariable("experienciaId") Integer experienciaId) {
        experienciaService.eliminarExperiencia(experienciaId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
