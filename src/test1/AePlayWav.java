/**
 * 作者：肖杨
 * 时间：2018.3.2
 * 功能：播放声音
 */

package test1;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class AePlayWav extends Thread {

    private String filename;
    public AePlayWav(String wavfile){
        filename = wavfile;

    }

    public void run(){

        //获取文件输入流
        File soundFile = new File(filename);

        AudioInputStream audioInputStream = null;
        try{
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        }catch (Exception e1){
            e1.printStackTrace();
            return;
        }
        //获取音频编码对象
        AudioFormat format = audioInputStream.getFormat();

        //设置数据输入
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,format,AudioSystem.NOT_SPECIFIED);

        try{
            auline = (SourceDataLine)AudioSystem.getLine(info);
            auline.open(format);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        auline.start();

        //从输入流读取数据发送到混音器
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[1024];

        try {
            while((nBytesRead = audioInputStream.read(abData,0,abData.length)) != -1){
                if(nBytesRead >= 0 ){
                    auline.write(abData,0,nBytesRead);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            return;
        }finally {
            //清空数据缓冲，并关闭输入
            auline.drain();
            auline.close();
        }
    }
}
