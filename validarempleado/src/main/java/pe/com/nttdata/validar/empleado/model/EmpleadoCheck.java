package pe.com.nttdata.validar.empleado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmpleadoCheck {
    @Id
    @SequenceGenerator(
            name = "empleadocheck_id_sequence",
            sequenceName = "empleadocheck_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empleadocheck_id_sequence"
    )
    private Integer id;
    private Integer empleadoId;
    private Boolean esEstafador;
    private Date fechaCreacion;
}
