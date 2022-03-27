package co.com.femseapto.model.municipio;

import co.com.femseapto.model.departamento.Departamento;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;
    String nombre;
    Departamento departamento;
}
