package co.com.femseapto.api;

import co.com.femseapto.model.actividadeconomica.ActividadEconomica;
import co.com.femseapto.model.departamento.Departamento;
import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.usecase.actividadeconomica.ActividadEconomicaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/actividades-economicas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ActividadEconomicaController {

    private final ActividadEconomicaUseCase actividadeconomicasUseCase;

    @GetMapping()
    public Mono<ResponseEntity<Flux<ActividadEconomica>>> listar() {
        return Mono.just(
                ResponseEntity.ok()
                .body(actividadeconomicasUseCase.listar()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ActividadEconomica>> listar(@PathVariable("id") String id) {
        return actividadeconomicasUseCase.listarPorId(id)
                .map( a -> ResponseEntity.ok()
                        .body(a)
                ).onErrorResume( ex -> {
                    if(ex instanceof BussinessException) {
                        HttpStatus httpStatus = HttpStatus.valueOf(((BussinessException) ex).getStatusCode());
                        return Mono.error(() -> new ResponseStatusException(httpStatus, ex.getMessage()));
                    }
                    return Mono.error(ex);
                });
    }
}
