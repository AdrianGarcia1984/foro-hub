package co.adriangarcia.foro.Hub.controllers;


import co.adriangarcia.foro.Hub.domain.usuarios.DatosRespuestaUsuario;
import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import co.adriangarcia.foro.Hub.domain.usuarios.UsuarioRepository;
import co.adriangarcia.foro.Hub.domain.usuarios.DatosRegistroUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    @Operation(summary="registra un nuevo usuario")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                           UriComponentsBuilder uriComponentsBuilder){
        Usuario user;
        var userExiste = usuarioRepository.findByEmail(datosRegistroUsuario.email());
        if (!userExiste.isEnabled()){
            user = usuarioRepository.save(new Usuario(datosRegistroUsuario));
            DatosRespuestaUsuario datosRespuestaMedico = new DatosRespuestaUsuario(
                    user.getNombre(),user.getEmail());
            URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(url).body(datosRespuestaMedico);
        }
        return ResponseEntity.status(200).body("el usuario ya existe");
    }
}
