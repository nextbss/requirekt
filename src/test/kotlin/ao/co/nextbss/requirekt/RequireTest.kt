package ao.co.nextbss.requirekt

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [RequireTest::class])
@ActiveProfiles("test")
@ComponentScan(basePackages = [])
@AutoConfigureMockMvc
internal class RequireTest(
    @Autowired val context: WebApplicationContext,
    @Autowired var mockMvc: MockMvc
) {

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build()
    }

    @Test
    @DisplayName("It Should Return Forbidden http status and error body in json")
    fun itShouldReturnForbiddenErrorMessage() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/messages"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isForbidden)
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['message']")
                    .value("Access forbidden. You are not allowed to administrate categories."))
            .andExpect(
                MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['code']")
                .value("104"))
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['status']")
                .value("403"))
    }

    @Test
    @DisplayName("It Should Return Not found http status and error body in json")
    fun itShouldReturnNotFoundErrorMessage() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['message']")
                    .value("Application with id not found"))
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['code']")
                    .value("101"))
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['status']")
                .value("404"))
    }

    @Test
    @DisplayName("It Should Return Bad Request http status and error body in json")
    fun itShouldReturnBadRequest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages/xHrFuz/check"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['message']")
                    .value("Message is invalid"))
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['status']")
                .value("400"))
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['code']")
                .value(""))
    }

    @Test
    @DisplayName("It Should Return custom error response in json")
    fun itShouldReturnCustomErrorResponse() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/messages/xHrFuz/transfer"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isForbidden)
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['message']")
                    .value("Access forbidden. You are not allowed to administrate categories."))
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['code']")
                    .value("104"))
            .andExpect(
                MockMvcResultMatchers
                    .jsonPath("$.['errors'].[0].['type']")
                    .value("authentication"))
            .andExpect(MockMvcResultMatchers
                .jsonPath("$.['errors'].[0].['status']")
                .value("403"))
    }
}