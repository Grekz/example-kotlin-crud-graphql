package com.grekz.crudKotlin

import com.coxautodev.graphql.tools.ObjectMapperConfigurer
import com.coxautodev.graphql.tools.ObjectMapperConfigurerContext
import com.coxautodev.graphql.tools.PerFieldObjectMapperProvider
import com.coxautodev.graphql.tools.SchemaParserOptions
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import graphql.language.FieldDefinition
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AppConfiguration {
  @Bean
  fun objectMapper(): ObjectMapper? {
    return ObjectMapper().registerModule(JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
  }

  @Bean
  fun schemaParserOptions(): SchemaParserOptions? {
    return SchemaParserOptions.newOptions()
      .objectMapperProvider(PerFieldObjectMapperProvider { fieldDefinition: FieldDefinition? -> objectMapper() }).build()
  }

//  @Bean
//  fun schemaParserOptions(): SchemaParserOptions? {
//    return SchemaParserOptions.newOptions()
//      .objectMapperConfigurer(ObjectMapperConfigurer { mapper: ObjectMapper, context: ObjectMapperConfigurerContext? -> mapper.registerModule(JavaTimeModule()) }).build()
//  }

  @Bean
  fun dateTimeType(): GraphQLScalarType? {
    return ExtendedScalars.DateTime
  }
}