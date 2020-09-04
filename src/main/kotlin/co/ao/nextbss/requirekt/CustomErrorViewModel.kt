package co.ao.nextbss.requirekt

import java.util.ArrayList

class CustomErrorViewModel(val status: Int = 0,
                           val code: String? = null,
                           val message: String? = null,
                           val type: String? = null): CustomErrorResponse {

    override fun toJSON(): String {
        val error = CustomErrorViewModel(status, code, message, type)
        val list = ArrayList<CustomErrorResponse>()
        list.add(error)
        return ErrorWrapper(list).toJsonString()
    }
}