package test1;

//子弹类
public class Shot implements Runnable{

    int x;
    int y;
    int direct;
    int speed =1;
    //是否还活着
    boolean isLive = true;


    public Shot(int x, int y, int direct){
        this.x = x;
        this.y = y;
        this.direct = direct;
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

    @Override
    public void run() {

        while (true){
            //子弹跑动
            //每隔8毫秒让子弹跑一个像素点
            try{
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct){
                //子弹向上飞
                case 0:
                    y = y - speed;
                    break;
                //子弹向右飞
                case 1:
                    x = x + speed;
                    break;
                //子弹向下飞
                case 2:
                    y = y + speed;
                    break;
                //子弹向左飞
                case 3:
                    x = x - speed;
                    break;
            }

            //判断该子弹是否碰到边缘
            if(x < 0 || x > 800 || y < 0 || y >600){
                this.isLive = false;
                break;
            }
        }


    }
}
