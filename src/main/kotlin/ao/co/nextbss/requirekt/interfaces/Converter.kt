package co.ao.nextbss.requirekt.interfaces

interface Converter {
    fun toJSON(vararg args: ArrayList<Any>): String
}