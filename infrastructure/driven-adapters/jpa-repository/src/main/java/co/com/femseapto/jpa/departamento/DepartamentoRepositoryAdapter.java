package co.com.femseapto.jpa.departamento;

import co.com.femseapto.jpa.AdapterOperations;
import co.com.femseapto.jpa.mapper.DataMapper;
import co.com.femseapto.model.departamento.Departamento;
import co.com.femseapto.model.departamento.gateways.DepartamentoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoRepositoryAdapter extends AdapterOperations<Departamento, DepartamentoData, Integer, DepartamentoDataRepository> implements DepartamentoRepository {

    public DepartamentoRepositoryAdapter(DepartamentoDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Departamento.DepartamentoBuilder.class).build());
    }

    @Override
    protected DepartamentoData toData(Departamento entity) {
        return DataMapper.toData(entity, mapper);
    }

    @Override
    protected Departamento toEntity(DepartamentoData data) {
        return DataMapper.toEntity(data);
    }
}
