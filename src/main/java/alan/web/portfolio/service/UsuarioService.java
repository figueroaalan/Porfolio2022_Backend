package alan.web.portfolio.service;


import alan.web.portfolio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean validarUsuario(String email, String password) {
        email = email.toLowerCase();
        return usuarioRepository.encontrarYValidar(email, password);
    }
}
