package co.ao.nextbss.requirekt

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/messages")
class RestApiController {

    @GetMapping
    fun get(): ResponseEntity<Any> {
        require(
            value = false,
            status = HttpStatus.NOT_FOUND,
            "101"
        ) {
            "Application with id not found"
        }

        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun add(): ResponseEntity<Any> {
        require(value = false, status = HttpStatus.FORBIDDEN, "104") {
            "Access forbidden. You are not allowed to administrate categories."
        }

        return ResponseEntity.ok().build()
    }

    @GetMapping("/xHrFuz/check")
    fun addURL(): ResponseEntity<Any> {
        require(false) {
            "Message is invalid"
        }
        return ResponseEntity.ok().build()
    }

    @PostMapping("/xHrFuz/transfer")
    fun custom(): ResponseEntity<Any> {
        require(value = false, arrayOf(HttpStatus.FORBIDDEN.value(), "104", "authentication")) {
            "Access forbidden. You are not allowed to administrate categories."
        }
        return ResponseEntity.ok().build()
    }
}