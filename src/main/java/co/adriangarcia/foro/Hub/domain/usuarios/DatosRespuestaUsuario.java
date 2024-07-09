package co.adriangarcia.foro.Hub.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email
) {
}
