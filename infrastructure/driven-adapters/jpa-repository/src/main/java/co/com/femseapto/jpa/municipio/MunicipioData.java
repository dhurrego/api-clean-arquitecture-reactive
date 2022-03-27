package co.com.femseapto.jpa.municipio;

import co.com.femseapto.jpa.departamento.DepartamentoData;
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
@Table(name = "municipios")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MunicipioData implements Serializable {

    @Id
    @NotNull(message = "El campo id del municipio es obligatorio")
    @Min(value = 1, message = "El id del municipio no puede ser menor de cero")
    Integer id;

    @NotEmpty(message = "El campo nombre del municipio es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre del municipio no puede tener más de 100 caracteres")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$", message = "Solo se permiten caracteres del alfabeto")
    @Column(name = "nombre", nullable = false, length = 100)
    String nombre;

    @NotNull(message = "El codigo del departmento es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false, foreignKey = @ForeignKey(name = "FK_municipio_departamento"))
    private DepartamentoData departamento;
}
