package co.adriangarcia.foro.Hub.domain.topico;

import java.time.LocalDateTime;

public record DatosrespuestaTopico(
        String titulo,
        String mensaje,
        Long idUsuario,
        LocalDateTime fechaCreacion,
        String status
) {

    public DatosrespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getFechaCreacion(), topico.getStatus());
    }
}
