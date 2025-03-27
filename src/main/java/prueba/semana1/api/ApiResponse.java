package prueba.semana1.api;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private T data;
    private LocalDateTime timestamp;
    private String message;
    private int count;

    public ApiResponse(T data) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.message = "OK";
        this.count = calcularCantidad(data);
        
    }

    public ApiResponse(T data, String message) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.count = calcularCantidad(data);
    }

    private int calcularCantidad(T data) {
        if (data instanceof java.util.Collection<?> c) {
            return c.size();
        } else if (data != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }
}
