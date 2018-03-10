/**
 * 作者：肖杨
 * 时间：2018.2.4
 * 功能：TankGame4.0
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 * 3.可以发射子弹，子弹连发（最多5）
 * 4.当我的坦克击中敌人坦克时，敌人就消失（爆炸效果）
 * 5.我被击中后，显示爆炸效果
 * 6.防止敌人坦克重叠运动
 *      6.1决定把判断是否碰撞的函数写到EnemyTank类
 * 7.可以分关
 *      7.1做一个开始的Panel,它是一个空的
 *      7.2闪烁效果
 * 8.可以在玩游戏的时候暂停和继续
 *      8.1当用户点击暂停时，子弹速度和坦克速度设为0，并让坦克方向不发生变化
 * 9.可以记录玩家的成绩
 *      9.1用文件流
 *      9.2单写一个记录类，完成对玩家记录
 *      9.2完成保存共击毁了多少辆敌人坦克的记录
 *      9.3存盘退出游戏， 可以记录当时的敌人坦克坐标，并可以恢复
 * 10.java如何操作声音文件
 *      10.1对声音文件的操作
 */

package test1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTankGame extends JFrame implements ActionListener,Runnable{

    MyPanel mp = null;

    //定义一个开始面板
    MyStartPanel msp = null;

    //定义一个结束面板
    MyGameOverPanel mgop = null;

    //作出我需要的菜单
    JMenuBar jmb = null;
    //开始游戏
    JMenu jm1 = null;
    JMenuItem jmi1 = null;
//    JMenuItem jmi2 = null;
//    JMenuItem jmi3 = null;
    JMenuItem jmi4 = null;
    JMenuItem jmi5 = null;
    JMenuItem jmi6 = null;

    //定义敌人坦克数n（从Recorder接收）
    int n;

    //定义己方坦克数m（从Recorder接收）
    int m;

    //定义基地是否被破坏（从Recorder接收）
    Boolean b;

    public static void main(String[] args) {
        MyTankGame mtg = new MyTankGame();
    }

    //构造函数
    public MyTankGame(){

        //创建菜单及菜单选项
        jmb = new JMenuBar();
        jm1 = new JMenu("游戏（G）");
        //设置快捷方式
        jm1.setMnemonic('G');
        jmi1 = new JMenuItem("开始新游戏（N）");
        jmi1.setMnemonic('N');
//        jmi2 = new JMenuItem("暂停（P）");
//        jmi2.setMnemonic('P');
//        jmi3 = new JMenuItem("开始（S）");
//        jmi3.setMnemonic('S');
        jmi4 = new JMenuItem("保存并退出游戏(E)");
        jmi4.setMnemonic('E');
        jmi5 = new JMenuItem("存盘退出游戏(C)");
        jmi5.setMnemonic('C');
        jmi6 = new JMenuItem("继续上局游戏(T)");
        jmi6.setMnemonic('T');

        //注册监听
        //对jmi1响应
        jmi1.addActionListener(this);
        jmi1.setActionCommand("newGame");
//        //对jmi2响应
//        jmi2.addActionListener(this);
//        jmi2.setActionCommand("pauseGame");
//        //对jmi3响应
//        jmi3.addActionListener(this);
//        jmi3.setActionCommand("startGame");
        //对jmi4响应
        jmi4.addActionListener(this);
        jmi4.setActionCommand("exit");
        //对jmi5响应
        jmi5.addActionListener(this);
        jmi5.setActionCommand("saveExit");
        //对jmi6响应
        jmi6.addActionListener(this);
        jmi6.setActionCommand("conGame");

        jm1.add(jmi1);
//        jm1.add(jmi2);
//        jm1.add(jmi3);
        jm1.add(jmi4);
        jm1.add(jmi5);
        jm1.add(jmi6);
        jmb.add(jm1);


        msp = new MyStartPanel();
        Thread t = new Thread(msp);
        t.start();


        this.setJMenuBar(jmb);
        this.add(msp);
        this.setSize(1000,670);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //对用户不同的点击作出不同的处理
        if(arg0.getActionCommand().equals("newGame")){
            //创建战场面板
            mp = new MyPanel("newGame");
            //启动mp线程
            Thread t = new Thread(mp);
            t.start();
            //先删除开始面板
            this.remove(msp);
            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示，刷新JFrame
            this.setVisible(true);
            //得到敌人坦克数n
            Thread t2 = new Thread(this);
            t2.start();


        }else if(arg0.getActionCommand().equals("exit")){
            //用户点击了退出系统菜单
            //保存击毁敌人数量
            Recorder.keepRecording();
            System.exit(0);
        }else if(arg0.getActionCommand().equals("saveExit")){
            Recorder rd = new Recorder();
            rd.setEts(mp.ets);
            //保存击毁敌人的数量和敌人的坐标
            rd.keepRecAndEnemyTank();

            //退出
            System.exit(0);
        }else if(arg0.getActionCommand().equals("conGame")){
            //创建上局战场面板
            mp = new MyPanel("conGame");
            //启动mp线程
            Thread t = new Thread(mp);
            t.start();
            //删除开始面板
            this.remove(msp);
            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示刷新JFrame
            this.setVisible(true);
            //开始判断游戏是否结束
            Thread t2 = new Thread(this);
            t2.start();
        }

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //不断从Recorder得到信息
            n = Recorder.getEnNum();
            m = Recorder.getMyLife();
            b = Recorder.getBaseIsLive();
            if (n == 0 || m ==0 || !b) {
                //删除游戏面板
                this.remove(mp);
                //加入游戏结束面板
                mgop = new MyGameOverPanel();
                this.add(mgop);
                Thread t1 = new Thread(mgop);
                t1.start();
                //显示，刷新JFrame
                this.setVisible(true);
                break;
            }
        }



    }
}


