package alan.web.portfolio.service;

import alan.web.portfolio.domain.Educacion;
import alan.web.portfolio.domain.Experiencia;
import alan.web.portfolio.repository.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EducacionService {
    @Autowired
    EducacionRepository educacionRepository;

    public void crearEducacion(String titulo, String descripcion, String fecha) {
        educacionRepository.create(titulo, descripcion, fecha);
    }


    public List<Educacion> obtenerTodasEducacion() {
        return educacionRepository.obtenerTodasActivas();
    }

    public void actualizarEducacion(Integer educacionId, Educacion educacion) {
        educacionRepository.actualizar(educacionId, educacion);
    }

    public void eliminarEducacion(Integer educacionId) {
        educacionRepository.eliminar(educacionId);
    }
}
