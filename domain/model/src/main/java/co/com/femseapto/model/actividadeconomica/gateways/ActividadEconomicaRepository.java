package co.com.femseapto.model.actividadeconomica.gateways;

import co.com.femseapto.model.actividadeconomica.ActividadEconomica;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActividadEconomicaRepository {
    Flux<ActividadEconomica> findAll();
    Mono<ActividadEconomica> findById(String id);
}
