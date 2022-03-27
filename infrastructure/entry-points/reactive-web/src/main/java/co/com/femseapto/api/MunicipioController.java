package co.com.femseapto.api;

import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.model.municipio.Municipio;
import co.com.femseapto.usecase.municipio.MunicipioUseCase;
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
@RequestMapping(value = "/municipios", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MunicipioController {

    private final MunicipioUseCase municipioUseCase;

    @GetMapping()
    public Mono<ResponseEntity<Flux<Municipio>>> listar() {
        return Mono.just(
                ResponseEntity.ok()
                .body(municipioUseCase.listar()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Municipio>> listar(@PathVariable("id") Integer id) {
        return municipioUseCase.listarPorId(id)
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
