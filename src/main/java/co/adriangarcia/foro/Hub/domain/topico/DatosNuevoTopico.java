package co.adriangarcia.foro.Hub.domain.topico;

import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosNuevoTopico (
        @NotNull
        String titulo,
        @NotNull
        String  mensaje,
        @NotNull
        Long idUsuario
){
}
