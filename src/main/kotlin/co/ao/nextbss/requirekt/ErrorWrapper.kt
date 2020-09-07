package co.ao.nextbss.requirekt

import co.ao.nextbss.Yoru

class ErrorWrapper(val errors: List<ErrorResponse>) {
    fun toJsonString(): String {
        return Yoru<ErrorWrapper>().toJson(this)
    }
}