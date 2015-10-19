package nl.mplatvoet.kotlin.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.velocity.VelocityConfigurer
import org.springframework.web.servlet.view.velocity.VelocityViewResolver


/*
 * Note that all Spring configuration classes and methods need to be defined as 'open'
 * otherwise these classes and methods would be 'final'. That way all classes and methods
 * can properly be proxied
 */
@Configuration
open class AppConfig {
    open @Bean fun textProvider(): TextProvider = StaticTextProvider("Hello World")
}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = arrayOf("nl.mplatvoet.kotlin.web.controller"))
open class WebConfig : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/")
    }

    @Bean open fun velocityConfig(): VelocityConfigurer = VelocityConfigurer().setProperties { props ->
        props["resource.loader"] = "class"
        props["class.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
    }

    @Bean open fun velocityViewResolver(): ViewResolver {
        val resolver = VelocityViewResolver()
        resolver.setPrefix("/vm/")
        resolver.setSuffix(".vm")
        return resolver
    }
}