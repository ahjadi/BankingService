package com.miniproject01.BankingService.steps

import com.miniproject01.BankingService.authentication.jwt.JwtService
import com.miniproject01.BankingService.entity.UserEntity
import com.miniproject01.BankingService.repository.UserRepository
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.MultiValueMap
import kotlin.test.assertContains
import kotlin.test.assertEquals

class BakingServiceSteps {

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Before
    fun setupTestData() {
        val user1 = UserEntity(username = "test1", password = passwordEncoder.encode("Test12345"))
        userRepository.save(user1)

    }

    private var token = ""
    private var headersMap = mutableMapOf<String, List<String>>()

    private var response: ResponseEntity<String>? = null


    @Given("A user registers with username {string} and password {string}")
    fun userCanRegister(username: String, password: String) {
        userRepository.save(
            UserEntity(
                username = username,
                password = passwordEncoder.encode(password))
        )
    }

//    @Given("A user named {string} with username {string} and password {string} is in the database")
//    fun userIsInDatabase(name: String, username:String, password: String){
//        val testUser = UserEntity(
//            name = name,
//            username = username,
//            password = passwordEncoder.encode(password)
//        )
//        userRepository.save(testUser)
//    }

    @Given("I have a token for user {string}")
    fun iHaveATokenForUser(user: String) {
        token = jwtService.generateToken(user)
        headersMap["Authorization"] = listOf("Bearer $token")
    }

    @When("I make a GET request to {string}")
    fun `I make a GET request to`(endpoint: String) {
        val headers = HttpHeaders(
            MultiValueMap.fromMultiValue(headersMap)
        )
        val requestEntity = HttpEntity<String>(headers)
        response = testRestTemplate.exchange(
            endpoint,
            HttpMethod.GET,
            requestEntity,
            String::class.java
        )
    }


    @When("I make a POST request to {string} with body")
    fun iMakeAPostRequest(endpoint: String, payload: String) {
        headersMap["Content-Type"] = listOf("application/json")
        val headers = HttpHeaders(
            MultiValueMap.fromMultiValue(
                headersMap
            )
        )
        val requestEntity = HttpEntity<String>(payload, headers)
        response = testRestTemplate.exchange(
            endpoint,
            HttpMethod.POST,
            requestEntity,
            String::class.java
        )
    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(expectedStatusCode: Int) {
        assertEquals(expectedStatusCode, response?.statusCode?.value())
    }

    @Then("the response body should be {string}")
    fun theResponseBodyShouldBe(expectedBody: String) {
        assertEquals(expectedBody, response?.body)
    }

    @Then("the response body should be")
    fun theResponseBodyShouldBeJSON(expectedJsonBody: String) {
        JSONAssert.assertEquals(expectedJsonBody, response?.body, JSONCompareMode.LENIENT)
    }

    @Then("a token exists")
    fun `there is a token exists`() {
        assertContains(response?.body ?: throw IllegalStateException("Expected a body with a token..."), "token")
    }

}