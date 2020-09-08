package co.ao.nextbss.requirekt

import co.ao.nextbss.Yoru

open class ErrorWrapper {
    val errors = ArrayList<AbstractErrorResponse>()

    fun toJsonString(error: AbstractErrorResponse): String {
        errors.add(error)
        return Yoru<ErrorWrapper>().toJson(this)
    }
}