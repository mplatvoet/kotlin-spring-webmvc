package nl.mplatvoet.kotlin.web

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext

/*
This is our web application entry point. This gets discovered no matter in what package this is hidden.
 */
class bootstrap : WebApplicationInitializer {
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


