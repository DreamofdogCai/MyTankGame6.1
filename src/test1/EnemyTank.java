package test1;

import java.util.Vector;

//敌人的Tank，把敌人Tank做成线程类
public class EnemyTank extends Tank implements Runnable{

    public EnemyTank(int x, int y){
        super(x,y);
    }

    //定义一个向量，可以访问MyPanel上所有敌人坦克
    Vector<EnemyTank> ets = new Vector<EnemyTank>();

    //定义一个向量，可以访问MyPanel上所有石头
    Vector<Stone> stones = new Vector<Stone>();

    //定义一个向量，可以访问MyPanel上所有墙
    Vector<Wall> walls = new Vector<Wall>();


    //定义一个向量，存放敌人子弹
    Vector<Shot> ss = new Vector<Shot>();
    Shot s = null;
    //敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹死亡后

    //得到MyPanel的敌人坦克向量
    public void setEts(Vector<EnemyTank>vv){
        this.ets = vv;
    }


    //得到MyPanel的石头向量
    public void setStones(Vector<Stone>vv){
        this.stones = vv;
    }

    //得到MyPanel的墙向量
    public void setWall(Vector<Wall>vv){
        this.walls = vv;
    }

    //判断是否碰到别的敌人坦克
    public boolean isTouchOtherEnemy() {
        boolean b = false;

        switch (this.direct){
            case 0:
                //我的坦克向上
                //取出所有的敌人坦克
                for(int i = 0;i < ets.size();i++){
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //如果不是自己
                    if(et != this){
                        //如果敌人的方向是向上或者是向下
                        if(et.direct == 0 || et.direct == 2){
                            if(this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y +30){
                                return true;
                            }
                            if(this.x + 20 >=et.x && this.x + 20 <= et.x + 20 && this.y >=et.y && this.y <= et.y + 30){
                                return true;
                            }
                        }
                        //如果敌人坦克的方向是向右或向左
                        if(et.direct == 3 || et.direct == 1){
                            if(this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y +20){
                                return true;
                            }
                            if(this.x + 20 >=et.x && this.x + 20 <= et.x + 30 && this.y >=et.y && this.y <= et.y + 20){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                //坦克向右
                for(int i = 0;i < ets.size();i++){
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //如果不是自己
                    if(et != this) {
                        //如果敌人的方向是向下或者是向上
                        if (et.direct == 0 || et.direct == 2) {
                            if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
                                return true;
                            }
                            if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y + 20 >= et.y && this.y + 20 <= et.y + 30) {
                                return true;
                            }
                        }
                        //如果敌人坦克的方向是向右或向左
                        if (et.direct == 3 || et.direct == 1) {
                            if (this.x + 30>= et.x && this.x  + 30 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
                                return true;
                            }
                            if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y + 20 >= et.y && this.y + 20 <= et.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                //坦克向下
                for(int i = 0;i < ets.size();i++){
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //如果不是自己
                    if(et != this) {
                        //如果敌人的方向是向下或者是向上
                        if (et.direct == 0 || et.direct == 2) {
                            if (this.x >= et.x && this.x <= et.x + 20 && this.y +30 >= et.y && this.y + 30 <= et.y + 30) {
                                return true;
                            }
                            if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y + 30 >= et.y && this.y + 30 <= et.y + 30) {
                                return true;
                            }
                        }
                        //如果敌人坦克的方向是向右或向左
                        if (et.direct == 3 || et.direct == 1) {
                            if (this.x >= et.x && this.x <= et.x + 30 && this.y + 30 >= et.y && this.y + 30<= et.y + 20) {
                                return true;
                            }
                            if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y + 30 >= et.y && this.y + 30<= et.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                //向左
                for(int i = 0;i < ets.size();i++){
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //如果不是自己
                    if(et != this) {
                        //如果敌人的方向是向下或者是向上
                        if (et.direct == 0 || et.direct == 2) {
                            if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
                                return true;
                            }
                            if (this.x >= et.x && this.x <= et.x + 20 && this.y + 20 >= et.y && this.y + 20 <= et.y + 30) {
                                return true;
                            }
                        }
                        //如果敌人坦克的方向是向右或向左
                        if (et.direct == 3 || et.direct == 1) {
                            if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
                                return true;
                            }
                            if (this.x >= et.x && this.x <= et.x + 30 && this.y + 20 >= et.y && this.y + 20 <= et.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }

        return b;
    }

    //判断是否碰到石块
    public boolean isTouchStone() {
        boolean b = false;
            //取出所有石头
            for (int i = 0; i < stones.size(); i++) {
                Stone stone = stones.get(i);
                //如果坦克的方向是向上
                if (this.direct == 0) {
                    if (this.x >= stone.x - 2  && this.x <= stone.x + 20 && this.y >= stone.y - 2 && this.y <= stone.y + 23) {
                        return true;
                    }
                    if (this.x + 20 >= stone.x - 2 && this.x + 20 <= stone.x + 20 && this.y >= stone.y - 2 && this.y <= stone.y + 23) {
                        return true;
                    }
                }
                //如果坦克方向是向下
                if (this.direct == 2) {
                    if (this.x >= stone.x - 2 && this.x <= stone.x + 20 && this.y + 30 >= stone.y - 2 && this.y + 30 <= stone.y + 20) {
                        return true;
                    }
                    if (this.x + 20 >= stone.x - 2 && this.x + 20 <= stone.x + 20 && this.y + 30 >= stone.y - 2 && this.y + 30 <= stone.y + 20) {
                        return true;
                    }
                }
                //如果坦克方向是向右
                if (this.direct == 1) {
                    if (this.x + 30 >= stone.x - 2 && this.x + 30 <= stone.x + 20 && this.y >= stone.y - 2 && this.y <= stone.y + 20) {
                        return true;
                    }
                    if (this.x + 30 >= stone.x - 2 && this.x + 30 <= stone.x + 20 && this.y + 20 >= stone.y - 2 && this.y + 20 <= stone.y + 20) {
                        return true;
                    }
                }
                //如果坦克方向是向左
                if (this.direct == 3) {
                    if (this.x >= stone.x - 2 && this.x <= stone.x + 22 && this.y >= stone.y - 2 && this.y <= stone.y + 20) {
                        return true;
                    }
                    if (this.x >= stone.x - 2 && this.x <= stone.x + 22 && this.y + 20 >= stone.y - 2 && this.y + 20 <= stone.y + 20) {
                        return true;
                    }
                }

            }


        return b;
    }

    //判断是否碰到墙
    public boolean isTouchWall() {
        boolean b = false;
        //取出所有墙
        for (int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);
            //如果坦克的方向是向上
            if (this.direct == 0) {
                if (this.x >= wall.x - 2  && this.x <= wall.x + 20 && this.y >= wall.y - 2 && this.y <= wall.y + 23) {
                    return true;
                }
                if (this.x + 20 >= wall.x - 2 && this.x + 20 <= wall.x + 20 && this.y >= wall.y - 2 && this.y <= wall.y + 23) {
                    return true;
                }
            }
            //如果坦克方向是向下
            if (this.direct == 2) {
                if (this.x >= wall.x - 2 && this.x <= wall.x + 20 && this.y + 30 >= wall.y - 2 && this.y + 30 <= wall.y + 20) {
                    return true;
                }
                if (this.x + 20 >= wall.x - 2 && this.x + 20 <= wall.x + 20 && this.y + 30 >= wall.y - 2 && this.y + 30 <= wall.y + 20) {
                    return true;
                }
            }
            //如果坦克方向是向右
            if (this.direct == 1) {
                if (this.x + 30 >= wall.x - 2 && this.x + 30 <= wall.x + 20 && this.y >= wall.y - 2 && this.y <= wall.y + 20) {
                    return true;
                }
                if (this.x + 30 >= wall.x - 2 && this.x + 30 <= wall.x + 20 && this.y + 20 >= wall.y - 2 && this.y + 20 <= wall.y + 20) {
                    return true;
                }
            }
            //如果坦克方向是向左
            if (this.direct == 3) {
                if (this.x >= wall.x - 2 && this.x <= wall.x + 22 && this.y >= wall.y - 2 && this.y <= wall.y + 20) {
                    return true;
                }
                if (this.x >= wall.x - 2 && this.x <= wall.x + 22 && this.y + 20 >= wall.y - 2 && this.y + 20 <= wall.y + 20) {
                    return true;
                }
            }

        }


        return b;
    }



    @Override
    public void run() {
        while (true){
            switch (direct){
                //敌方坦克向上跑
                case 0:
                    for(int i = 0;i < 30;i++) {
                        //限定坦克跑动区域
                        if(y > 0 && !this.isTouchOtherEnemy() && !this.isTouchStone() && !isTouchWall()) {
                            //敌方坦克跑动
                            y = y - speed;
                        }
                            //每隔50毫秒让坦克跑一个像素点
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                    break;
                //敌方坦克向右跑
                case 1:
                    for(int i = 0;i < 30;i++) {
                    if(x < 750 && !this.isTouchOtherEnemy() && !this.isTouchStone() && !isTouchWall()) {
                        //敌方坦克跑动
                        x = x + speed;
                    }
                        //每隔50毫秒让坦克跑一个像素点
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                    break;
                //敌方坦克向下跑
                case 2:
                    for(int i = 0;i < 30;i++) {
                    if(y < 570 && !this.isTouchOtherEnemy() && !this.isTouchStone() && !isTouchWall()) {
                        //敌方坦克跑动
                        y = y + speed;
                    }
                        //每隔50毫秒让坦克跑一个像素点
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                    break;
                //敌方坦克向左跑
                case 3:
                    for(int i = 0;i < 30;i++) {
                    if(x > 0 && !this.isTouchOtherEnemy() && !this.isTouchStone() && !isTouchWall()) {
                        //敌方坦克跑动
                        x = x - speed;
                    }
                        //每隔50毫秒让坦克跑一个像素点
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                    break;
            }

                    if(isLive){
                        if(ss.size() < 4){
                            Shot s = null;
                            switch (direct) {
                                //向上
                                case 0:
                                    //创建一颗子弹
                                    s = new Shot(x + 8,y, 0);
                                    //把子弹加入向量
                                    ss.add(s);
                                    break;
                                //向右
                                case 1:
                                    s = new Shot(x + 30, y + 8, 1);
                                    ss.add(s);
                                    break;
                                //向下
                                case 2:
                                    s = new Shot(x + 8, y + 30, 2);
                                    ss.add(s);
                                    break;
                                //向左
                                case 3:
                                    s = new Shot(x, y + 8, 3);
                                   ss.add(s);
                                    break;
                            }
                            //启动子弹线程
                            Thread t = new Thread(s);
                            t.start();
                        }
                    }

            //让坦克随机产生于一个新的方向
            this.direct = (int)(Math.random() * 4);

            //判断敌人Tank是否死亡
            if(this.isLive == false){
                //让坦克死亡后，退出线程
                break;
            }

        }

    }
}

