package co.com.femseapto.api.handler.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ExceptionReponse {
    @Setter
    private String message;

    @Setter
    private String code;

    private final Date timestamp = new Date();
}
