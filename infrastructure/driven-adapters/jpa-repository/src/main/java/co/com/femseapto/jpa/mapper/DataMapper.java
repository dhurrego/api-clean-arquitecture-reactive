package co.com.femseapto.jpa.mapper;

import co.com.femseapto.jpa.departamento.DepartamentoData;
import co.com.femseapto.jpa.municipio.MunicipioData;
import co.com.femseapto.jpa.pais.PaisData;
import co.com.femseapto.model.departamento.Departamento;
import co.com.femseapto.model.municipio.Municipio;
import co.com.femseapto.model.pais.Pais;
import org.reactivecommons.utils.ObjectMapper;

public class DataMapper {

    private DataMapper() {
    }

    public static Departamento toEntity(DepartamentoData data) {
        return Departamento.builder()
                .id(data.getId())
                .nombre(data.getNombre())
                .pais(Pais.builder()
                        .id(data.getPais().getId())
                        .nombre(data.getPais().getNombre())
                        .build())
                .build();
    }

    public static DepartamentoData toData(Departamento entity, ObjectMapper mapper) {
        DepartamentoData departamentoData = mapper.map(entity, DepartamentoData.class);
        PaisData paisData = mapper.map(entity.getPais(), PaisData.class);
        departamentoData.setPais(paisData);
        return departamentoData;
    }

    public static Municipio toEntity(MunicipioData data) {
        return Municipio.builder()
                .id(data.getId())
                .nombre(data.getNombre())
                .departamento(Departamento.builder()
                        .id(data.getDepartamento().getId())
                        .nombre(data.getDepartamento().getNombre())
                        .pais(Pais.builder()
                                .id(data.getDepartamento().getPais().getId())
                                .nombre(data.getDepartamento().getPais().getNombre())
                                .build())
                        .build())
                .build();
    }

    public static MunicipioData toData(Municipio entity, ObjectMapper mapper) {
        MunicipioData municipioData = mapper.map(entity, MunicipioData.class);
        DepartamentoData departamentoData = mapper.map(entity.getDepartamento(), DepartamentoData.class);
        PaisData paisData = mapper.map(entity.getDepartamento().getPais(), PaisData.class);
        departamentoData.setPais(paisData);
        municipioData.setDepartamento(departamentoData);
        return municipioData;
    }
}
