package com.undabot.jobfair.news.repository.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "item", strict = false)
public class NewsRss {

    @Element(name = "title")
    public String title;
    @Element(name = "link")
    public String link;
    @Element(name = "description")
    public String description;
    @Element(name = "author", required = false)
    public String author;
    @Element(name = "category", required = false)
    public String category;
    @Element(name = "guid", required = false)
    public String guid;
    @Element(name = "pubDate", required = false)
    public String pubDate;
    @Element(name = "thumbnail")
    public Thumbnail thumbnail;

}
