/**
 * 作者：肖杨
 * 时间：2018.2.9
 * 功能：构建阻挡坦克的石块
 */

package test1;

public class Stone {
    int x,y;
    boolean isLive =true;

    public Stone(int x, int y){
        this.x = x;
        this.y = y;
    }

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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }







}
