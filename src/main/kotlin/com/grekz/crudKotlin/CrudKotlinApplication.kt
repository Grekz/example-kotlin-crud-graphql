package com.grekz.crudKotlin

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.RuntimeWiring
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class CrudKotlinApplication

fun main(args: Array<String>) {

	RuntimeWiring.newRuntimeWiring().scalar(ExtendedScalars.DateTime)
	runApplication<CrudKotlinApplication>(*args)
}

