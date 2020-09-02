package co.ao.nextbss.requirekt

import org.springframework.http.HttpStatus

/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 * Note: HttpStatus is [Bad_REQUEST] by default
 *
 */
inline fun require(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(ErrorViewModel.singleJSON(HttpStatus.BAD_REQUEST, lazyMessage().toString()))
    }
}

/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 *
 */
inline fun require(value: Boolean, httpStatus: HttpStatus, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(ErrorViewModel.singleJSON(httpStatus, lazyMessage().toString()), httpStatus)
    }
}

/**
 * Throws an [ApiException] with the result of calling [lazyMessage] if the [value] is false.
 *
 */
inline fun require(value: Boolean, status: HttpStatus, errorCode: String, lazyMessage: () -> Any) {
    if (!value) {
        throw ApiException(
            ErrorViewModel.singleJSON(
                status,
                errorCode,
                lazyMessage().toString()
            ),
            status = status
        )
    }
}
