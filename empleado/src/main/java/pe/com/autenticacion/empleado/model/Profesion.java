package pe.com.autenticacion.empleado.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profesion {

    @Id
    @SequenceGenerator(
            name = "profesion_id_sequence",
            sequenceName = "profesion_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profesion_id_sequence"
    )
    @Column(name = "profesion_id")
    private int id;

    @Column(name = "profesion_nombre")
    private String nombre;


}
