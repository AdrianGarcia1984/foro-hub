package co.adriangarcia.foro.Hub.domain.topico;

import co.adriangarcia.foro.Hub.domain.usuarios.Usuario;
import co.adriangarcia.foro.Hub.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
