package ao.co.nextbss.requirekt

import co.ao.nextbss.requirekt.interfaces.Converter

abstract class AbstractErrorResponse: Converter {
    override fun toJSON(vararg args: ArrayList<Any>): String {
        return ErrorWrapper().toJsonString(this)
    }
}