package co.com.femseapto.jpa.pais;

import co.com.femseapto.jpa.AdapterOperations;
import co.com.femseapto.model.pais.Pais;
import co.com.femseapto.model.pais.gateways.PaisRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PaisRepositoryAdapter extends AdapterOperations<Pais, PaisData, Integer, PaisDataRepository> implements PaisRepository {

    public PaisRepositoryAdapter(PaisDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Pais.PaisBuilder.class).build());

    }
}
