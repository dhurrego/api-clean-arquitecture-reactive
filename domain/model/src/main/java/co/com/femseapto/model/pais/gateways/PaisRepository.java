package co.com.femseapto.model.pais.gateways;

import co.com.femseapto.model.pais.Pais;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaisRepository {
    Flux<Pais> findAll();
    Mono<Pais> findById(Integer id);
}
