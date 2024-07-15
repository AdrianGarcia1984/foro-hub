package co.adriangarcia.foro.Hub.infra.security;

import co.adriangarcia.foro.Hub.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("llega el email para ser verificado "+ email);
        var user = usuarioRepository.findByEmail(email);
        System.out.println("llega de la base de datos "+user.isEnabled()+user.getUsername()+user.getAuthorities());
        return user;
    }
}
