package co.ao.nextbss.requirekt

import org.springframework.http.HttpStatus

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



inline fun require(value: Boolean, vararg args: Array<*>, lazyMessage: () -> Any) {
    if (!value) {
        // Todo: Check if a custom error exists in the consumers base package
        // If a custom package exists load it
        // We might possibly only be able to load one custom error per project
        // Since we will only have one custom require function
        val func = ::CustomErrorViewModel
        val x = func.call(403, "104", lazyMessage().toString(), "authentication",)
        throw ApiException(
           x.toJSON(),
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
