package co.com.femseapto.usecase.actividadeconomica;

import co.com.femseapto.model.actividadeconomica.ActividadEconomica;
import co.com.femseapto.model.actividadeconomica.gateways.ActividadEconomicaRepository;
import co.com.femseapto.model.exceptions.BussinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
public class ActividadEconomicaUseCase {

    private final ActividadEconomicaRepository actividadEconomicaRepository;

    public Flux<ActividadEconomica> listar() {
        return actividadEconomicaRepository.findAll()
                .parallel()
                .runOn(Schedulers.parallel())
                .sequential()
                .sort(comparing(ActividadEconomica::getNombre));
    }

    public Mono<ActividadEconomica> listarPorId(String id) {
        return actividadEconomicaRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new BussinessException("Actividad economica no encontrada", 404)));
    }
}
