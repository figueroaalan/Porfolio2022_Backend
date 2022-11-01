package alan.web.portfolio.resource;

import alan.web.portfolio.domain.Educacion;
import alan.web.portfolio.domain.Experiencia;
import alan.web.portfolio.service.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/api/educacion")
public class EducacionResource {
    @Autowired
    EducacionService educacionService;

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearEducacion(@RequestBody Map<String, Object> educacionMap) {

        String titulo = (String) educacionMap.get("titulo");
        String descripcion = (String) educacionMap.get("descripcion");
        String fecha = (String) educacionMap.get("fecha");
        educacionService.crearEducacion(titulo, descripcion, fecha);
        return new ResponseEntity("PIOLA PA", HttpStatus.OK);
    }

    @PutMapping("/{educacionId}")
    public ResponseEntity<Map<String, Boolean>> actualizarEducacion(HttpServletRequest request,
                                                                    @PathVariable("educacionId") Integer educacionId,
                                                                    @RequestBody Educacion educacion) {
        educacionService.actualizarEducacion(educacionId, educacion);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Educacion>> obtenerTodasEducacion(HttpServletRequest request) {
        List<Educacion> educacion = educacionService.obtenerTodasEducacion();
        return new ResponseEntity<>(educacion, HttpStatus.OK);
    }
}




