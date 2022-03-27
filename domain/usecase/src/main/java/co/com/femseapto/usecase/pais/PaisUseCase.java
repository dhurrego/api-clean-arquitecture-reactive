package co.com.femseapto.usecase.pais;

import co.com.femseapto.model.exceptions.BussinessException;
import co.com.femseapto.model.pais.Pais;
import co.com.femseapto.model.pais.gateways.PaisRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PaisUseCase {

    private final PaisRepository paisRepository;

    public Flux<Pais> listar() {
        return paisRepository.findAll();
    }

    public Mono<Pais> listarPorId(Integer id) {
        return paisRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new BussinessException("Pais no encontrado", 404)));
    }

}
