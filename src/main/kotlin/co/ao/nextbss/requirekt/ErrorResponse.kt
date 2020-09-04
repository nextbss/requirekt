package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
interface ErrorResponse {
    fun toJSON(): String
}