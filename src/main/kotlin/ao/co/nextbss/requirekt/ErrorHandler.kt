package ao.co.nextbss.requirekt

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ApiException::class)
    fun handleApiException(apiException: ApiException?): ResponseEntity<Any> {
        return ResponseEntity.status(apiException!!.status)
            .contentType(MediaType.APPLICATION_JSON)
            .body(apiException.error)
    }
}
