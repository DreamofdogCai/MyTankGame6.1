package test1;

import javax.swing.*;
import java.awt.*;

class MyGameOverPanel extends JPanel implements Runnable{

    int times = 0;

    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,800,600);
        //设置闪烁
        if(times % 2 == 0) {
            g.setColor(Color.red);
            //定义字体
            Font myFont = new Font("华文新魏", Font.BOLD, 100);
            g.setFont(myFont);
            g.drawString("Game Over", 150, 300);
        }
    }

    @Override
    public void run() {
        while(true){

            //设置睡眠
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