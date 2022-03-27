package co.com.femseapto.usecase.departamento;

import co.com.femseapto.model.departamento.Departamento;
import co.com.femseapto.model.departamento.gateways.DepartamentoRepository;
import co.com.femseapto.model.exceptions.BussinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
public class DepartamentoUseCase {

    private final DepartamentoRepository departamentoRepository;

    public Flux<Departamento> listar() {
        return departamentoRepository.findAll()
                .sort(comparing(Departamento::getNombre));
    }

    public Mono<Departamento> listarPorId(Integer id) {
        return departamentoRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new BussinessException("Departamento no encontrado", 404)));
    }

}
