package edu.javeriana.ingenieria.social.profesor.controlador;

import edu.javeriana.ingenieria.social.profesor.dominio.Profesor;
import edu.javeriana.ingenieria.social.profesor.servicio.ServicioProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorProfesor {
    @Autowired
    private ServicioProfesor servicio;

    @GetMapping("listar")
    public List<Profesor> obtenerProfesores(){
        return servicio.obtenerProfesores();
    }

    @GetMapping("obtener")
    public ResponseEntity<Profesor> obtenerEstudiantePorId(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerProfesor(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProfesor(id), HttpStatus.OK);
    }

    @GetMapping("obtenerCedula")
    public ResponseEntity<Profesor> obtenerEstudiantePorCedula(@RequestParam Integer cedula){
        if(cedula == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.profesorExiste(cedula)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProfesorPorCedula(cedula), HttpStatus.OK);
    }
}
