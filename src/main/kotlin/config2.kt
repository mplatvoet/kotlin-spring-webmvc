package nl.mplatvoet.kotlin.web

import org.springframework.context.annotation.Configuration as configuration
import org.springframework.context.annotation.Bean as bean
import nl.mplatvoet.kotlin.web.StaticTextProvider
import org.springframework.web.servlet.config.annotation.EnableWebMvc as enableWebMvc
import org.springframework.context.annotation.ComponentScan as componentScan
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.view.velocity.VelocityConfigurer
import nl.mplatvoet.kotlin.web.setProperties
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.velocity.VelocityViewResolver


/*
 * Note that all Spring configuration classes and methods need to be defined as 'open'
 * otherwise these classes and methods would be 'final'. That way all classes and methods
 * can properly be proxied
 */
configuration
open class AppConfig {
    open bean fun textProvider() : TextProvider = StaticTextProvider("Hello World")
}

configuration enableWebMvc componentScan(basePackages = array("nl.mplatvoet.kotlin.web.controller"))
open class WebConfig : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/")
    }

    bean open fun velocityConfig(): VelocityConfigurer  = VelocityConfigurer().setProperties { props ->
        props["resource.loader"] = "class"
        props["class.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
    }

    bean open fun velocityViewResolver(): ViewResolver {
        val resolver = VelocityViewResolver()
        resolver.setPrefix("/vm/")
        resolver.setSuffix(".vm")
        return resolver
    }
}