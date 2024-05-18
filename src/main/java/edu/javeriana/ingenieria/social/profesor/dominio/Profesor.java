package edu.javeriana.ingenieria.social.profesor.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profesor {
    @Id
    private Integer id;
    @Column(name="id_institucional")
    private Integer idInstitucional;
    private Integer cedula;
    @Column(name="correo_institucional")
    private String correoInstitucional;
}
