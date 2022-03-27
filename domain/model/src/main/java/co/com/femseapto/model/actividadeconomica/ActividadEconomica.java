package co.com.femseapto.model.actividadeconomica;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class ActividadEconomica implements Serializable {

    private static final long serialVersionUID = 1L;

    final String id;
    final String nombre;
}
