package ao.co.nextbss.requirekt

import co.ao.nextbss.requirekt.annotation.ErrorResponse

@ErrorResponse
class AAACustomErrorViewModel(
    var status: Int = 0,
    var code: String? = null,
    var message: String? = null,
    var type: String? = null): AbstractErrorResponse() {

    override fun toJSON(vararg args: ArrayList<Any>): String {
        status = fromArgsAsInt(0, args)
        code = fromArgsAsString(1, args)
        message = fromArgsAsString(2, args)
        type = fromArgsAsString(3, args)
        return super.toJSON()
    }
}



