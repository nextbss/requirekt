package ao.co.nextbss.requirekt

import org.springframework.http.HttpStatus

class ApiException(val error: String, val status: Int = HttpStatus.BAD_REQUEST.value()) : RuntimeException()
