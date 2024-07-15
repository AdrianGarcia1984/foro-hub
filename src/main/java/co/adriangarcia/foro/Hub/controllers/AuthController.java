package co.adriangarcia.foro.Hub.controllers;

import co.adriangarcia.foro.Hub.domain.usuarios.DatosAutenticacionUsuario;
import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import co.adriangarcia.foro.Hub.infra.security.DatosJWTToken;
import co.adriangarcia.foro.Hub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {

        System.out.println("paso 1: ingresa");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.email(), datosAutenticacionUsuario.clave()));
        if(authentication.isAuthenticated()){
//            return DatosAutenticacionUsuario.builder()
//                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()).build();
            var JWTtoken = tokenService.generarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

//
//        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
//                datosAutenticacionUsuario.clave());
//        System.out.println("paso 2, se crea el authToken: "+authToken);
//
//
//        var usuarioAutenticado = authenticationManager.authenticate(authToken);
//
//        System.out.println("paso 3: usuario autenticado: "+usuarioAutenticado.isAuthenticated());
//
//        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
//        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
