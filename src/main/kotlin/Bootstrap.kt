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

/*
This is our web application entry point. This gets discovered no matter in what package this is hidden.
 */
class Bootstrap : WebApplicationInitializer {
    override fun onStartup(ctx: ServletContext) {
        //Just for the sake of demonstration we are configuring this application
        //with two configuration files. an application config which contains our services
        //and an web config which contains our controllers

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


