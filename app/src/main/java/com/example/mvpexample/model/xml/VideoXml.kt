package com.example.mvpexample.model.xml
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "content", strict = false)
data class VideoXml constructor(

    @field:Attribute(name = "url")
    var url: String = ""
)
