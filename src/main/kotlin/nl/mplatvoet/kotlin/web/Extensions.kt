package nl.mplatvoet.kotlin.web

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.view.velocity.VelocityConfigurer
import java.util.Properties

//just makes the bootstrap a bit cleaner to read
inline fun <reified T : Any> AnnotationConfigWebApplicationContext.register() = this.register(javaClass<T>())


fun VelocityConfigurer.setProperties(fn: (Properties) -> Unit) : VelocityConfigurer {
    val properties = Properties()
    fn(properties)
    this.setVelocityProperties(properties)
    return this
}