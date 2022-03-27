package co.com.femseapto.jpa.actividadeconomica;

import co.com.femseapto.jpa.AdapterOperations;
import co.com.femseapto.model.actividadeconomica.ActividadEconomica;
import co.com.femseapto.model.actividadeconomica.gateways.ActividadEconomicaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ActividadEconomicaRepositoryAdapter extends AdapterOperations<ActividadEconomica, ActividadEconomicaData, String, ActividadEconomicaDataRepository> implements ActividadEconomicaRepository {

    public ActividadEconomicaRepositoryAdapter(ActividadEconomicaDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, ActividadEconomica.ActividadEconomicaBuilder.class).build());

    }
}
