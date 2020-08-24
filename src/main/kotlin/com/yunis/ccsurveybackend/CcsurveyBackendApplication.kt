package com.yunis.ccsurveybackend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = ["com.yunis"])
@Configuration
class CcsurveyBackendApplication

fun main(args: Array<String>) {
	runApplication<CcsurveyBackendApplication>(*args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer {
	return object : WebMvcConfigurerAdapter() {
		override fun addCorsMappings(registry: CorsRegistry) {
			registry.addMapping("/**")
					.allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT")
					.allowedOrigins("*")
		}
	};
}