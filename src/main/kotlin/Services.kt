package nl.mplatvoet.kotlin.web

trait TextProvider {
    val value: String
}

class StaticTextProvider(override val value: String) : TextProvider