package test1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

//我的面板
public class MyPanel extends JPanel implements KeyListener,Runnable {


    //定义石块集合
    Vector<Stone> stones = new Vector<Stone>();

    //定义无敌墙集合
    Vector<Wall> walls = new Vector<Wall>();

    //定义敌人的坦克组
    Vector<EnemyTank> ets = new Vector<EnemyTank>();

    //定义上局的敌人坦克组
    Vector<Node> nodes = new Vector<Node>();

    //定义炸弹集合
    Vector<Bomb> bombs = new Vector<Bomb>();

    //newBase对象
    Base base = new Base(284,520);


    //定义一个我的Tank
    Hero hero = null;

    //敌方坦克个数
    int enSize = 6;

    //实例化加入图片的对象
    AddImage add = new AddImage();

    //定义6张图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;



    //定义变量保存当前坦克速度和子弹速度
    int tankSpeedSave = 0;
    int shotSpeedSave = 0;

    //构造函数
    public MyPanel(String flag) {

        //恢复记录
        Recorder.getRecording();

        hero = new Hero(190, 500);

        //给hero传入Panel中石头和墙
        hero.setStoneAndWall(stones,walls);

        //保存坦克速度
        tankSpeedSave = hero.speed;

        if(flag.equals("newGame")) {
            //初始化敌人的Tank
            for (int i = 0; i < enSize; i++) {
                //创建一辆敌人的Tank
                EnemyTank et = new EnemyTank(50 + i * 120, 20);
                et.setColor(0);
                et.setDirect(2);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //将MyPanel的石头向量交给该敌人坦克
                et.setStones(stones);
                //将MyPanel的石头向量交给该敌人坦克
                et.setWall(walls);
                //启动敌人的坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加一颗子弹
                //开火
                Shot s = new Shot(et.x + 10, et.y + 30, 2);

                //保存子弹速度
                shotSpeedSave = s.speed;

                //加入给敌人坦克
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();

                //加入
                ets.add(et);


            }
        }else {
            //得到记录信息
            nodes = new Recorder().getNodesAndEnNums();
            //初始化敌人的Tank
            for (int i = 0; i < nodes.size(); i++) {
                //取出Node中的敌人坦克信息
                Node node = nodes.get(i);
                //创建一辆敌人的Tank
                EnemyTank et = new EnemyTank(node.etx, node.ety);
                et.setColor(0);
                et.setDirect(node.etDirect);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //将MyPanel的敌人坦克向量交给该敌人坦克
                et.setEts(ets);
                //将MyPanel的石头向量交给该敌人坦克
                et.setStones(stones);
                //将MyPanel的石头向量交给该敌人坦克
                et.setWall(walls);

                //启动敌人的坦克
                Thread t = new Thread(et);
                t.start();
                //给敌人坦克添加一颗子弹
                //开火
                Shot s = new Shot(et.x + 10, et.y + 30, 2);

                //保存子弹速度
                shotSpeedSave = s.speed;

                //加入给敌人坦克
                et.ss.add(s);
                Thread t2 = new Thread(s);
                t2.start();

                //加入
                ets.add(et);

                Recorder.setEnNum(node.etNum);

            }
        }


        try {
            image1 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb1.jpg"));
            image2 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb2.jpg"));
            image3 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb3.jpg"));
            image4 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb4.jpg"));
            image5 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb5.jpg"));
            image6 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\bomb6.jpg"));
            image7 = ImageIO.read(new File("D:\\myJavaDemo\\MyTankGame6.0\\src\\兔子 - 副本.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        //初始化图片
//        image1 = (new ImageIcon(add.getImage("bomb1.jpg"))).getImage();
//        image2 = (new ImageIcon(add.getImage("bomb2.jpg"))).getImage();
//        image3 = (new ImageIcon(add.getImage("bomb3.jpg"))).getImage();
//        image4 = (new ImageIcon(add.getImage("bomb4.jpg"))).getImage();
//        image5 = (new ImageIcon(add.getImage("bomb5.jpg"))).getImage();
//        image6 = (new ImageIcon(add.getImage("bomb6.jpg"))).getImage();

        //播放开战声音
        AePlayWav aePlayWav = new AePlayWav("D:\\格式转换文件\\坦克大战.wav");
        aePlayWav.start();
//        while(true){
//            //每隔两分二十秒重播
//            AePlayWav aePlayWav1 = new AePlayWav("D:\\格式转换文件\\坦克大战2.wav");
//            aePlayWav1.start();
//            try{
//                Thread.sleep(140000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }



        //创建石块
        //第一堵墙
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(0, 0 + i*20);
            stones.add(stone);
        }
        //第二堵墙
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(100, 0 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(120, 0 + i*20);
            stones.add(stone);
        }
        //第三堵墙
        for(int i = 0;i < 3;i++){
            Stone stone = new Stone(220, 40 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 3;i++){
            Stone stone = new Stone(240, 40 + i*20);
            stones.add(stone);
        }
        //第四堵墙
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(220,  140+ i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(240,  140+ i*20);
            stones.add(stone);
        }
        //第五堵墙（Copy第三）
        for(int i = 0;i < 3;i++){
            Stone stone = new Stone(340, 40 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 3;i++){
            Stone stone = new Stone(360, 40 + i*20);
            stones.add(stone);
        }
        //第六堵墙（Copy第四）
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(340,  140+ i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(360,  140+ i*20);
            stones.add(stone);
        }
        //第七堵墙（Copy第二）
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(460, 0 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(480, 0 + i*20);
            stones.add(stone);
        }
        //第八堵墙（Copy第二）
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(580, 0 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(600, 0 + i*20);
            stones.add(stone);
        }
        //第九堵墙（Copy第二）
        for(int i = 0;i < 5;i++) {
            Stone stone = new Stone(580, 60 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 5;i++) {
            Stone stone = new Stone(600, 60 + i*20);
            stones.add(stone);
        }
        //下方第一堵墙（Copy第一）
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(0, 440 + i*20);
            stones.add(stone);
        }
        //下方第二堵墙（Copy第二）
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(100, 440 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(120, 440 + i*20);
            stones.add(stone);
        }
        //下方第三堵墙（Copy第三）
        for(int i = 0;i < 6;i++){
            Stone stone = new Stone(220, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 6;i++){
            Stone stone = new Stone(240, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 2;i++){
            Stone stone = new Stone(260, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 2;i++){
            Stone stone = new Stone(280, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 2;i++){
            Stone stone = new Stone(300, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 2;i++){
            Stone stone = new Stone(320, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 6;i++){
            Stone stone = new Stone(340, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 6;i++){
            Stone stone = new Stone(360, 480 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(260, 580 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(280, 580 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++){
            Stone stone = new Stone(300, 580 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 1;i++) {
            Stone stone = new Stone(320, 580 + i * 20);
            stones.add(stone);
        }
        //下方第四堵墙（Copy第二）
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(460, 440 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(480, 440 + i*20);
            stones.add(stone);
        }
        //下方第五堵墙（Copy第二）
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(580, 440 + i*20);
            stones.add(stone);
        }
        for(int i = 0;i < 8;i++) {
            Stone stone = new Stone(600, 440 + i*20);
            stones.add(stone);
        }

        //设置无敌墙
        for(int i = 0;i < 24;i++) {
            Wall wall = new Wall(80 + i * 20, 300);
            walls.add(wall);
        }






    }

    //画出提示信息
    public void showINfo(Graphics g) {

        //画出提示信息坦克(该坦克不参与战斗)
        this.drawTank(830,150,g,0,0);
        g.setColor(Color.black);
        Font myFont = new Font("华文新魏", Font.BOLD, 20);
        g.setFont(myFont);
        g.drawString(Recorder.getEnNum()+"",900,170);
        this.drawTank(830,210,g,0,1);
        g.setColor(Color.black);
        Font myFont1 = new Font("华文新魏", Font.BOLD, 20);
        g.setFont(myFont1);
        g.drawString(Recorder.getMyLife()+"",900,230);

        //画出玩家的总成绩
        g.setColor(Color.black);
        Font f = new Font("宋体",Font.BOLD,20);
        g.setFont(f);
        g.drawString("您的总成绩",830,280);

        this.drawTank(830,300,g,0,0);

        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnNum() + "",900,320);
    }


    //重写paint函数
    public void paint(Graphics g){

        super.paint(g);
        g.fillRect(0,0,800,600);

        //画出提示信息坦克
        this.showINfo(g);

        //画出自己的坦克
        if(hero.isLive) {
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
        }
        //从ss中取出每颗子弹并绘制
        for(int i = 0; i < hero.ss.size();i++) {

            Shot myShot = hero.ss.get(i);
            //画出子弹，画出一颗子弹
            if (myShot != null && myShot.isLive == true) {
                g.setColor(Color.orange);
                g.fillOval(myShot.x, myShot.y, 4, 4);
            }
            //如果子弹消失
            if(myShot.isLive == false){
                //从ss中删除该子弹
                hero.ss.remove(myShot);
            }
        }

        //画出炸弹
        for(int i = 0;i < bombs.size();i++){
            //取出炸弹
            Bomb b = bombs.get(i);

            if(b.life > 11){
                g.drawImage(image1,b.x - 5,b.y,35,35,this);
            }else if(b.life > 9){
                g.drawImage(image2,b.x - 5,b.y,35,35,this);
            }else if(b.life > 7){
                g.drawImage(image3,b.x - 5,b.y,35,35,this);
            }else if(b.life > 5){
                g.drawImage(image4,b.x - 5,b.y,35,35,this);
            }else if(b.life > 3){
                g.drawImage(image5,b.x - 5,b.y,35,35,this);
            }else{
                g.drawImage(image6,b.x - 5,b.y,35,35,this);
            }

            //让b的生命值减小
            b.lifeDown();
            //如果炸弹生命值为零，炸弹消失
            if(b.life == 0){
                bombs.remove(b);
            }
        }


        //画出敌人的坦克
        for(int i = 0;i < ets.size();i++){
            EnemyTank et = ets.get(i);
            if(et.isLive) {
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
            }
            //从ss（敌人的子弹）中取出每颗子弹并绘制
            for(int j = 0;j < et.ss.size();j++) {
                Shot etShot = et.ss.get(j);
                //画出子弹，画出一颗子弹
                if (etShot != null && etShot.isLive == true) {
                    g.fillOval(etShot.x, etShot.y, 4, 4);
                }else{
                    //如果敌人的坦克死亡就从Vector去掉子弹
                    et.ss.remove(etShot);
                }
                //如果子弹消失
                if (etShot.isLive == false) {
                    //从ss中删除该子弹
                    et.ss.remove(etShot);
                }
            }
        }


        //画出石块
        for(int i = 0;i < stones.size();i++){
            Stone stone = stones.get(i);
            if(stone.isLive){
                this.drawStone(stone.getX(),stone.getY(),g);
            }
            //如果石块消失
            if(stone.isLive == false){
                //从stones中删除该石块
                stones.remove(stone);
            }
        }

        //画出墙
        for(int i =0;i < walls.size();i++){
            Wall wall = walls.get(i);
            this.drawWall(wall.getX(),wall.getY(),g);

        }

        //画出基地
        this.drawBase(base.getX(),base.getY(),g);
    }


    //判断我的是否击中敌方坦克或者敌方子弹
    public void hitEnemyTank() {
        for (int i = 0; i < hero.ss.size(); i++) {
            //取出己方子弹
            Shot myShot = hero.ss.get(i);
            //判断子弹是否有效
            if (myShot.isLive) {
                //取出每个敌人坦克，与它判断
                for (int j = 0; j < ets.size(); j++) {
                    //取出坦克
                    EnemyTank et = ets.get(j);
                    //判断坦克是否活着
                    if (et.isLive) {
                        this.hitEnemyTank(myShot, et);
                    }
                    for (int k = 0; k < et.ss.size(); k++) {
                        //取出敌方子弹
                        Shot etShot = et.ss.get(k);
                        //判断敌方子弹是否有效
                        if (etShot.isLive) {
                            this.hitShot(myShot, etShot);
                        }
                    }
                }
            }
        }
    }

    //敌人子弹是否击中我
    public void hitMe(){
        //取出每一个敌人的坦克
        for(int i = 0;i < ets.size();i++){
            //取出敌人坦克
            EnemyTank et = ets.get(i);
            //取出每一颗子弹
            for(int j = 0;j < et.ss.size();j++){
                //取出子弹
                Shot etShot = et.ss.get(j);
                if(hero.isLive) {
                    //判断是否击中
                    this.hitMyTank(etShot, hero);
                }
            }
        }
    }

    //子弹是否击中基地
    public void hitBase(){
        //取出每一个敌人的坦克
        for(int i = 0;i < ets.size();i++){
            //取出敌人坦克
            EnemyTank et = ets.get(i);
            //取出每一颗子弹
            for(int j = 0;j < et.ss.size();j++){
                //取出子弹
                Shot etShot = et.ss.get(j);
                    //判断是否击中
                    this.ishitBase(etShot,base);
            }
        }
        //取出每一个己方坦克子弹
        for(int i = 0;i < hero.ss.size();i++){
            Shot heroShot = hero.ss.get(i);
                //判断是否击中基地
                this.ishitBase(heroShot,base);
        }
    }

    //写一个函数专门判断子弹是否击中敌人坦克
    public void hitEnemyTank(Shot s,Tank et){

        //判断该坦克的方向
        switch (et.direct){
            //如果敌人坦克的方向是上或者是下
            case 0:
            case 2:if(s.x > et.x && s.x < et.x + 20 && s.y > et.y && s.y < et.y + 30){
                //击中
                //子弹死亡
                s.isLive = false;
                //敌人坦克死亡
                et.isLive = false;
                //减少敌人数量
                Recorder.reduceEnNum();
                //增加我的记录
                Recorder.addEnNumRec();
                //创建一颗炸弹，放入Vector
                Bomb b = new Bomb(et.x,et.y);
                bombs.add(b);
            }break;
            case 1:
            case 3:if(s.x > et.x && s.x < et.x + 30 && s.y > et.y && s.y < et.y + 20 ){
                //击中
                //子弹死亡
                s.isLive = false;
                //敌人坦克死亡
                et.isLive = false;
                //减少敌人数量
                Recorder.reduceEnNum();
                //增加我的记录
                Recorder.addEnNumRec();
                //创建一颗炸弹，放入Vector
                Bomb b = new Bomb(et.x,et.y);
                bombs.add(b);
            }break;
        }

    }
    //写一个函数专门判断子弹是否击中己方坦克
    public void hitMyTank(Shot s,Tank et){

        //判断该坦克的方向
        switch (et.direct){
            //如果敌人坦克的方向是上或者是下
            case 0:
            case 2:if(s.x > et.x && s.x < et.x + 20 && s.y > et.y && s.y < et.y + 30){
                //击中
                //子弹死亡
                s.isLive = false;
                //敌人坦克死亡
                Recorder.reduceMyLife();
                et.isLive = false;
                //创建一颗炸弹，放入Vector
                Bomb b = new Bomb(et.x,et.y);
                bombs.add(b);
            }break;
            case 1:
            case 3:if(s.x > et.x && s.x < et.x + 30 && s.y > et.y && s.y < et.y + 20 ){
                //击中
                //子弹死亡
                s.isLive = false;
                //敌人坦克死亡
                Recorder.reduceMyLife();
                et.isLive = false;
                //创建一颗炸弹，放入Vector
                Bomb b = new Bomb(et.x,et.y);
                bombs.add(b);
            }break;
        }

    }

    //写一个函数专门判断己方子弹是否击中敌方子弹
    public void hitShot(Shot s,Shot ss){
        if(s.x >= ss.x - 1 &&  s.x <= ss.x + 1 && s.y >= ss.y - 1 &&  s.y <= ss.y + 1){
            //击中
            //子弹死亡
            s.isLive = false;
            ss.isLive = false;
        }
    }

    //判断己方子弹和敌方子弹是否击中基地
    public void ishitBase(Shot s, Base base){
        if(s.x >= base.x - 2 && s.x <= base.x + 32 && s.y >= base.y -2 && s.y <= base.y + 32){
            s.isLive = false;
            base.isLive = false;
            Recorder.setBaseIsLive(false);
        }
    }



    //画出石块的函数
    public void drawStone (int x,int y,Graphics g){
                g.setColor(Color.red);
                g.fill3DRect(x, y, 20, 20, true);
                g.setColor(Color.lightGray);
                g.drawLine(x, y + 9, x + 19, y + 9);
                g.drawLine(x + 9, y + 9, x + 9, y + 19);


    }

    //画出基地的函数
    public void drawBase(int x,int y,Graphics g){
        g.drawImage(image7,x,y,30,60,this);
    }

    //判断子弹是否击中石块
    public  void hitStone(){
        //取出每一块石块
        for (int i = 0; i < stones.size(); i++) {
            Stone stone = stones.get(i);
            for (int j = 0; j < ets.size(); j++) {
                EnemyTank et = ets.get(j);
                for (int k = 0; k < et.ss.size(); k++) {
                    Shot etShot = et.ss.get(k);
                    //取出敌人子弹
                    if (etShot.x > stone.x - 2 && etShot.x < stone.x + 20 && etShot.y > stone.y - 2 && etShot.y < stone.y + 20) {
                        stone.isLive = false;
                        etShot.isLive = false;
                    }
                }
            }
            for (int j = 0;j < hero.ss.size();j++) {
                Shot heroShot = hero.ss.get(j);
                //判断石块是否被己方子弹击中
                if (heroShot.x > stone.x - 2 && heroShot.x < stone.x + 20 && heroShot.y > stone.y - 2 && heroShot.y < stone.y + 20) {
                    heroShot.isLive = false;
                    stone.isLive = false;
                }
            }
        }
    }

    //画出墙的函数
    public void drawWall (int x,int y,Graphics g){
        g.setColor(Color.white);
        g.fill3DRect(x, y, 20, 20, true);
        g.setColor(Color.lightGray);
        g.drawLine(x, y + 9, x + 19, y + 9);
        g.drawLine(x + 9, y + 9, x + 9, y + 19);

    }

    //判断子弹是否击中墙
    public  void hitWall(){
        //取出每一块墙
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            for (int j = 0; j < ets.size(); j++) {
                EnemyTank et = ets.get(j);
                for (int k = 0; k < et.ss.size(); k++) {
                    Shot etShot = et.ss.get(k);
                    //取出敌人子弹
                    if (etShot.x > wall.x - 2 && etShot.x < wall.x + 20 && etShot.y > wall.y - 2 && etShot.y < wall.y + 20) {
                        etShot.isLive = false;
                    }
                }
            }
            for (int j = 0;j < hero.ss.size();j++) {
                Shot heroShot = hero.ss.get(j);
                //判断石块是否被己方子弹击中
                if (heroShot.x > wall.x - 2 && heroShot.x < wall.x + 20 && heroShot.y > wall.y - 2 && heroShot.y < wall.y + 20) {
                    heroShot.isLive = false;
                }
            }
        }
    }


    //画出坦克的函数
    public void drawTank(int x,int y,Graphics g,int direct,int type){

        //判断是什么类型的坦克
        switch (type){
            case 0: g.setColor(Color.cyan);break;
            case 1: g.setColor(Color.yellow);break;
        }

        //判断方向
        switch (direct){
            //向上
            case 0:
                //画出我的Tank(到时再封装成一个函数)
                //1.画出左边的矩形
                g.fill3DRect(x,y,5,30,true);
                //2.画出右边的矩形
                g.fill3DRect(x + 15,y,5,30,true);
                //3.画出中间矩形
                g.fillRect(x + 5,y + 5,10,20);
                //修改颜色
                g.setColor(Color.orange);
                //4.画出边框轮廓
                g.draw3DRect(x + 6,y + 10,7,10,true);
                //5.画出炮筒
                g.draw3DRect(x + 9,y,1, 10,true);
                //6.画出链条颜色
                for(int i = 1;i < 8;i++){
                g.draw3DRect(x - 1,y + 4 * i - 2,5,1,true);
                g.draw3DRect(x + 15,y + 4 * i - 2,5,1,true);
                }
                break;
            //向右
            case 1:
                //1.画出Tank的左履带
                g.fill3DRect(x,y,30,5,true);
                //2.画出Tank的右履带
                g.fill3DRect(x,y + 15,30,5,true);
                //3.画出中间矩形
                g.fillRect(x + 5,y + 5,20,10);
                //修改颜色
                g.setColor(Color.orange);
                //4.画出边框轮廓
                g.draw3DRect(x + 10,y + 6,10,7,true);
                //5.画出炮筒
                g.draw3DRect(x + 20,y + 9,10,1,true);
                //6.画出链条颜色
                for(int i = 1;i < 8;i++){
                    g.draw3DRect(x + 4 * i - 2,y - 1,1,5,true);
                    g.draw3DRect(x + 4 * i - 2,y + 15,1,5,true);
                }
                break;
            //向下
            case 2:
                //1.画出Tank的右履带
                g.fill3DRect(x,y,5,30,true);
                //2.画出Tank的左履带
                g.fill3DRect(x + 15,y,5,30,true);
                //3.画出中间矩形
                g.fillRect(x + 5,y + 5,10,20);
                //修改颜色
                g.setColor(Color.orange);
                //4.画出边框轮廓
                g.draw3DRect(x + 6,y + 10,7,10,true);
                //5.画出炮筒
                g.draw3DRect(x + 9,y + 20,1, 10,true);
                //6.画出链条颜色
                for(int i = 1;i < 8;i++){
                    g.draw3DRect(x - 1,y + 4 * i - 2,5,1,true);
                    g.draw3DRect(x + 15,y + 4 * i - 2,5,1,true);
                }
                break;
            //向左
            case 3:
                //1.画出Tank的右履带
                g.fill3DRect(x,y,30,5,true);
                //2.画出Tank的左履带
                g.fill3DRect(x,y + 15,30,5,true);
                //3.画出中间矩形
                g.fillRect(x + 5,y + 5,20,10);
                //修改颜色
                g.setColor(Color.orange);
                //4.画出边框轮廓
                g.draw3DRect(x + 10,y + 6,10,7,true);
                //5.画出炮筒
                g.draw3DRect(x,y + 9,10,1,true);
                //6.画出链条颜色
                for(int i = 1;i < 8;i++){
                    g.draw3DRect(x + 4 * i - 2,y - 1,1,5,true);
                    g.draw3DRect(x + 4 * i - 2,y + 15,1,5,true);
                }
                break;
        }

    }





    //按键上下左右

    @Override//键的一个值被输出
    public void keyTyped(KeyEvent e) {

    }

    @Override//键被按下
    public void keyPressed(KeyEvent e) {
        //设置我的坦克方向
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            //向下
            this.hero.setDirect(2);
            this.hero.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            //向上
            this.hero.setDirect(0);
            this.hero.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            //向右
            this.hero.setDirect(1);
            this.hero.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            //向左
            this.hero.setDirect(3);
            this.hero.moveLeft();
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE ){
            //判断玩家是否按下空格

            //开火
            if(this.hero.ss.size() < 5) {
                this.hero.shotEnemy(hero);
            }
        }


        if(e.getKeyCode() == KeyEvent.VK_P){
            //判断玩家是否按下P键暂停
            //将己方坦克速度设为0
            hero.speed = 0;
            //将敌方坦克速度设为0
            for(int i = 0;i < ets.size();i++){
                EnemyTank et = ets.get(i);
                et.speed = 0;
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            //判断玩家是否按下S键开始
            hero.speed = tankSpeedSave;
            for(int i = 0;i < ets.size();i++){
                EnemyTank et = ets.get(i);
                et.speed = tankSpeedSave;
            }
        }
        //调用repaint函数，来重绘界面(必须重绘panel)
        this.repaint();
    }

    @Override//键被释放
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //每隔5毫秒去重绘子弹
        while (true){
            try{
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.hitStone();
            this.hitWall();
            //是否击中敌人
            this.hitEnemyTank();
            //是否击中自己
            this.hitMe();
            //判断是否击中基地
            this.hitBase();
            //重绘
            this.repaint();
        }
    }
}
