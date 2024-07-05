package me.dio4.adapters.in.exception;


import me.dio4.domain.exception.ChampionNOtFundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*Classe criada para usar as exceções
* numero de champion for diferente da quantidade existe e
*outras exceções que podem vir a ocorrer
*/


@ControllerAdvice
public class GlobalExceptionHandler {
// Sera usada quando a exceção de champions

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ChampionNOtFundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNOtFundException domainError){

        return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity <ApiError> handleDomainException(Exception unexpectedError){
        String message = "Ops! Ocorreu um erro inesperado :(";
        logger.error(message,unexpectedError);

        return ResponseEntity.internalServerError().body(new ApiError(message));
    }


    public  record ApiError(String message){
    }
}
