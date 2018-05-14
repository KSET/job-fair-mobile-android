package com.undabot.jobfair.news.repository.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "rss", strict = false)
public class RssFeedPage {

    @Element(name = "channel")
    public Channel channel;
}
