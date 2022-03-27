package co.com.femseapto.model.departamento.gateways;

import co.com.femseapto.model.departamento.Departamento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartamentoRepository {
    Flux<Departamento> findAll();
    Mono<Departamento> findById(Integer id);
}
