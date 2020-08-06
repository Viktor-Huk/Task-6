package com.example.mvpexample.model.xml
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class ItemXml constructor(

    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "description")
    var description: String = "",

    @field:Element(name = "duration")
    var duration: String = "",

    @field:Element(name = "group")
    var groupXml: GroupXml? = null
)
