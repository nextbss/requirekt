package co.ao.nextbss.requirekt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component

@Component
@ComponentScan(basePackages = [])
open class CustomErrorBeanFinder {

    lateinit var errors: List<ErrorResponse>
}