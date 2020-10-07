package es.uniovi.eii.cows.model.rss.parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import es.uniovi.eii.cows.model.NewsItem;

public class ElPaisParser extends BaseRSSParser {

    public ElPaisParser(XmlPullParser xpp) {
        super("https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada", xpp);
    }

    @Override
    protected void parseItem() throws IOException, XmlPullParserException {
        // Is a new element
        if (eventType == XmlPullParser.START_TAG) {
            // The element is the wanted type
            if (xpp.getName().equalsIgnoreCase("item")) {
                // We create a new item to fill
                item = new NewsItem();
            } else if (xpp.getName().equalsIgnoreCase("title") && item != null) {
                // Title element
                item.setTitle(xpp.nextText());
            } else if (xpp.getName().equalsIgnoreCase("description") && item != null) {
                // Description element
                item.setDescription(xpp.nextText());
            }
        // Finished parsing the wanted element
        } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
            Log.i("LNE", item.toString());
            news.add(item);
            item = null;
        }
    }
}
