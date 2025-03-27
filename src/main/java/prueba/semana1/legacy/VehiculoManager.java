package prueba.semana1.legacy;

import prueba.semana1.models.Vehiculo;
import prueba.semana1.models.Coche;
import prueba.semana1.models.Moto;
import prueba.semana1.models.Barco;

/**
 * VehiculoManager
 * Se encarga de gestionar los Vehiculos.
 */
public class VehiculoManager {
    private Vehiculo vehiculo;

    /**
     * Método para crear un Vehiculo siguiendo el input del usuario.
     *
     * @return Vehiculo vehiculo
     */
    public Vehiculo createVehiculo() {

        InputHandler inputHandler = new InputHandler();

        Integer tipoVehiculo = inputHandler.getTipoVehiculo();

        switch (tipoVehiculo) {
            case 0: // Coche
                this.vehiculo = this.createCoche();
                break;
            case 1: // Moto
                this.vehiculo = this.createMoto();
                break;
            case 2: // Barco
                this.vehiculo = this.createBarco();
                break;
            case 3: // Salir
                break;
            default: // Como default por opcion no incluida, salir
                System.out.println("No se ha encontrado una opción válida.");
                return null;

        }
        return null; // no se ha encontrado ningun resultado valido

    }

    private Vehiculo createCoche() {
        Coche coche = new Coche("Rojo", "Toyota", "20.000 €", "SADF12", "Manual");
        System.out.println(
                "Se ha creado un vehículo de tipo Coche ");
        return coche;
    }

    private Vehiculo createMoto() {
        Moto moto = new Moto("Negro", "Yamaha", "50.000 €", "YTUI78", "Motocross");
        System.out.println(
                "Se ha creado un vehículo de tipo Moto ");
        return moto;
    }

    private Vehiculo createBarco() {
        Barco barco = new Barco("Rojo", "Toyota", "20.000 €", "VBNM76", "Yate");
        System.out.println(
                "Se ha creado un vehículo de tipo Barco ");
        return barco;
    }

    /**
     * pregunta y ejecuta
     * la accion del usuario.
     */
    public boolean runAccion() {

        InputHandler inputHandler = new InputHandler();

        Integer accion = inputHandler.getAccion();

        switch (accion) {
            case 0: // Opcion arranque
                return vehiculo.arrancar();

            case 1: // Opcion parar
                return vehiculo.parar();

            case 2: // Opcion avanzar
                if (vehiculo.isArrancado()) {
                    int metrosAvanzados = inputHandler.getMetros();
                    return vehiculo.avanzar(metrosAvanzados);
                } else {
                    System.out.println(
                            "No puedo avanzar con un vehiculo que no está en marcha,"
                                    + "por favor arranque antes de avanzar.");
                    return false;
                }

            case 3: // Opcion retroceder
                if (vehiculo.isArrancado()) {
                    int metrosRetrocedidos = inputHandler.getMetros();
                    return vehiculo.retroceder(metrosRetrocedidos);
                } else {
                    System.out.println(
                            "No puedo retroceder con un vehiculo que no está en marcha,"
                                    + "por favor arranque antes de avanzar.");
                    return false;
                }
            case 4: // Opcion imprimir datos
                vehiculo.imprimirDatos();
                return false;
            case 5: // Salir
                return true;
            default: // Como default por opcion no incluida, salir
                return true;

        }

    }

}
