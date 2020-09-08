package co.ao.nextbss.requirekt.annotation

import org.springframework.stereotype.Component

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class ErrorResponse(

)