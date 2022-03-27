package co.com.femseapto.usecase.municipio;

import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.model.municipio.Municipio;
import co.com.femseapto.model.municipio.gateways.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
public class MunicipioUseCase {

    private final MunicipioRepository municipioRepository;

    public Flux<Municipio> listar() {
        return municipioRepository.findAll()
                .parallel()
                .runOn(Schedulers.parallel())
                .sequential()
                .sort(comparing(Municipio::getNombre));
    }

    public Mono<Municipio> listarPorId(Integer id) {
        return municipioRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new BussinessException("Municipio no encontrado", 404)));
    }
}
