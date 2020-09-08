package co.ao.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
class CustomErrorViewModel(
    var status: Int = 0,
    var code: String? = null,
    var message: String? = null,
    var type: String? = null): AbstractErrorResponse() {

    override fun toJSON(vararg args: ArrayList<Any>): String {
        status = getValueFromIndexAsInt(0, args)
        code = getValueFromIndexAsString(1, args)
        message = getValueFromIndexAsString(2, args)
        type = getValueFromIndexAsString(3, args)
        return super.toJSON()
    }


}



