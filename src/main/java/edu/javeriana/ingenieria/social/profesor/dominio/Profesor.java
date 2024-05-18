package edu.javeriana.ingenieria.social.profesor.dominio;

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
    private Integer id_institucional, cedula;
    private String correo_institucional;
}
