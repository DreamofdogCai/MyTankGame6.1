package test1;

import java.util.Vector;

//我的坦克
public class Hero extends Tank {

    //子弹
//    Shot s = null;

    //定义石块集合
    Vector<Stone> stones = new Vector<Stone>();

    //定义墙集合
    Vector<Wall> walls = new Vector<Wall>();

    //子弹多来几颗
    Vector<Shot> ss = new Vector<Shot>();
    Shot s = null;


    //我的坦克的起始位置
    public Hero(int x, int y){

        super(x,y);

    }

    public void setHeroDirect(int direct){
        this.direct = direct;
    }

    //接收Panel里的石块和墙
    public void setStoneAndWall(Vector<Stone> stones,Vector<Wall> walls){
        this.stones = stones;
        this.walls = walls;
    }

    //开火
    public void shotEnemy(Hero hero) {
        if(hero.isLive){
            switch (this.direct) {
                //向上
                case 0:
                    //创建一颗子弹
                    s = new Shot(x + 8, y, 0);
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
        }
        //启动子弹线程
        Thread t = new Thread(s);
        t.start();
    }

    //判断是否碰到石块
    public boolean isTouchStone() {
        boolean b = false;
        //取出己方坦克
        //取出所有石头
        for (int j = 0; j < stones.size();j++) {
            Stone stone = stones.get(j);
            //如果坦克的方向是向上
            if (this.direct == 0) {
                if (this.x >= stone.x - 2 && this.x <= stone.x + 20 && this.y >= stone.y - 2 && this.y <= stone.y + 23) {
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
        //取出己方坦克
        //取出所有墙
        for (int j = 0; j < walls.size();j++) {
            Wall wall = walls.get(j);
            //如果坦克的方向是向上
            if (this.direct == 0) {
                if (this.x >= wall.x - 2 && this.x <= wall.x + 20 && this.y >= wall.y - 2 && this.y <= wall.y + 23) {
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

    //坦克向上移动
    public void moveUp(){
        if(y > 0 && !isTouchStone() && !isTouchWall()){
        y = y - speed;
        }
    }
    //坦克向右移动
    public void moveRight(){
        if(x < 750 && !isTouchStone() && !isTouchWall()){
        x = x + speed;
        }
    }
    //坦克向下移动
    public  void moveDown(){
        if(y < 570 && !isTouchStone() && !isTouchWall()){
        y = y + speed;

        }
    }
    //坦克向左移动
    public void moveLeft(){
        if(x > 0 && !isTouchStone() && !isTouchWall()){
        x = x - speed;
        }
    }
}
