package co.com.femseapto.jpa.departamento;

import co.com.femseapto.jpa.pais.PaisData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "departamentos")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DepartamentoData implements Serializable {

    @Id
    @NotNull(message = "El campo id del departamento es obligatorio")
    @Min(value = 1, message = "El id del departamento no puede ser menor de cero")
    Integer id;

    @NotEmpty(message = "El campo nombre del departamento es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre del departamento no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$", message = "Solo se permiten caracteres del alfabeto")
    @Column(name = "nombre", nullable = false, length = 100)
    String nombre;

    @NotNull(message = "El codigo del país es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false, foreignKey = @ForeignKey(name = "FK_departamento_pais"))
    private PaisData pais;
}
