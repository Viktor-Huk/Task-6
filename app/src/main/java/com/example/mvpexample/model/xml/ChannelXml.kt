package com.example.mvpexample.model.xml
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class ChannelXml constructor(

    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "link")
    var link: String = "",

    @field:ElementList(inline = true, entry = "item", name = "item", empty = true)
    var itemXml: MutableList<ItemXml>? = null
)
