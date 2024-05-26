// package mangahub.app.repositorio;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import mangahub.app.entities.Reserva;
// import mangahub.app.error.exception.ReservaNotFoundException;
// import mangahub.app.repository.ReservaRepository;
// import mangahub.app.service.impl.ReservaServiceImpl;
// import mangahub.app.service.user.ReservaService;

// /**
//  * Pruebas unitarias para el repositorio de reservas.
//  */
// @ExtendWith(MockitoExtension.class)
// public class ReservaRepositorioTest {

//     @Mock
//     private ReservaRepository reservaRepository;

//     @InjectMocks
//     private ReservaService reservaService = new ReservaServiceImpl();

//     /**
//      * Prueba para verificar la obtención de una reserva por su ID.
//      */
//     @Test
//     public void testObtenerReservaPorId() {
//         Long reservaId = 1L;
//         Reserva reservaMock = new Reserva();
//         reservaMock.setId(reservaId);
//         when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaMock));
//         Reserva result = reservaService.obtenerReservaPorId(reservaId);
//         assertEquals(reservaMock, result);
//     }

//     /**
//      * Prueba para verificar la excepción lanzada al intentar obtener una reserva por un ID que no existe.
//      */
//     @Test
//     public void testObtenerReservaPorIdNoExiste() {
//         Long reservaId = 1L;
//         when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());
//         assertThrows(ReservaNotFoundException.class, () -> {
//             reservaService.obtenerReservaPorId(reservaId);
//         });
//     }
// }
