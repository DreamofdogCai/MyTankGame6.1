package test1;
//坦克类
public class Tank {

    //表示Tank的横坐标
    int x = 0;

    //表示Tank的纵坐标
    int y = 0;

    //坦克方向
    //0表示上，1表示右，2表示下，3表示左
    int direct = 0;

    //坦克的速度
    int speed = 3;

    //Tank的颜色
    int color;

    //Tank是否存活
    boolean isLive = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y){

        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
