package com.undabot.jobfair.news.repository.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(name = "channel")
public class Channel {

    @ElementList(inline = true, required = false)
    public List<NewsRss> articleList;
}
