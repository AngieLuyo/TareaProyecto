package pe.com.autenticacion.empleado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empleado {
    @Id
    @SequenceGenerator(
            name = "empleado_id_sequence",
            sequenceName = "empleado_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empleado_id_sequence"
    )
    @Column(name = "empleado_id")
    private Integer id;
    @Column(name = "empleado_nombre")
    @NotEmpty
    @Pattern(regexp="[a-zA-Z]{2,20}")
    private String nombre;
    @Column(name = "empleado_apellidoPaterno")
    @NotEmpty
    @Pattern(regexp="[a-zA-Z]{2,20}")
    private String apellidoPaterno;
    @Column(name = "empleado_apellidoMaterno")
    @NotEmpty
    @Pattern(regexp="[a-zA-Z]{2,20}")
    private String apellidoMaterno;

    @Column(name = "empleado_dni")
    @NotEmpty
    @Pattern(regexp="\\d{8}")
    private String dni;

    @Column(name = "empleado_edad")
    @NotEmpty
    @Pattern(regexp="\\d{1,3}")
    private String edad;

    @ManyToOne
    @JoinColumn(name="profesion_id", nullable = false)
    @NotNull(message = "{NotNull.empleado.profesion}")
    private Profesion profesion;

    @Column(name = "empleado_email")
    @NotEmpty
    @Email
    private String email;
    @Column(name = "empleado_fechaNacimiento")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNacimiento;

}
