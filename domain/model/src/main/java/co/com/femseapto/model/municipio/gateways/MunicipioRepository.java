package co.com.femseapto.model.municipio.gateways;

import co.com.femseapto.model.municipio.Municipio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MunicipioRepository {
    Flux<Municipio> findAll();
    Mono<Municipio> findById(Integer id);
}
