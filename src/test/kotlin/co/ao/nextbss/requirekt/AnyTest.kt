package co.ao.nextbss.requirekt

import org.junit.jupiter.api.Test

class AnyTest {
    @Test
    fun itShouldConvertFromAnyToInt() {
        val a: Any = 1
        val b: Any = "Time"

        val list = arrayListOf(a, b)

        list.forEach { println(it.javaClass) }
        list.forEach { println(it.toString()) }

        args(list)
    }



    private fun args(vararg args: ArrayList<Any>) {
        println(args[0][0])
    }
}