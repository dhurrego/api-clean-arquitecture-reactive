package co.com.femseapto.jpa.pais;

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
@Table(name = "paises")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PaisData implements Serializable {

    @Id
    @NotNull(message = "El campo id del país es obligatorio")
    @Min(value = 1, message = "El id del país no puede ser menor de cero")
    Integer id;

    @NotEmpty(message = "El campo nombre del país es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre del pais no puede tener más de 50 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$", message = "Solo se permiten caracteres del alfabeto")
    @Column(name = "nombre", nullable = false, length = 50)
    String nombre;
}
