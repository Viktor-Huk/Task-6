package com.example.mvpexample.model.xml
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "media:group", strict = false)
data class GroupXml constructor(

    @field:ElementList(inline = true, entry = "content", name = "content", required = false, empty = true)
    var videoXml: MutableList<VideoXml>? = null,

    @field:Element(name = "thumbnail")
    var thumbnailXml: ThumbnailXml? = null
)
