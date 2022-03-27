package co.com.femseapto.jpa.actividadeconomica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "actividades_economicas")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ActividadEconomicaData implements Serializable {

    @Id
    @NotNull(message = "El campo id de la actividad economica es obligatorio")
    String id;

    @NotEmpty(message = "El campo nombre de la actividad es obligatorio")
    @Size(min = 1, max = 200, message = "El nombre de la actividad no puede tener más de 200 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$", message = "Solo se permiten caracteres del alfabeto")
    @Column(name = "nombre", nullable = false, length = 200)
    String nombre;
}
