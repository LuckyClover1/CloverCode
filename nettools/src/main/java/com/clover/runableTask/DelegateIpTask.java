package com.clover.runableTask;

import com.clover.config.ConfigUtil;
import com.clover.enumEntity.DelegateIP;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DelegateIpTask implements Runnable{
    private static String delegateWeb = "https://www.xicidaili.com/wn/";

    public static void main(String[] args) {
        new DelegateIpTask().run();

    }

    @Override
    public void run() {
        try {
            System.out.println("开始加载代理IP");
            Connection con = Jsoup.connect(delegateWeb);
            Document doc = con.get();
            if(doc != null){
                List<DelegateIP> list = new ArrayList<>();
                Elements trs = doc.getElementsByTag("tr");
                trs.forEach((tr)->{
                    Elements tds = tr.getElementsByTag("td");
                    if(tds.size() > 0){
                        String ip = tds.get(1).html();
                        String port = tds.get(2).html();
                        String days = tds.get(8).html();
                        if(days.contains("天")){
                            DelegateIP delegateIP= new DelegateIP();
                            delegateIP.setIp(ip);
                            delegateIP.setPort(Integer.valueOf(port));
                            list.add(delegateIP);
                        }
                    }
                });
                ConfigUtil.setDelegateIpList(list);
            }
            System.out.println("完成加载代理IP");

        } catch (Exception e){

        }
    }
}
