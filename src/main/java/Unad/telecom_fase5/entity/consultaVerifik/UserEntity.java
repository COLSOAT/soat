package Unad.telecom_fase5.entity.consultaVerifik;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "documento")
    private String documento;

    @Column(name = "informacion")
    private String informacion;
}
