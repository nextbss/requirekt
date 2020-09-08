package co.ao.nextbss.requirekt

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
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



fun fromArrayList(vararg args: Any): ArrayList<Any> {
    return arrayListOf(args)
}

inline fun require(value: Boolean, vararg args: ArrayList<Any>) {
    if (!value) {
        // Todo: Check if a custom error exists in the consumers base package
        // If a custom package exists load it
        // We might possibly only be able to load one custom error per project
        // Since we will only have one custom require function

        val customErrorBeanFinder = SpringContext.getBean(CustomErrorBeanFinder::class.java)
        val errors: List<AbstractErrorResponse> = customErrorBeanFinder.errors

        val nameOfCustomClassToInstantiate = errors[0].javaClass.canonicalName

        println(nameOfCustomClassToInstantiate)

        val klazz: AbstractErrorResponse = Class.forName(nameOfCustomClassToInstantiate)
            .getDeclaredConstructor().newInstance() as AbstractErrorResponse

        throw ApiException(
            klazz.toJSON(*args),
            HttpStatus.FORBIDDEN
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
            httpStatus)
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
            status)
    }
}


@Component
class SpringContext : ApplicationContextAware {
    @Throws(BeansException::class)
    override fun setApplicationContext(context: ApplicationContext?) {

        // store ApplicationContext reference to access required beans later on
        Companion.context = context
    }

    companion object {
        private var context: ApplicationContext? = null

        /**
         * Returns the Spring managed bean instance of the given class type if it exists.
         * Returns null otherwise.
         * @param beanClass
         * @return
         */
        fun <T : Any?> getBean(beanClass: Class<T>?): T {
            return context!!.getBean(beanClass)
        }
    }
}
