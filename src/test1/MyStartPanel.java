package test1;

import javax.swing.*;
import java.awt.*;

//就是一个提示作用
class MyStartPanel extends JPanel implements Runnable{

    int times = 0;

    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,800,600);
        //提示信息
        if(times % 2 == 0) {
            g.setColor(Color.yellow);
            //开关信息的字体
            Font myFont = new Font("华文新魏", Font.BOLD, 100);
            g.setFont(myFont);
            g.drawString("Stage: 1", 230, 300);
        }
    }

    @Override
    public void run() {
        while(true){

            //休眠
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }

            times++;
            //重画
            this.repaint();
        }
    }
}
