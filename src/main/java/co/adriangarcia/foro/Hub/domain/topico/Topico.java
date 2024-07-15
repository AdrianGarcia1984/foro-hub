package co.adriangarcia.foro.Hub.domain.topico;

//import co.adriangarcia.foro.Hub.domain.usuarios.Perfil;
import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    //private String curso;
    //private String respuesta;



//    @Enumerated(EnumType.STRING)
//    private Perfil perfil;

    public Topico(DatosNuevoTopico topico, Usuario user) {
        this.autor=user;
        this.mensaje=topico.mensaje();
        this.titulo=topico.titulo();
        this.fechaCreacion= LocalDateTime.now();
        this.status="1";
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public Usuario getAutor() {
        return autor;
    }
}
