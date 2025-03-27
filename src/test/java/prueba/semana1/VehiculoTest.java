package prueba.semana1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import prueba.semana1.models.Barco;
import prueba.semana1.models.Coche;
import prueba.semana1.models.Vehiculo;

/**
 * VehiculoTest Pruebas unitarias para la clase Vehiculo
 */
public class VehiculoTest {

    @Test
    public void testVehiculosIgualesPorMatriculaMismoTipo() {
        Vehiculo v1 = new Coche("Rojo", "Toyota", "20000", "ABC123", "Manual");
        Vehiculo v2 = new Coche("Negro", "Renault", "15000", "ABC123", "Automático");

        assertEquals(v1, v2, "Dos coches con la misma matrícula deben ser iguales");
        assertEquals(
                v1.hashCode(),
                v2.hashCode(),
                "HashCode debe ser igual si las matrículas son iguales");
    }

    @Test
    public void testVehiculosDeTiposDistintosNoSonIguales() {
        Vehiculo coche = new Coche("Rojo", "Toyota", "20000", "ABC123", "Manual");
        Vehiculo barco = new Barco("Blanco", "Yamaha", "80000", "ABC123", "Lancha");

        assertNotEquals(
                coche,
                barco,
                "Vehículos de distinto tipo con misma matrícula no deben ser iguales");
    }
}
