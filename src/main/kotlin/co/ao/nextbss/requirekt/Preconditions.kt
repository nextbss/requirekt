package co.ao.nextbss.requirekt

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.SpringProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.lang.IllegalStateException
import java.util.logging.Logger
import kotlin.collections.ArrayList


/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 * Note: HttpStatus is [Bad_REQUEST] by default.
 */
inline fun require(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(
            DefaultErrorViewModel(
                HttpStatus.BAD_REQUEST.value(),
                "",
                lazyMessage().toString()
            ).toJSON())
    }
}



inline fun require(value: Boolean, vararg args: ArrayList<Any>,) {
    val logger: Logger = Logger.getLogger("Preconditions")
    if (!value) {
        val customErrorBeanFinder = SpringContext.getBean(CustomErrorBeanFinder::class.java)
        val errors: List<AbstractErrorResponse> = customErrorBeanFinder.errors
        logger.info { "Number of error types found: ${errors.size} " }
        errors.forEach { println(it.javaClass.canonicalName) }

        val filteredListOfCustomErrors = errors.filterNot {
            it.javaClass.canonicalName == "co.ao.nextbss.requirekt.DefaultErrorViewModel"
                || it.javaClass.canonicalName == "co.ao.nextbss.requirekt.ErrorResponse"
                || it.javaClass.canonicalName == "co.ao.nextbss.requirekt.ErrorResponse"
        }

        logger.info { "Number of filtered error types found: ${filteredListOfCustomErrors.size}" }

        filteredListOfCustomErrors.forEach { println(it.javaClass.canonicalName) }

        val nameOfCustomClassToInstantiate = if (customErrorBeanFinder.environment.activeProfiles.contains("test")) {
              filteredListOfCustomErrors[0].javaClass.canonicalName
        } else {
            try {
                filteredListOfCustomErrors[1].javaClass.canonicalName
            } catch (e: IndexOutOfBoundsException) {
                throw IllegalStateException("No CustomError added. Add a Custom Error that subclasses AbstractErrorResponse and annotate it with @ErrorResponse")
            }
        }

        logger.info("Canonical name of class that will instantiated as a custom error: $nameOfCustomClassToInstantiate")

        val customError: AbstractErrorResponse = Class.forName(nameOfCustomClassToInstantiate)
            .getDeclaredConstructor().newInstance() as AbstractErrorResponse

        throw ApiException(
            customError.toJSON(*args),
            Any().getValueFromIndexAsInt(
                0,
                args
            )
        )
    }
}

/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 *
 */
inline fun require(value: Boolean, httpStatus: HttpStatus, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(
            DefaultErrorViewModel(
                httpStatus.value(),
                "",
                lazyMessage().toString()
            ).toJSON(),
            httpStatus.value())
    }
}

/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 *
 */
inline fun require(value: Boolean, status: HttpStatus, errorCode: String, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(
            DefaultErrorViewModel(
                status.value(),
                errorCode,
                lazyMessage().toString()
            ).toJSON(),
            status.value())
    }
}


@Component
class SpringContext : ApplicationContextAware {
    @Throws(BeansException::class)
    override fun setApplicationContext(context: ApplicationContext?) {
        Companion.context = context
    }

    companion object {
        private var context: ApplicationContext? = null

        fun <T : Any?> getBean(beanClass: Class<T>?): T {
            return context!!.getBean(beanClass)
        }
    }
}
