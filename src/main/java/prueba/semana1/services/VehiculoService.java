package prueba.semana1.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import prueba.semana1.models.Barco;
import prueba.semana1.models.Coche;
import prueba.semana1.models.Moto;
import prueba.semana1.models.Vehiculo;
import prueba.semana1.dto.VehiculoDto;

@Service
public class VehiculoService {

    private final List<Vehiculo> vehiculos = new ArrayList<>();

    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    public Vehiculo crearVehiculo(VehiculoDto dto) {
        Vehiculo nuevo;

        switch (dto.tipo.toLowerCase()) {
            case "coche":
                nuevo = new Coche(dto.color, dto.marca, dto.precio, dto.matricula, dto.tipoMarchas);
                break;
            case "barco":
                nuevo = new Barco(dto.color, dto.marca, dto.precio, dto.matricula, dto.tipoBarco);
                break;
            case "moto":
                nuevo = new Moto(dto.color, dto.marca, dto.precio, dto.matricula, "Motocross");
                break;
            default:
                throw new IllegalArgumentException("Tipo de vehículo no reconocido: " + dto.tipo);
        }
        if (vehiculos.contains(nuevo)) {
            throw new IllegalArgumentException(
                    "Ya existe un vehículo con la matrícula: " + dto.matricula);
        }
        vehiculos.add(nuevo);
        return nuevo;
    }

    public Vehiculo obtenerPorMatricula(String matricula) {

        return vehiculos.stream()
                .filter(v -> v.getMatricula().equalsIgnoreCase(matricula))
                .findFirst()
                .orElse(null);
    }

}
