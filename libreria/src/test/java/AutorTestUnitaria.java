import com.distribuida.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorTestUnitaria { private Autor autor;
    @BeforeEach
    public void setUp() {
        autor = new Autor(1, "Javier", "Lema", "Ecuador", "Cotopaxi","289745487","gl23@hotmail.com"); }
    @Test
    public void testAutorConstructorAndGetteres() {
        assertAll("validar datos de autor",
                () -> assertEquals(1, autor.getIdautor()),
                () -> assertEquals("Javier", autor.getNombre()),
                () -> assertEquals("Lema", autor.getApellido()),
                () -> assertEquals("Ecuador", autor.getPais()),
                () -> assertEquals("Cotopaxi", autor.getDireccion()),
                () -> assertEquals("289745487", autor.getTelefono()),
                () -> assertEquals("gl23@hotmail.com", autor.getCorreo()) ); }
    @Test
    public void testAutorSettrs() { autor = new Autor();
        autor.setIdautor(2);
        autor.setNombre("Pedro");
        autor.setApellido("Acurio");
        autor.setPais("España");
        autor.setDireccion("Av. america");
        autor.setTelefono("0945678858");
        autor.setCorreo("aer@hotmail.com");
        assertAll("validar datos de autor",
                () -> assertEquals(2, autor.getIdautor()),
                () -> assertEquals("Pedro", autor.getNombre()),
                () -> assertEquals("Acurio", autor.getApellido()),
                () -> assertEquals("España", autor.getPais()),
                () -> assertEquals("Av. america", autor.getDireccion()),
                () -> assertEquals("0945678858", autor.getTelefono()),
                () -> assertEquals("aer@hotmail.com", autor.getCorreo())); }
    @Test
    public void testAutorToString(){String str = autor.toString();
        assertAll("validar datos de autor",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Javier")),
                () -> assertTrue(str.contains("Lema")),
                () -> assertTrue(str.contains("Ecuador")),
                () -> assertTrue(str.contains("Cotopaxi")),
                () -> assertTrue(str.contains("289745487")),
                () -> assertTrue(str.contains("gl23@hotmail.com")));}
}