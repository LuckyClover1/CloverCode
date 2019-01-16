package com.clover.runableTask;

import com.clover.config.ConfigUtil;
import com.clover.database.DatabaseUtil;
import com.clover.enumEntity.*;
import com.clover.netTools.LoadThreadPool;
import com.clover.netTools.SpiderApp;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.security.krb5.Config;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoadHtmlTask implements Runnable {
    private static final CountDownLatch count = new CountDownLatch(100000);

    private final String url;

    public LoadHtmlTask(String url) {
        this.url = url;
    }
    public static void main(String[] args) {
        LoadHtmlTask task = new LoadHtmlTask("https://www.qiushibaike.com/");
        task.run();
    }
    @Override
    public void run() {
        System.out.println("load url : "+this.url);
        final String exceptionURL;
        Connection con = Jsoup.connect(this.url);con.request().header("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cache-Control: max-age=0\n" +
                "Connection: keep-alive\n" +
                "Cookie: _ga=GA1.2.1036768537.1546609561; _gid=GA1.2.1654780166.1546867918; _qqq_uuid_=\"2|1:0|10:1547037437|10:_qqq_uuid_|56:OGEzNTI2YzUxYjFlMGMwYTA0NDAyZGJkZTQyMGIzZDg1ZWM2YThlZQ==|5e676ba0ac82aa26e37618931020f8b9ec5d6732a3a23d646746cd4e9f891b76\"; _xsrf=2|76eeefe9|11eda7278a0cd8159f16069e14ce944d|1547209603; Hm_lvt_2670efbdd59c7e3ed3749b458cafaa37=1547035862,1547128887,1547136628,1547209606; _gat=1; __cur_art_index=1500; Hm_lpvt_2670efbdd59c7e3ed3749b458cafaa37=1547209609\n" +
                "Host: www.qiushibaike.com\n" +
                "If-None-Match: \"10270292659ce494ac72070441f8a125da682368\"\n" +
                "Referer: https://www.qiushibaike.com/\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
//        DelegateIP delegateIP = ConfigUtil.getNextDelegate();
//        DelegateIP delegateIP = new DelegateIP();
//        delegateIP.setPort(9999);
//        delegateIP.setIp("163.125.71.138");
//        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(delegateIP.getIp(),delegateIP.getPort()));
//        try {
//            URLConnection con = (URLConnection) new URL(url).openConnection(proxy);
//            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
//            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            con.setRequestProperty("cookie", "_ga=GA1.2.1036768537.1546609561; _gid=GA1.2.1654780166.1546867918; _qqq_uuid_=\"2|1:0|10:1547037437|10:_qqq_uuid_|56:OGEzNTI2YzUxYjFlMGMwYTA0NDAyZGJkZTQyMGIzZDg1ZWM2YThlZQ==|5e676ba0ac82aa26e37618931020f8b9ec5d6732a3a23d646746cd4e9f891b76\"; _xsrf=2|76eeefe9|11eda7278a0cd8159f16069e14ce944d|1547209603; Hm_lvt_2670efbdd59c7e3ed3749b458cafaa37=1547035862,1547128887,1547136628,1547209606; Hm_lpvt_2670efbdd59c7e3ed3749b458cafaa37=1547209741; __cur_art_index=1501");
//            con.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//            con.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
//            con.setRequestProperty("Connection","keep-alive");
//            con.setRequestProperty("Host","www.qiushibaike.com");
//            con.setRequestProperty("Referer","https://www.qiushibaike.com/");
//            con.setRequestProperty("Upgrade-Insecure-Requests","1");
//
//            con.connect();
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
//            String l = null;
//            while((l=buffer.readLine())!=null){
//                bs.append(l);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Document doc = con.get();
//            Document doc = Jsoup.parse(bs.toString());
            if(doc != null){
                System.out.println("data back success");
                Map<String,Object> qiushiInfo = new HashMap<>();
                //左侧列
                Element col0Element = doc.getElementsByClass("col0").get(0);
                //用户数据
                Element userElement = col0Element.getElementsByClass("side-left-userinfo").get(0);
                Author userInfo = getUserInfo(userElement);
                qiushiInfo.put("userInfo",userInfo);

                //中间列
                Element col1Element = doc.getElementsByClass("col1").get(0);
                QiubaiInfo qiubaiInfo = new QiubaiInfo();
                String title = col1Element.getElementsByClass("article-title").get(0).html();
                qiubaiInfo.setTitle(title);
                //发布时间
                String publishTime = col1Element.getElementsByClass("stats-time").get(0).html();
                //好笑
                String haoxiaoNum = col1Element.getElementsByClass("stats-vote").get(0)
                                            .getElementsByClass("number").get(0).html();
                //发布的版块
                String publishSource = col1Element.getElementsByClass("source-column").get(0).html()
                                            .replace("：",":").split(":",2)[1];

                qiubaiInfo.setPublishTime(DateUtils.parseDate(publishTime+":00","yyyy-MM-dd HH:mm:ss"));
                qiubaiInfo.setPublishSource(publishSource);
                qiubaiInfo.setHaoxiaoNum(haoxiaoNum);

                //正文
                Elements elements = col1Element.getElementsByClass("article");
                Element element = elements.get(0);
                String content = getContent(element);
                qiubaiInfo.setContent(content);

                //正文神评论
                Elements commentEles = col1Element.getElementsByClass("comments-wrapper");
                if(commentEles != null && commentEles.size()>0){
                    List<Comment> comments = getComments(commentEles.get(0));
                    qiushiInfo.put("comments",comments);
                }
/*
                //正文包含的媒体类型
                String type = element.getElementById("single-next-link").attr("class");
                switch (type){
                    case "image":;
                    case "multi":{
                        List<String> names = getImages(element);
                        qiushiInfo.put("imageNames",names.toString());
                        break;
                    }
                    case "video":{
                        String name = getVideo(element);
                        qiushiInfo.put("videoName",name);
                        break;
                    }
                    case "word":{
                        break;
                    }
                }*/
                qiushiInfo.put("qiubaiInfo",qiubaiInfo);
                //TODO 入库
                saveQiushiInfo(qiushiInfo);
                count.countDown();
                TimeUnit.SECONDS.sleep(ConfigUtil.getRandomUnsignedInt(1,2));
                long cou = count.getCount();
                System.out.println(cou);
                if(cou > 0 ){
                    //加载下一条
                    String nextUrl = getNextQiushiUrl(col1Element);
                    if(nextUrl.replaceAll("/","").equalsIgnoreCase(
                            ConfigUtil.getString("spider.host","").replaceAll("/",""))){
                        nextUrl = ConfigUtil.getString("spider.host","")
                                +"/article/" + ConfigUtil.getRandomUnsignedInt(100000000,999999999);
                    }
                    LoadThreadPool.submit(new LoadHtmlTask(nextUrl));

                }

            }else{
                System.out.println("null call back"+ this.url);
            }
        }catch (Exception e) {
            e.printStackTrace();
            try {
                TimeUnit.SECONDS.sleep(10);
                LoadThreadPool.submit(new LoadHtmlTask(ConfigUtil.getString("spider.host","")
                        +"/article/" + ConfigUtil.getRandomUnsignedInt(100000000,999999999)));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获取图片
     * @param element
     * @return 所有图片名称
     */
    private List<String> getImages(Element element){
        List<String> imgNames = new ArrayList<>();
        Elements imgs = element.getElementsByTag("img");
        imgs.forEach((img)->{
            String imgUrl = "https:" + img.attr("src");
            String[] urlPs = imgUrl.split("/");
            imgNames.add(urlPs[urlPs.length-1]);
            System.out.println("获取图片"+imgUrl);
            LoadThreadPool.submit(new MediaDownloadTask(imgUrl,MediaType.Image));
        });

        return imgNames;
    }

    /**
     * 获取视频文件
     * @param element
     * @return 视频名称
     */
    private String getVideo(Element element){
        Element video = element.parent().getElementsByTag("video").get(0);
        Element source = video.getElementsByTag("source").get(0);
        String videoUrl = source.attr("src");
        String videoType = source.attr("type").split("/",2)[1];
        System.out.println("获取视频" + videoUrl);
        LoadThreadPool.submit(new MediaDownloadTask(videoUrl,MediaType.Video));
        String[] urlPs = videoUrl.split("/");
        return urlPs[urlPs.length-1];
    }

    /**
     * 正文文本
     * @param element 正文标签
     * @return
     */
    private String getContent(Element element){
        Element content = element.getElementsByClass("content").get(0);
        return content.html();
    }

    /**
     * 神评论
     * @param element
     * @return
     */
    private List<Comment> getComments(Element element){
        List<Comment> comments = new ArrayList<>();
        Elements commentsElements = element.getElementsByClass("comment-block");
        commentsElements.forEach((e)->{
            Comment comment = new Comment();
            String userName = e.getElementsByClass("userlogin").get(0).attr("title");
            String commentText = e.getElementsByClass("body").get(0).html();
            comment.setComment_userName(userName);
            comment.setComment_context(commentText);
            comments.add(comment);
        });
        return comments;
    }

    /**
     * 获取下一条糗事
     * @param element
     * @return
     */
    private String getNextQiushiUrl(Element element){
        String nextQiushiUrl = element.getElementById("articleNextLink").attr("value");
        return ConfigUtil.getString("spider.host","") + nextQiushiUrl;
    }

    /**
     * 作者信息
     * @param element
     * @return
     */
    private Author getUserInfo(Element element){
        Author user = new Author();
        //作者主页
        String host = ConfigUtil.getString("spider.host","") + element.attr("href");
        user.setAuthorHost(host);
        //头像及名称
        Element userImage = element.getElementsByTag("img").get(0);
        String userName = userImage.attr("alt");
        String userImgUrl = userImage.attr("src");
        user.setAuthorName(userName);
//        user.put("userImgUrl",userImgUrl);
        //年龄、性别
        Elements ageAndSexs = element.getElementsByClass("side-fans-num");
        if(ageAndSexs != null && ageAndSexs.size()>0) {
            String sex = ageAndSexs.get(0).hasClass("userM") ? "Men" : "Women";
            String age = ageAndSexs.get(0).html().replace("<i></i>", "");
            user.setSex(sex);
            user.setAge(Integer.valueOf(age));
        }
        //好笑、粉丝、糗事
        Elements userInfos = element.getElementsByClass("side-detail");
        String haoxiaoNum = userInfos.get(0).getElementsByClass("side-line1").get(0).html();
        String fansNum = userInfos.get(1).getElementsByClass("side-line1").get(0).html();
        String qiushiNum = userInfos.get(2).getElementsByClass("side-line1").get(0).html();
        user.setHaoxiaoNum(haoxiaoNum);
        user.setFansNum(fansNum);
        user.setQiushiNum(qiushiNum);

        return  user;
    }

    private void saveQiushiInfo(Map map){
        Author author = (Author)map.get("userInfo");
        if(author != null){
            int id = DatabaseUtil.selectId("qiubai.getAuthorId",author.getAuthorName());
            if(id > 0){
                author.setAuthorId(id);
            }else{
                DatabaseUtil.insert("qiubai.insertAuthor",author);
            }
        }

        QiubaiInfo qiubaiInfo = (QiubaiInfo)map.get("qiubaiInfo");
        if(qiubaiInfo != null){
            qiubaiInfo.setAuthorId(author.getAuthorId());
            DatabaseUtil.insert("qiubai.insertQiushi",qiubaiInfo);
        }

        List<Object> comments = (List<Object>)map.get("comments");
        if(comments != null){
            comments.forEach((comment)->{
                ((Comment)comment).setQiushi_id(qiubaiInfo.getId());
            });
            DatabaseUtil.batchInsert("qiubai.insertComment",comments);
        }
        System.out.println("save data.");
    }


}
