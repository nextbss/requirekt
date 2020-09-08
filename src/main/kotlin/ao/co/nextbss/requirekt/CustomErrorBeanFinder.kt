package ao.co.nextbss.requirekt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@ComponentScan(basePackages = [])
open class CustomErrorBeanFinder {
    @Autowired
    lateinit var errors: List<AbstractErrorResponse>

    @Autowired
    lateinit var environment: Environment
}