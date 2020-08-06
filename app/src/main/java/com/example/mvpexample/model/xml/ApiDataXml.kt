package com.example.mvpexample.model.xml

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class ApiDataXml constructor(

    @field:Element(name = "channel")
    var channelXml: ChannelXml? = null
)
