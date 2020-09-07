package co.ao.nextbss.requirekt

import java.util.ArrayList

class ErrorViewModel(val status: Int = 0,
                     val code: String? = null,
                     val message: String? = null,
                     val type: String? = null): ErrorResponse {

    override fun toJSON(): String {
        val error = ErrorViewModel(status, code, message, type)
        val list = ArrayList<ErrorResponse>()
        list.add(error)
        return ErrorWrapper(list).toJsonString()
    }
}