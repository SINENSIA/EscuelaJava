package prueba.semana1.services;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import prueba.semana1.models.Vehiculo;

@Service
public class ViajeService {

    private final VehiculoService vehiculoService;
    private final Map<String, Thread> viajesActivos = new ConcurrentHashMap<>();

    public ViajeService(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    public boolean iniciarViaje(
            String matricula,
            int velocidadMedia,
            int duracionSegundos) {
        Optional<Vehiculo> optVehiculo = Optional.ofNullable(
                vehiculoService.obtenerPorMatricula(matricula));

        if (optVehiculo.isEmpty()) {
            System.out.println("No se encontró ningún vehículo con matrícula: " + matricula);
            return false;
        }

        Vehiculo vehiculo = optVehiculo.get();

        if (viajesActivos.containsKey(matricula) && viajesActivos.get(matricula).isAlive()) {
            System.out.println("Ya hay un viaje activo para " + matricula);
            return false;
        }

        // Crear hilo de viaje
        Thread viaje = new Thread(() -> {
            vehiculo.arrancar();
            try {
                for (int i = 0; i < duracionSegundos; i++) {
                    vehiculo.avanzar(velocidadMedia); // avance constante por segundo
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                vehiculo.parar();
                viajesActivos.remove(matricula);
            }
        });

        viajesActivos.put(matricula, viaje);
        viaje.start();
        return true;
    }

    public boolean estaEnViaje(String matricula) {
        return viajesActivos.containsKey(matricula)
                && viajesActivos.get(matricula).isAlive();
    }
}
