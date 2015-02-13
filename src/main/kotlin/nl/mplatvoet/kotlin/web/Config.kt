package nl.mplatvoet.kotlin.web

import org.springframework.web.WebApplicationInitializer
import javax.servlet.ServletContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.context.annotation.Configuration as configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc as enableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.context.annotation.Bean as bean
import org.springframework.context.annotation.ComponentScan as componentScan
import org.springframework.context.annotation.Bean as bean
import org.springframework.web.servlet.view.velocity.VelocityConfigurer
import org.springframework.web.servlet.view.velocity.VelocityViewResolver
import org.springframework.web.servlet.ViewResolver

/**
 * Created by mplatvoet on 11-9-2014.
 */

class Bootstrap : WebApplicationInitializer {
    override fun onStartup(ctx: ServletContext) {
        //AppConfig
        val appCtx = AnnotationConfigWebApplicationContext()
        appCtx.register<AppConfig>()
        ctx.addListener(ContextLoaderListener(appCtx))

        //WebConfig
        val webCtx = AnnotationConfigWebApplicationContext()
        webCtx.register<WebConfig>()
        val dispatcher = ctx.addServlet("dispatcher", DispatcherServlet(webCtx))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }
}

configuration
open class AppConfig {
    open bean fun textProvider() = StaticTextProvider("Hello World")
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
