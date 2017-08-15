package org.bear.bookstore.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StopWatch;

public class UploadFileTest {
	public static void main(String[] args) {
		final String remote_url = "http://localhost/bookstore-single/upload";// 第三方服务器请求地址
        //final String remote_url = "https://ec-t.lanmaoly.com/lanmao-ec-app-qa/lanmaoec/service";// 第三方服务器请求地址
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String result = "";
        try {
        	FileInputStream file = new FileInputStream(new File("d:/1.csv"));
            HttpPost httpPost = new HttpPost(remote_url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, "1");// 文件流
            
    		builder.setCharset(Charset.forName("UTF-8"));
    		
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            
            StopWatch sw = new StopWatch("uploadfile");
            sw.start();
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
            sw.stop();
            System.out.println(result);
            System.out.println(sw.getLastTaskTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
