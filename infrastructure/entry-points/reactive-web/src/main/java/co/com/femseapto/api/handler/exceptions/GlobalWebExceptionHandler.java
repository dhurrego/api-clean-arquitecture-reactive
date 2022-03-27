package co.com.femseapto.api.handler.exceptions;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Component
@Order(-1)
public class GlobalWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties webproperties,
                                     ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, webproperties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorGeneral = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        ExceptionReponse errorResponse = new ExceptionReponse();

        HttpStatus httpStatus;
        String statusCode = String.valueOf(errorGeneral.get("status"));
        Throwable errorException = getError(request);

        if (errorException instanceof WebExchangeBindException) {
            String mensaje = ((WebExchangeBindException) errorException).getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .getDefaultMessage();

            errorResponse.setCode(String.valueOf(((WebExchangeBindException) errorException).getRawStatusCode()));
            errorResponse.setMessage(mensaje);
            httpStatus =  ((WebExchangeBindException) errorException).getStatus();

            return ServerResponse.status(httpStatus)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(errorResponse));
        }

        if (errorException instanceof ResponseStatusException && Objects.nonNull(((ResponseStatusException) errorException).getReason())) {

            errorResponse.setCode(String.valueOf(((ResponseStatusException) errorException).getRawStatusCode()));
            errorResponse.setMessage(((ResponseStatusException) errorException).getReason());
            httpStatus =  ((ResponseStatusException) errorException).getStatus();

            return ServerResponse.status(httpStatus)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(errorResponse));
        }

        switch (statusCode) {
            case "400":
                try {
                    errorResponse.setCode("400");
                    errorResponse.setMessage("Petición incorrecta");
                    httpStatus = HttpStatus.BAD_REQUEST;
                } catch (Exception e) {
                    errorResponse.setCode("500");
                    errorResponse.setMessage("Error general del backend");
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }

                break;
            case "406":
                errorResponse.setCode("406");
                errorResponse.setMessage("Archivo no subido correctamente");
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
                break;
            case "401":
                errorResponse.setCode("401");
                errorResponse.setMessage("No autorizado");
                httpStatus = HttpStatus.UNAUTHORIZED;
                break;
            case "403":
                errorResponse.setCode("403");
                errorResponse.setMessage("Acceso denegado");
                httpStatus = HttpStatus.FORBIDDEN;
                break;
            case "404":
                errorResponse.setCode("404");
                errorResponse.setMessage("No encontrado");
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            default:
                errorResponse.setCode("500");
                errorResponse.setMessage("Error general del backend");
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        return ServerResponse.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorResponse));
    }
}
