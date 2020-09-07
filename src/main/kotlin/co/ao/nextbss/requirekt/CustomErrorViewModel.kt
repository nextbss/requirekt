package co.ao.nextbss.requirekt

class CustomErrorViewModel(val status: Int = 0,
                           val code: String? = null,
                           val message: String? = null,
                           val type: String? = null): ErrorResponse() {

    override fun toJSON(): String {
        return super.toJSON()
    }
}