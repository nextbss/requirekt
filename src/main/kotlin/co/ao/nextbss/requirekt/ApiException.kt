package co.ao.nextbss.requirekt

import org.springframework.http.HttpStatus

class ApiException(val error: String, val status: HttpStatus = HttpStatus.BAD_REQUEST) : RuntimeException()
