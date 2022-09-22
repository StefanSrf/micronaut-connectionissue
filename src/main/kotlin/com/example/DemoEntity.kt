package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity


@Introspected
@MappedEntity
data class DemoEntity(
    @field:Id
    val id: Long,
    val name: String,
)