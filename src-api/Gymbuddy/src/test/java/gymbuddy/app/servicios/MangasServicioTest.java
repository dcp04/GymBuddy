// package mangahub.app.servicios;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

// import mangahub.app.entities.EstadoReserva;
// import mangahub.app.entities.Manga;
// import mangahub.app.entities.Reserva;
// import mangahub.app.entities.Usuario;
// import mangahub.app.error.exception.MangaNotFoundException;
// import mangahub.app.repository.MangaRepository;
// import mangahub.app.repository.ReservaRepository;
// import mangahub.app.service.impl.MangasServiceImpl;

// /**
//  * Pruebas unitarias para el servicio de mangas.
//  */
// @ExtendWith(MockitoExtension.class)
// public class MangasServicioTest {

//     @Mock
//     private MangaRepository mangaRepository;

//     @Mock
//     private ReservaRepository reservaRepository;

//     @InjectMocks
//     private MangasServiceImpl mangasService;

//     /**
//      * Prueba para agregar un manga.
//      */
//     @Test
//     public void testAgregarManga() {
//         Manga manga = new Manga();
//         manga.setTitulo("One Piece");
//         manga.setAutor("Eiichiro Oda");
//         manga.setIsbn("9784088739359");
//         when(mangaRepository.save(manga)).thenReturn(manga);
//         Manga result = mangasService.agregarManga(manga);
//         assertEquals(manga, result);
//     }

//     /**
//      * Prueba para obtener un manga por su ID (cuando existe).
//      */
//     @Test
//     public void testObtenerMangaPorIdExiste() {
//         Long id = 1L;
//         Manga manga = new Manga();
//         manga.setId(id);
//         when(mangaRepository.findById(id)).thenReturn(Optional.of(manga));
//         Manga result = mangasService.obtenerMangaPorId(id);
//         assertEquals(manga, result);
//     }

//     /**
//      * Prueba para obtener un manga por su ID (cuando no existe).
//      */
//     @Test
//     public void testObtenerMangaPorIdNoExiste() {
//         Long id = 1L;
//         when(mangaRepository.findById(id)).thenReturn(Optional.empty());
//         assertThrows(MangaNotFoundException.class, () -> mangasService.obtenerMangaPorId(id));
//     }

//     /**
//      * Prueba para actualizar un manga.
//      */
//     @Test
//     public void testActualizarManga() {
//         Long id = 1L;
//         Manga manga = new Manga();
//         manga.setId(id);
//         manga.setTitulo("Naruto");
//         manga.setAutor("Masashi Kishimoto");
//         manga.setIsbn("9784088739358");
//         Manga detallesManga = new Manga();
//         detallesManga.setTitulo("Naruto Shippuden");
//         detallesManga.setAutor("Masashi Kishimoto");
//         detallesManga.setIsbn("9784088739360");
//         when(mangaRepository.findById(id)).thenReturn(Optional.of(manga));
//         when(mangaRepository.save(manga)).thenReturn(manga);
//         Manga result = mangasService.actualizarManga(id, detallesManga);
//         assertEquals(detallesManga.getTitulo(), result.getTitulo());
//     }

//     /**
//      * Prueba para eliminar un manga.
//      */
//     @Test
//     public void testEliminarManga() {
//         Long id = 1L;
//         mangasService.eliminarManga(id);
//         verify(mangaRepository, times(1)).deleteById(id);
//     }

//     /**
//      * Prueba para listar todos los mangas.
//      */
//     @Test
//     public void testListarTodosLosMangas() {
//         Pageable pageable = Pageable.unpaged();
//         Page<Manga> page = mock(Page.class);
//         when(mangaRepository.findAll(pageable)).thenReturn(page);
//         Page<Manga> result = mangasService.listarTodosLosMangas(pageable);
//         assertEquals(page, result);
//     }

//     /**
//      * Prueba para listar los mangas prestados por un usuario.
//      */
//     @Test
//     public void testListarMangasPrestadosPorUsuario() {
//         Long usuarioId = 1L;
//         Pageable pageable = Pageable.unpaged();
//         Page<Manga> page = mock(Page.class);
//         when(reservaRepository.findMangasPrestadosPorUsuarioId(usuarioId, pageable)).thenReturn(page);
//         Page<Manga> result = mangasService.listarMangasPrestadosPorUsuario(usuarioId, pageable);
//         assertEquals(page, result);
//     }

//     /**
//      * Prueba para reservar un manga.
//      */
//     @Test
//     public void testReservarManga() {
//         Manga manga = new Manga();
//         Usuario usuario = new Usuario();

//         assertNull(mangasService.reservarManga(manga, usuario)); // No implementado, retorna null
//     }

//     /**
//      * Prueba para devolver un manga.
//      */
//     @Test
//     public void testDevolverManga() {
//         Long reservaId = 1L;
//         Reserva reserva = new Reserva();
//         reserva.setId(reservaId);
//         reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
//         when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));
//         mangasService.devolverManga(reservaId);
//         verify(reservaRepository).save(reserva);
//     }
// }
