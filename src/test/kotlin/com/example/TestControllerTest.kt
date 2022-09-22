package com.example

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest

@MicronautTest
internal class TestControllerTest(
    @Client("/") val client: HttpClient
) {

    @RepeatedTest(10)
    fun `should allow parallel db invocations`() {
        val response = client.toBlocking().exchange<String>("/testParallelDbInvocation")
        assertEquals(200, response.status.code)
    }
}