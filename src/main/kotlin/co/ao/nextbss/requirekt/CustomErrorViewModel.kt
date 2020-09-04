package co.ao.nextbss.requirekt

import java.util.*

class CustomCustomErrorViewModel(val status: Int = 0,
                                 val code: String? = null,
                                 val message: String? = null,
                                 val type: String? = null): CustomErrorResponse {

    override fun toJSON(): String {
        val args = this::class.java.declaredFields
        args.iterator().forEach { print(it.type) }
        val errorViewModel = CustomCustomErrorViewModel(status, code, message, type)
        val viewModels = ArrayList<CustomErrorResponse>()
        viewModels.add(errorViewModel)
        return ErrorWrapper(viewModels).toJsonString()
    }
}