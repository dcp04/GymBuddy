// package mangahub.app.repositorio;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;

// import mangahub.app.entities.Manga;
// import mangahub.app.repository.MangaRepository;
// import mangahub.app.service.impl.MangasServiceImpl;

// /**
//  * Pruebas unitarias para el repositorio de mangas.
//  */
// @ExtendWith(MockitoExtension.class)
// public class MangaRepositorioTest {

//     @Mock
//     private MangaRepository mangaRepositoryMock;

//     @InjectMocks
//     private MangasServiceImpl mangaService;

//     private Manga manga;
//     private List<Manga> mangaList;

//     /**
//      * Configuración inicial para las pruebas.
//      */
//     @BeforeEach
//     void setUp() {
//         manga = new Manga();
//         manga.setId(1L);
//         manga.setTitulo("One Piece");
//         manga.setAutor("Eiichiro Oda");
//         manga.setIsbn("9781234567890");

//         mangaList = new ArrayList<>();
//         mangaList.add(manga);
//     }

//     /**
//      * Prueba para verificar la obtención de todos los mangas.
//      */
//     @Test
//     void testFindAll() {
//         when(mangaRepositoryMock.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(mangaList));

//         Page<Manga> result = mangaService.listarTodosLosMangas(Pageable.unpaged());

//         assertEquals(mangaList.size(), result.getTotalElements());
//         assertTrue(result.getContent().contains(manga));
//     }

//     /**
//      * Prueba para verificar la obtención de un manga por su ID.
//      */
//     @Test
//     void testFindById() {
//         when(mangaRepositoryMock.findById(1L)).thenReturn(Optional.of(manga));

//         Optional<Manga> result = Optional.of(mangaService.obtenerMangaPorId(1L));

//         assertTrue(result.isPresent());
//         assertEquals(manga, result.get());
//     }

//     /**
//      * Prueba para verificar la creación de un manga.
//      */
//     @Test
//     void testSave() {
//         when(mangaRepositoryMock.save(manga)).thenReturn(manga);

//         Manga savedManga = mangaService.agregarManga(manga);

//         assertEquals(manga, savedManga);
//     }

//     /**
//      * Prueba para verificar la eliminación de un manga por su ID.
//      */
//     @Test
//     void testDeleteById() {
//         mangaService.eliminarManga(1L);

//         verify(mangaRepositoryMock).deleteById(1L);
//     }
// }
