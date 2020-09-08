package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
class DefaultErrorViewModel(val status: Int?,
                            val code: String?,
                            val message: String?
) : AbstractErrorResponse() {
    override fun toJSON(vararg args: ArrayList<Any>): String {
        return super.toJSON()
    }
}