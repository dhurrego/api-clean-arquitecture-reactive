package co.com.femseapto.api;

import co.com.femseapto.model.departamento.Departamento;
import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.usecase.departamento.DepartamentoUseCase;
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
@RequestMapping(value = "/departamentos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DepartamentoController {

    private final DepartamentoUseCase departamentoUseCase;

    @GetMapping()
    public Mono<ResponseEntity<Flux<Departamento>>> listar() {
        return Mono.just(
                ResponseEntity.ok()
                .body(departamentoUseCase.listar()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Departamento>> listar(@PathVariable("id") Integer id) {
        return departamentoUseCase.listarPorId(id)
                .map( d -> ResponseEntity.ok()
                        .body(d)
                ).onErrorResume( ex -> {
                    if(ex instanceof BussinessException) {
                        HttpStatus httpStatus = HttpStatus.valueOf(((BussinessException) ex).getStatusCode());
                        return Mono.error(() -> new ResponseStatusException(httpStatus, ex.getMessage()));
                    }
                    return Mono.error(ex);
                });
    }
}
