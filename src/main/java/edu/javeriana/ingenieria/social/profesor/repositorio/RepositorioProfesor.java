package edu.javeriana.ingenieria.social.profesor.repositorio;

import edu.javeriana.ingenieria.social.profesor.dominio.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfesor extends JpaRepository<Profesor, Integer> {
    Profesor findOneByCedula(Integer cedula);

    boolean existsByCedula(Integer cedula);
}
