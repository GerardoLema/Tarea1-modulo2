import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class CategoriaTestUnitaria {
    private Categoria categoria;
    @BeforeEach
    public void setUp() {
        categoria = new Categoria(1, "programacion", "En esta categoria están los libros de programación 1, 2, 3, 4, web, distribuida, en red, etc.");}
    @Test
    public void testCategoriaConstructorAndGetteres() {
        assertAll("validar datos de categoria",
                () -> assertEquals(1, categoria.getIdcategoria()),
                () -> assertEquals("programacion", categoria.getCategoria()),
                () -> assertEquals("En esta categoria están los libros de programación 1, 2, 3, 4, web, distribuida, en red, etc.", categoria.getDescripcion())
        );}
    @Test
    public void testCategoriaSettrs() {
        categoria = new Categoria();
        categoria.setIdcategoria(2);
        categoria.setCategoria("Matematicas");
        categoria.setDescripcion("Ecuaciones de primer grado");
        assertAll("validar datos de categoria",
                () -> assertEquals(2, categoria.getIdcategoria()),
                () -> assertEquals("Matematicas", categoria.getCategoria()),
                () -> assertEquals("Ecuaciones de primer grado", categoria.getDescripcion()));}
    @Test
    public void testCategoriaToString(){
        String str = categoria.toString();
        assertAll("validar datos de categoria",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("programacion")),
                () -> assertTrue(str.contains("En esta categoria están los libros de programación 1, 2, 3, 4, web, distribuida, en red, etc.")));}}