package nl.mplatvoet.kotlin.web.controller

import nl.mplatvoet.kotlin.web.TextProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping


@Controller
open class RootController @Autowired constructor(private val textProvider: TextProvider) {
    @RequestMapping("/") open fun index(map: ModelMap): String {
        map["message"] = textProvider.value
        return "home"
    }
}


