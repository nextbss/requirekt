package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
class CustomErrorViewModel(
    var status: Int = 0,
    var code: String? = null,
    var message: String? = null,
    var type: String? = null): AbstractErrorResponse() {

    override fun toJSON(vararg args: ArrayList<Any>): String {
        status = args[0][0] as Int

        code = args[0][1] as String

        message = args[0][2] as String

        type = args[0][3] as String

        return super.toJSON()
    }
}