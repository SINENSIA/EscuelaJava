package prueba.semana1.dto;

public class VehiculoDto {
    public String tipo; // "Coche", "Barco", etc.
    public String color;
    public String marca;
    public String precio;
    public String matricula;

    // Campos específicos para ciertos tipos
    public String tipoMarchas; // solo para Coche
    public String tipoBarco; // solo para Barco
}
