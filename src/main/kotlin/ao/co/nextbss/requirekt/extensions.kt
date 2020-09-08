package ao.co.nextbss.requirekt

fun getValueFromIndex(index: Int, args: Array<out ArrayList<Any>>): Any {
    return args[0][index]
}

fun getValueFromIndexAsInt(index: Int, args: Array<out ArrayList<Any>>): Int {
    return args[0][index] as Int
}

fun getValueFromIndexAsString(index: Int, args: Array<out ArrayList<Any>>): String {
    return args[0][index] as String
}

fun getValueFromIndexAsFloat(index: Int, args: Array<out ArrayList<Any>>): Float {
    return args[0][index] as Float
}