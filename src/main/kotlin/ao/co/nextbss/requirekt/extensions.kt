package ao.co.nextbss.requirekt

fun fromArgs(index: Int, args: Array<out ArrayList<Any>>): Any {
    return args[0][index]
}

fun fromArgsAsInt(index: Int, args: Array<out ArrayList<Any>>): Int {
    return args[0][index] as Int
}

fun fromArgsAsString(index: Int, args: Array<out ArrayList<Any>>): String {
    return args[0][index] as String
}

fun fromArgsAsFloat(index: Int, args: Array<out ArrayList<Any>>): Float {
    return args[0][index] as Float
}