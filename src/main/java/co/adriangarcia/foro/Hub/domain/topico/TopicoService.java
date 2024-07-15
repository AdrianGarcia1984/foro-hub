package co.adriangarcia.foro.Hub.domain.topico;

import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import co.adriangarcia.foro.Hub.domain.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    private Usuario usuario;

    public String guardarTopico(DatosNuevoTopico topico){
        //La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).
        System.out.println(topico);
        Boolean exist = topicoRepository.existsByTitulo(topico.titulo());
        if (exist){
           return "se encontro el topico en la bd";
        }
        Optional<Usuario> user = usuarioRepository.findById(topico.idUsuario());
        if (user.isPresent()){
            usuario=user.get();
        }
        Topico topicoGuardar = new Topico(topico, user.get());
        topicoRepository.save(topicoGuardar);
        return "topico creado con exito";
    }

    public String eliminarTopico (Long id){
        try{
            var topico = topicoRepository.getReferenceById(id);
            System.out.println("topico delete: "+topico);
            if (topico != null){
                topicoRepository.delete(topico);
                return "eliminado";
            }
            return "no encontrado";
        } catch (EntityNotFoundException e){
            return "error";
        }


    }


}
