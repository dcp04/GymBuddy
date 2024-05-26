// package mangahub.app.controladores;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.verifyNoInteractions;
// import static org.mockito.Mockito.when;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import mangahub.app.controller.MangaController;
// import mangahub.app.entities.Manga;
// import mangahub.app.entities.Reserva;
// import mangahub.app.entities.Usuario;
// import mangahub.app.error.exception.ExcepcionCampoVacio;
// import mangahub.app.service.MangasService;
// import mangahub.app.service.user.ReservaService;

// @ExtendWith(MockitoExtension.class)
// public class MangaControladorTest {

//     @Mock
//     private MangasService mangasService;

//     @Mock
//     private ReservaService reservaService;

//     @InjectMocks
//     private MangaController mangaController;

//     /**
//      * Prueba para verificar el método de listar todos los mangas.
//      */
//     @Test
//     public void testListarTodosLosMangas() {
//         // Lista de mangas de ejemplo
//         List<Manga> listaMangas = new ArrayList<>();
//         Manga manga1 = new Manga();
//         manga1.setId(1L);
//         manga1.setTitulo("Manga 1");
//         manga1.setAutor("Autor 1");
//         manga1.setIsbn("1234567890");

//         Manga manga2 = new Manga();
//         manga2.setId(2L);
//         manga2.setTitulo("Manga 2");
//         manga2.setAutor("Autor 2");
//         manga2.setIsbn("0987654321");

//         listaMangas.add(manga1);
//         listaMangas.add(manga2);

//         // Configuración de simulación para el servicio de mangas
//         Pageable pageable = Pageable.unpaged();
//         Page<Manga> mangas = new PageImpl<>(listaMangas, pageable, listaMangas.size());
//         when(mangasService.listarTodosLosMangas(any())).thenReturn(mangas);

//         // Llamando al método del controlador
//         ResponseEntity<Page<Manga>> response = mangaController.listarTodosLosMangas(null, null, 0, 10,null, null);

//         // Verificando el resultado
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mangas, response.getBody());
//     }

    
//     /**
//      * Prueba para verificar el método de obtener manga por ID.
//      */
//     @Test
//     public void testGetMangaById() {
//         // Simulando el manga devuelto por el servicio
//         Manga manga = new Manga();
//         manga.setId(1L);
//         when(mangasService.obtenerMangaPorId(1L)).thenReturn(manga);

//         // Llamando al método del controlador
//         Manga mangaObtenido = mangaController.getMangaById(1L);

//         // Verificando el resultado
//         assertEquals(manga, mangaObtenido);
//     }

//     /**
//      * Prueba para verificar el método de crear manga.
//      */
//     @Test
//     public void testCreateManga() {
//         // Datos de ejemplo
//         Manga manga = new Manga();
//         manga.setTitulo("Manga de prueba");
//         manga.setAutor("Autor de prueba");
//         manga.setIsbn("1234567890");

//         // Simulando la creación de un manga por el servicio
//         when(mangasService.agregarManga(manga)).thenReturn(manga);

//         // Llamando al método del controlador
//         Manga mangaCreado = mangaController.createManga(manga);

//         // Verificando el resultado
//         assertEquals(manga, mangaCreado);
//     }

//     /**
//      * Prueba para verificar el método de crear manga con campos vacíos.
//      */
//     @Test
//     public void testCreateMangaWithEmptyFields() {
//         // Datos de ejemplo con campos vacíos
//         Manga manga = new Manga();

//         // Llamando al método del controlador y esperando una excepción
//         assertThrows(ExcepcionCampoVacio.class, () -> mangaController.createManga(manga));

//         // Verificando que el servicio no fue invocado
//         verifyNoInteractions(mangasService);
//     }

//     /**
//      * Prueba para verificar el método de actualizar manga.
//      */
//     @Test
//     public void testUpdateManga() {
//         // Datos de ejemplo
//         Manga manga = new Manga();
//         manga.setId(1L);
//         manga.setTitulo("Manga de prueba actualizado");
//         manga.setAutor("Autor de prueba actualizado");
//         manga.setIsbn("0987654321");

//         // Simulando la actualización de un manga por el servicio
//         when(mangasService.actualizarManga(1L, manga)).thenReturn(manga);

//         // Llamando al método del controlador
//         Manga mangaActualizado = mangaController.updateManga(1L, manga);

//         // Verificando el resultado
//         assertEquals(manga, mangaActualizado);
//     }

//     /**
//      * Prueba para verificar el método de eliminar manga.
//      */
//     @Test
//     public void testDeleteManga() {
//         // Llamando al método del controlador
//         mangaController.deleteManga(1L);

//         // Verificando que el servicio fue invocado
//         verify(mangasService, times(1)).eliminarManga(1L);
//     }

//     /**
//      * Prueba para verificar el método de realizar reserva.
//      */
//     @Test
//     public void testRealizarReserva() {
//         // Datos de ejemplo
//         Long mangaId = 1L;
//         Usuario usuario = new Usuario();
//         usuario.setId(1L);

//         // Simulando la reserva de un manga por el servicio
//         when(reservaService.esMangaDisponibleParaReserva(mangaId)).thenReturn(true);

//         LocalDate fechaReserva = LocalDate.now();
//         LocalDate fechaExpiracion = fechaReserva.plusDays(7);

//         Reserva reserva = new Reserva();
//         reserva.setManga(new Manga());
//         reserva.setFechaReserva(fechaReserva);
//         reserva.setFechaExpiracion(fechaExpiracion);
//         when(reservaService.crearReserva(mangaId, usuario.getId(), fechaReserva, fechaExpiracion)).thenReturn(reserva);

//         // Llamando al método del controlador
//         ResponseEntity<?> response = mangaController.realizarReserva(mangaId, usuario);

//         // Verificando el resultado
//         assertEquals(HttpStatus.CREATED, response.getStatusCode());
//         // Verifica los detalles de la reserva en el body según lo que se espere
//     }
// }

