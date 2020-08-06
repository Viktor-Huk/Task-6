package com.example.mvpexample.model.xml
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "thumbnail", strict = false)
data class ThumbnailXml constructor(

    @field:Attribute(name = "url")
    var url: String = ""
)
