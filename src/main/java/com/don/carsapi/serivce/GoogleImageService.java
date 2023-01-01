package com.don.carsapi.serivce;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class GoogleImageService {

    public String getImage(String query) throws Exception {
        Document doc = Jsoup.connect("https://www.google.com/search?source=lnms&tbm=isch&q=" + query).get();

        Element image = doc.select(".Q4LuWd[data-src]").first();

        return new URL(image.attr("data-src")).toString();

    }
}
