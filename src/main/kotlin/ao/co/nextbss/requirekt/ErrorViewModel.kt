package ao.co.nextbss.requirekt

import co.ao.nextbss.Yoru
import org.springframework.http.HttpStatus

class ErrorViewModel(
    private var status: Int,
    private var code: String,
    private var message: String
)  {

    init {
        this.status = status
        this.code = code
        this.message = message
    }

    fun toJsonString(): String {
        return Yoru<ErrorViewModel>().toJson(this)
    }

    private constructor (status: HttpStatus, code: String, message: String) : this(status.value(), code, message)

    companion object {
        fun singleJSON(status: HttpStatus, errorCode: String, message: String): String {
            val errorViewModel = ErrorViewModel(status, errorCode, message)
            val viewModels = ArrayList<ErrorViewModel>()
            viewModels.add(errorViewModel)
            return ErrorWrapper(viewModels).toJsonString()
        }

        fun singleJSON(status: HttpStatus, message: String): String {
            val errorViewModel = ErrorViewModel(status, "", message)
            val viewModels = ArrayList<ErrorViewModel>()
            viewModels.add(errorViewModel)
            return ErrorWrapper(viewModels).toJsonString()
        }

        fun single(status: HttpStatus, errorCode: String, message: String): List<ErrorViewModel> {
            return listOf(ErrorViewModel(status, errorCode, message))
        }

        fun listToJsonString(errors: List<ErrorViewModel?>?): String {
            return Yoru<List<ErrorViewModel>>().toJson(errors)
        }
    }
}