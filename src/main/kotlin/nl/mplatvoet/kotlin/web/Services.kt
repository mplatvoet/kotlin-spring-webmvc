package nl.mplatvoet.kotlin.web

/**
 * Created by mplatvoet on 13-2-2015.
 */

trait TextProvider {
    val value: String
}

class StaticTextProvider(override val value: String) : TextProvider