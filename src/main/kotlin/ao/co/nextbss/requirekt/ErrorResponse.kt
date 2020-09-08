package ao.co.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
open class ErrorResponse: AbstractErrorResponse() {
    override fun toJSON(vararg args: ArrayList<Any>): String {
        return ErrorWrapper().toJsonString(this)
    }
}