package com.clover.runableTask;

import com.clover.config.ConfigUtil;
import com.clover.enumEntity.MediaType;
import com.clover.util.RegxUtil;
import com.sun.imageio.plugins.common.ImageUtil;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

/**
 * 下载媒体数据
 * @author Clover
 */
public class MediaDownloadTask implements Runnable{
    private final String mediaUrl;
    private final MediaType type;

    /**
     * @param url 资源链接
     * @param type 资源类型
     */
    public MediaDownloadTask(String url, MediaType type) {
        this.mediaUrl = url;
        this.type = type;
    }

    @Override
    public void run() {
        if(!RegxUtil.url(mediaUrl)){
            return;
        }
        int byteRead;
        URL url = null;
        try {
            url = new URL(mediaUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        try {
            //2.获取链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(type == MediaType.Image ? imageFile(mediaUrl) : videoFile(mediaUrl));

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //延迟3到8秒
        try {
            TimeUnit.SECONDS.sleep(ConfigUtil.getRandomUnsignedInt(3,5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String imageFile(String mediaUrl){
        String[] fileNames = mediaUrl.trim().split("/");
        String fileName = fileNames[fileNames.length-1];
        fileName = fileName.contains(".") ? fileName : fileName + ".jpeg";
        String filePath = ConfigUtil.getString("media.path", "E:\\learn\\mysql_qiubai_mediaData\\")
                + "image\\" + fileName;
        mkParentDirs(filePath);

        return filePath;
    }
    private String videoFile(String mediaUrl){
        String[] fileNames = mediaUrl.trim().split("/");
        String fileName = fileNames[fileNames.length-1];
        fileName = fileName.contains(".") ? fileName : fileName + ".mp4";
        String filePath = ConfigUtil.getString("media.path", "E:\\learn\\mysql_qiubai_mediaData\\")
                + "video\\" + fileName;
        mkParentDirs(filePath);
        return filePath;
    }

    private void mkParentDirs(String filePath){
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            synchronized (this){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MediaDownloadTask("https://pic.qiushibaike.com/system/pictures/11943/119437322/medium/app119437322.jpg",MediaType.Image).run();
//        URL url = new URL("http://pic.qiushibaike.com/system/pictures/11943/119437322/medium/app119437322.jpg");

    }

}
