package co.adriangarcia.foro.Hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Boolean existsByTitulo(String titulo);
}
