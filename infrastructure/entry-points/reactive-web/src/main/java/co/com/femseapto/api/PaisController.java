package co.com.femseapto.api;

import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.model.pais.Pais;
import co.com.femseapto.usecase.pais.PaisUseCase;
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
@RequestMapping(value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaisController {

    private final PaisUseCase paisUseCase;

    @GetMapping()
    public Mono<ResponseEntity<Flux<Pais>>> listar() {
        return Mono.just(
                ResponseEntity.ok()
                .body(paisUseCase.listar()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pais>> listar(@PathVariable("id") Integer id) {
        return paisUseCase.listarPorId(id)
                .map( m -> ResponseEntity.ok()
                        .body(m)
                ).onErrorResume( ex -> {
                    if(ex instanceof BussinessException) {
                        HttpStatus httpStatus = HttpStatus.valueOf(((BussinessException) ex).getStatusCode());
                        return Mono.error(() -> new ResponseStatusException(httpStatus, ex.getMessage()));
                    }
                    return Mono.error(ex);
                });
    }
}
