package edu.javeriana.ingenieria.social.profesor;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.javeriana.ingenieria.social.profesor.controlador.ControladorProfesor;
import edu.javeriana.ingenieria.social.profesor.dominio.Profesor;
import edu.javeriana.ingenieria.social.profesor.servicio.ServicioProfesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorProfesorTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private ControladorProfesor controladorProfesor;

	@BeforeEach
	public void limpiarYRecargarBaseDatos() throws SQLException {
		// Leer el script SQL desde el archivo
		ClassPathResource resource = new ClassPathResource("Script.sql");
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
		resource = new ClassPathResource("Inserts-Prueba.sql");
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
	}
	@Test
	public void testObtenerProfesores() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/profesores/listar"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4));
	}

	@Test
	public void testObtenerProfesorPorId() throws Exception {
		Integer id = 1;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/profesores/obtener?id=" + id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
				.andExpect(MockMvcResultMatchers.jsonPath("$.correoInstitucional").value("pedro.ramirez@universidad.edu"));
	}

	@Test
	public void testObtenerProfesorPorCedula() throws Exception {
		Integer cedula = 2222222;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/profesores/obtenerCedula?cedula=" + cedula))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cedula").value(cedula))
				.andExpect(MockMvcResultMatchers.jsonPath("$.correoInstitucional").value("ana.garcia@universidad.edu"));
	}
}
