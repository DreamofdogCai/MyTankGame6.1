package test1;

public class Bomb {

    //炸弹的坐标
    int x,y;
    //炸弹的生命值
    int life = 12;
    boolean isLive = true;
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    //炸弹的生命值减小
    public  void lifeDown(){
        if(life > 0){
            life--;
        }else{
            this.isLive = false;
        }
    }
}
