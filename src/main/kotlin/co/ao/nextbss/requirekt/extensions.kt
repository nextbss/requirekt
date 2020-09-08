package co.ao.nextbss.requirekt

fun Any.getValueFromIndex(index: Int, args: Array<out ArrayList<Any>>): Any {
    return args[0][index]
}

fun Any.getValueFromIndexAsInt(index: Int, args: Array<out ArrayList<Any>>): Int {
    return args[0][index] as Int
}

fun Any.getValueFromIndexAsString(index: Int, args: Array<out ArrayList<Any>>): String {
    return args[0][index] as String
}

fun Any.getValueFromIndexAsFloat(index: Int, args: Array<out ArrayList<Any>>): Float {
    return args[0][index] as Float
}