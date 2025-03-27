package prueba.semana1.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.semana1.api.ApiResponse;
import prueba.semana1.dto.VehiculoDto;
import prueba.semana1.models.Vehiculo;
import prueba.semana1.services.VehiculoService;

/**
 * Controlador REST para la entidad {@link Vehiculo}.
 * 
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Vehiculo>>> getVehiculos() {
        List<Vehiculo> lista = vehiculoService.obtenerTodos();
        return ResponseEntity.ok(new ApiResponse<>(lista));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Vehiculo>> crearVehiculo(@RequestBody VehiculoDto dto) {
        Vehiculo nuevo = vehiculoService.crearVehiculo(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(nuevo, "Vehículo creado correctamente"));
    }

    /**
     * Devuelve un vehículo por su matrícula.
     *
     * @param matricula Matrícula del vehículo a buscar.
     * @return El vehículo con la matrícula indicada, o un código de error 404 si no
     *         se encuentra.
     * @see VehiculoService#obtenerPorMatricula(String)
     */
    @GetMapping("/{matricula}")
    public ResponseEntity<ApiResponse<Vehiculo>> getVehiculoPorMatricula(
            @PathVariable("matricula") String matricula) {
        Vehiculo vehiculo = vehiculoService.obtenerPorMatricula(matricula);
        if (vehiculo != null) {
            return ResponseEntity.ok(new ApiResponse<>(vehiculo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "Vehículo no encontrado"));
        }
    }

}
