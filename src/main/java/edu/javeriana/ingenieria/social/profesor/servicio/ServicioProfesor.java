package edu.javeriana.ingenieria.social.profesor.servicio;

import edu.javeriana.ingenieria.social.profesor.dominio.Profesor;
import edu.javeriana.ingenieria.social.profesor.repositorio.RepositorioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProfesor {
    @Autowired
    private RepositorioProfesor repositorio;
    public List<Profesor> obtenerProfesores() {
        return repositorio.findAll();
    }

    public Profesor obtenerProfesor(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    public Profesor obtenerProfesorPorCedula(Integer cedula) {
        return repositorio.findOneByCedula(cedula);
    }

    public boolean profesorExiste(Integer cedula) {
        return repositorio.existsByCedula(cedula);
    }
}
