package co.com.femseapto.jpa.municipio;

import co.com.femseapto.jpa.AdapterOperations;
import co.com.femseapto.jpa.mapper.DataMapper;
import co.com.femseapto.model.municipio.Municipio;
import co.com.femseapto.model.municipio.gateways.MunicipioRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioRepositoryAdapter extends AdapterOperations<Municipio, MunicipioData, Integer, MunicipioDataRepository> implements MunicipioRepository {

    public MunicipioRepositoryAdapter(MunicipioDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Municipio.MunicipioBuilder.class).build());
    }

    @Override
    protected MunicipioData toData(Municipio entity) {
        return DataMapper.toData(entity, mapper);
    }

    @Override
    protected Municipio toEntity(MunicipioData data) {
        return DataMapper.toEntity(data);
    }
}
