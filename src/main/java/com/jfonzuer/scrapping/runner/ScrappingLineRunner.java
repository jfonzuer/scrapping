package com.jfonzuer.scrapping.runner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;

import java.util.Map;

/**
 * Created by pgm on 28/05/17.
 */
public class ScrappingLineRunner implements CommandLineRunner {


    @Override
    public void run(String... strings) throws Exception {


        Connection.Response loginForm = Jsoup.connect("https://scrapethissite.com/pages/gotchas/?gotcha=login")
                .timeout(2000)
                .method(Connection.Method.GET)
                .userAgent("http://scrapingauthority.com")
                .execute();

        Map<String, String> loginCookies = loginForm.cookies();

        Connection.Response login = Jsoup.connect("https://scrapethissite.com/pages/gotchas/?gotcha=login")
                .userAgent("http://scrapingauthority.com")
                .data("user", "user")
                .data("pass","pass")
                .cookies(loginCookies)
                .method(Connection.Method.POST)
                .execute();
        loginCookies = login.cookies();


        Document paginationPage = Jsoup.connect("https://scrapethissite.com/pages/forms/")
                .userAgent("http://scrapingauthority.com")
                .get();;

        Elements pagination = paginationPage.select(".pagination > li > a");
        for (Element e:pagination) {
            String url = e.attr("abs:href");
            //Document page = Jsoup.connect(url).get();
            //scrape the page..
        }

    }
}
