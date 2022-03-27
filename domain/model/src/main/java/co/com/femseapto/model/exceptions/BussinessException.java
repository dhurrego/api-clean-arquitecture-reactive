package co.com.femseapto.model.exceptions;

import lombok.Data;

@Data
public class BussinessException extends Exception {

    private Integer statusCode;

    public BussinessException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
