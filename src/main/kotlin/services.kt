package nl.mplatvoet.kotlin.web

interface TextProvider {
    val value: String
}

class StaticTextProvider(override val value: String) : TextProvider