package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.CustomErrorResponse

@CustomErrorResponse
interface CustomErrorResponse {
    fun toJSON(): String
}