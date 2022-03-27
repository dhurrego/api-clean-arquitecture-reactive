package co.com.femseapto.model.pais;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    final Integer id;
    final String nombre;

}
