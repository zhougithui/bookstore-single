package org.bear.bookstore.concurrent;
 
import java.io.IOException;
import java.io.RandomAccessFile;
 
public class ReadBigFile {
 public static void readBigFile() throws IOException{
  String fileName = "D:/BaiduYunDownload/myeclipse-2016-1.0-offline-installer-windows.exe";
  RandomAccessFile randomFile = null;
  randomFile = new RandomAccessFile(fileName, "r");
        long fileLength = randomFile.length();
        System.out.println("文件大小:" + fileLength);
        int start = 46000;
        randomFile.seek(0);
        byte[] bytes = new byte[1024 * 10];
        int byteread = 0;
        // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
        // 将一次读取的字节数赋给byteread
        while ((byteread = randomFile.read(bytes)) != -1) {
        	System.out.write(bytes, 0, byteread);
        }
        System.out.println(bytes.length);
        System.out.println(new String(bytes,"UTF-8"));
        if (randomFile != null) {
         randomFile.close();
        }
 
 }
 /**
  * @param args
  * @throws IOException 
  */
 public static void main(String[] args) throws IOException {
   ReadBigFile.readBigFile();
 }
 
}