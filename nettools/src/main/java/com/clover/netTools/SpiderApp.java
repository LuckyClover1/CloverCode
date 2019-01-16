package com.clover.netTools;

import com.clover.config.ConfigUtil;
import com.clover.runableTask.LoadHtmlTask;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class SpiderApp {
    public static void main( String[] args )
    {
        String host = ConfigUtil.getString("spider.host","https://www.qiushibaike.com");
        Connection con = Jsoup.connect(host);
        try {
            Document doc = con.get();
            //糗事首页推荐
            Elements elements = doc.getElementsByClass("item");
            elements.forEach((e)->{
                //a标签
                Elements aElements = e.getElementsByTag("a");
                if(aElements.size() ==0){
                    return;
                }
                Element aElement = aElements.get(0);
                //推荐的糗事链接
                String recmdHref = aElement.attr("href");
                LoadThreadPool.submit(new LoadHtmlTask(host+recmdHref));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
