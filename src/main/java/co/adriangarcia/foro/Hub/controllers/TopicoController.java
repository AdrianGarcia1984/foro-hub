package co.adriangarcia.foro.Hub.controllers;

import co.adriangarcia.foro.Hub.domain.topico.DatosNuevoTopico;
import co.adriangarcia.foro.Hub.domain.topico.DatosrespuestaTopico;
import co.adriangarcia.foro.Hub.domain.topico.TopicoRepository;
import co.adriangarcia.foro.Hub.domain.topico.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una nuevo topic en la base de datos",
            description = "",
            tags = { "post" })
    public ResponseEntity CrearTopico(@RequestBody @Valid DatosNuevoTopico datos){
        var response = service.guardarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Transactional
    @Operation(
            summary = "consulta todos los topicos en la base de datos",
            description = "",
            tags = { "get" })
    public ResponseEntity<Page<DatosrespuestaTopico>> listadoTopicos(@PageableDefault(size = 4) Pageable paginacion){
        System.out.println( topicoRepository.findAll(paginacion).map(DatosrespuestaTopico::new));

            return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosrespuestaTopico::new));
    }

}
