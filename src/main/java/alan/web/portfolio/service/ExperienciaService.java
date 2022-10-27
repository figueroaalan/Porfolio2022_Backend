package alan.web.portfolio.service;

import alan.web.portfolio.domain.Experiencia;
import alan.web.portfolio.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    public void crearExperiencia(String titulo, String descripcion, String fecha) {
        experienciaRepository.create(titulo, descripcion, fecha);
    }
}
