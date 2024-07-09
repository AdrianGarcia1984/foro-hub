package co.adriangarcia.foro.Hub.domain.usuarios;

import jakarta.validation.constraints.*;

public record DatosRegistroUsuario (
        @NotBlank
        String nombre,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email,
        @NotNull
        Perfil perfil
){
}
