package co.ao.nextbss.requirekt

import co.ao.nextbss.Yoru

internal class ErrorWrapper(val errors: List<ErrorViewModel>) {
    fun toJsonString(): String {
        return Yoru<ErrorWrapper>().toJson(this)
    }
}