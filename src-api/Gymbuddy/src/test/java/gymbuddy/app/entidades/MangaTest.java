// package mangahub.app.entidades;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.Set;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;
// import mangahub.app.entities.Manga;

// /**
//  * Prueba unitaria para la clase Manga.
//  */
// @ExtendWith(MockitoExtension.class)
// public class MangaTest {

//     private Validator validator;

//     @Mock
//     private Manga mockManga;

//     /**
//      * Configura el validador antes de cada prueba.
//      */
//     @BeforeEach
//     public void setUp() {
//         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();
//     }

//     /**
//      * Prueba para validar un objeto Manga válido.
//      */
//     @Test
//     public void testValidManga() {
//         Manga manga = new Manga();
//         manga.setTitulo("One Piece");
//         manga.setAutor("Eiichiro Oda");
//         manga.setIsbn("9781234567897");

//         Set<ConstraintViolation<Manga>> violations = validator.validate(manga);
//         assertEquals(0, violations.size());
//     }

//     /**
//      * Prueba para validar un objeto Manga inválido.
//      */
//     @Test
//     public void testInvalidManga() {
//         Manga manga = new Manga();
//         manga.setTitulo("");
//         manga.setAutor("Author");
//         manga.setIsbn("12345");

//         Set<ConstraintViolation<Manga>> violations = validator.validate(manga);
//         assertEquals(2, violations.size());
//     }

//     /**
//      * Prueba para verificar el comportamiento del objeto Manga mockeado.
//      */
//     @Test
//     public void testMockManga() {
//         when(mockManga.getTitulo()).thenReturn("Naruto");
//         when(mockManga.getAutor()).thenReturn("Masashi Kishimoto");
//         when(mockManga.getIsbn()).thenReturn("9780123456789");

//         assertEquals("Naruto", mockManga.getTitulo());
//         assertEquals("Masashi Kishimoto", mockManga.getAutor());
//         assertEquals("9780123456789", mockManga.getIsbn());
//     }
// }
