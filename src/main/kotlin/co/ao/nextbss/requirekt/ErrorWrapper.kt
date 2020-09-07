package co.ao.nextbss.requirekt

import co.ao.nextbss.Yoru

class ErrorWrapper {
    val errors = ArrayList<ErrorResponse>()

    fun toJsonString(error: ErrorResponse): String {
        errors.add(error)
        return Yoru<ErrorWrapper>().toJson(this)
    }
}