package nl.mplatvoet.kotlin.web.controller

import org.springframework.web.bind.annotation.RequestMapping as requestMapping
import org.springframework.beans.factory.annotation.Autowired as autowired
import org.springframework.stereotype.Controller as controller
import nl.mplatvoet.kotlin.web.TextProvider
import org.springframework.ui.ModelMap


controller
open class RootController [autowired] (private val textProvider: TextProvider) {
    requestMapping("/") open fun index(map: ModelMap): String {
        map["message"] = textProvider.value
        return "home"
    }
}


