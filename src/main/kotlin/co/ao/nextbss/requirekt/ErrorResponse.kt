package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
abstract class ErrorResponse {
    open fun toJSON(): String {
        return ErrorWrapper().toJsonString(this)
    }
}