package co.com.femseapto.model.departamento;

import co.com.femseapto.model.pais.Pais;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer id;
    String nombre;
    Pais pais;
}
