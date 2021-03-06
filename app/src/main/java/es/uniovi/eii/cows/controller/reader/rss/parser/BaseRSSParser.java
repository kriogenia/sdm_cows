package es.uniovi.eii.cows.controller.reader.rss.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.cows.model.NewsItem;

public abstract class BaseRSSParser implements RSSParser {

    protected String url;
    protected List<NewsItem> news;
    protected XmlPullParser xpp;
    protected NewsItem item;
    protected int eventType;

    public BaseRSSParser(String url, XmlPullParser xpp) {
        this.news = new ArrayList<>();
        this.url = url;
        this.xpp = xpp;
    }

    @Override
    public List<NewsItem> parse(InputStream is) throws XmlPullParserException, IOException {
        this.news = new ArrayList<>();
        xpp.setInput(is, "UTF-8");
        eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            parseItem();
            eventType = xpp.next();
        }
        return news;
    }

    @Override
    public String getURL() {
        return url;
    }

    /**
     * Parses the items and adds them to the list
     *
     * @throws IOException
     * @throws XmlPullParserException
     */
    protected abstract void parseItem() throws IOException, XmlPullParserException;

    /**
     * TDD Method
     * @return      Current list of news
     */
    public List<NewsItem> getNews() {
        return news;
    }

    /**
     *  TDD Method
     *
     * @param eventType     New XMLParser Event Type
     */
    protected void setEventType(int eventType) {
        this.eventType = eventType;
    }

    /**
     *  TDD Method
     * @return  Current NewsItem
     */
    public NewsItem getItem() {
        return item;
    }
}
