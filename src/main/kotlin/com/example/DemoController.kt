package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

@Controller
class DemoController(
    private val demoRepository: DemoRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get("testParallelDbInvocation")
    fun testParallelDbInvocation(): Mono<String> {
        return demoRepository.save(DemoEntity(1, "test"))
            .doOnNext { logger.info("entity saved") }
            .flatMap {
                Mono.zip(
                    Mono.just("").doOnNext { logger.info("after no db interaction mono 1") },
                    demoRepository.findById(1L).map { logger.info("after db invocation 1") },
                    demoRepository.findById(1L).map { logger.info("after db invocation 2") },
                    Mono.just("").doOnNext { logger.info("after no db interaction mono 2") },
                )
            }.flatMap {
                logger.info("in join")
                Mono.just("")
            }
    }
}

