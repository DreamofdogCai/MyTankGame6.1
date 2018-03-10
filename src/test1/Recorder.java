/**
 * 作者：肖杨
 * 时间：2018.3.1
 * 功能：记录类，同时也可以保存玩家的设置
 */
package test1;

import java.io.*;
import java.util.Vector;

public class Recorder {

    //记录每关有多少敌人
    private static int enNum = 6;
    //设置我有多少可用的人
    private static int myLife = 1;
    //记录总共消灭了多少敌人
    private static int allEnNum = 0;
    //记录基地是否被破坏
    private static boolean baseIsLive = true;
    //从文件中恢复记录点
    static Vector<Node> nodes = new Vector<Node>();


    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;



    public void setBr(BufferedReader br) {
        Recorder.br = br;
    }

    public Vector<EnemyTank> getEts() {
        return ets;
    }

    public void setEts(Vector<EnemyTank> ets) {
        this.ets = ets;
    }

    public static boolean getBaseIsLive() {
        return baseIsLive;
    }

    public static void setBaseIsLive(boolean baseIsLive) {
        Recorder.baseIsLive = baseIsLive;
    }

    private Vector<EnemyTank> ets = new Vector<EnemyTank>();


    //完成读取记录点任务
    public Vector<Node> getNodesAndEnNums(){
        try{
            fr = new FileReader("D:\\myJavaDemo\\MyTankGame6.0\\src\\坦克游戏存盘.txt");
            br = new BufferedReader(fr);
            //读取第一行
            String n = "";
            n = br.readLine();
            allEnNum = Integer.parseInt(n);
            while((n = br.readLine()) != null){
                String []xyzn = n.split(" ");
                    Node node = new Node(Integer.parseInt(xyzn[0]),Integer.parseInt(xyzn[1]),Integer.parseInt(xyzn[2]),Integer.parseInt(xyzn[3]));
                    nodes.add(node);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //后打开先关闭
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    //保存击毁敌人的数量和敌人坦克坐标、方向/新加入保存剩余敌人数量
    public void keepRecAndEnemyTank(){
        try{

            //创建
            fw = new FileWriter("D:\\myJavaDemo\\MyTankGame6.0\\src\\坦克游戏存盘.txt");
            bw = new BufferedWriter(fw);
            //写入第一排数据
            bw.write(allEnNum + "\r\n");
            //保存当前活着的敌人坦克的坐标和方向
            for(int i = 0;i < ets.size();i++){

                //取出第一个坦克
                EnemyTank et = ets.get(i);

                if(et.isLive){

                    //活的保存
                    String recode = et.x + " " + et.y + " " + et.direct + " " + enNum;

                    //写入
                    bw.write(recode + "\r\n");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //后开先关闭
            try {
                bw.close();
                fw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //从文件中读取，记录
    public static void getRecording(){
        try{
            fr = new FileReader("D:\\myJavaDemo\\MyTankGame6.0\\src\\坦克游戏存盘.txt");
            br = new BufferedReader(fr);
            String n = br.readLine();
            allEnNum = Integer.parseInt(n);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //后打开先关闭
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //把玩家击毁敌人坦克数量保存到文件中
    public static void keepRecording(){
        try{

            //创建
            fw = new FileWriter("D:\\myJavaDemo\\MyTankGame6.0\\src\\坦克游戏存盘.txt");
            bw = new BufferedWriter(fw);

            bw.write(allEnNum + "\r\n");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //后开先关闭
            try {
                bw.close();
                fw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static  int  getEnNum() {
        return enNum;
    }

    public static void setEnNum(int enNum) {
        Recorder.enNum = enNum;
    }

    public static int  getMyLife() {
        return myLife;
    }

    public static void setMyLife(int myLife) {
        Recorder.myLife = myLife;
    }

    public static int getAllEnNum() {
        return allEnNum;
    }

    public static void setAllEnNum(int allEnNum) {
        Recorder.allEnNum = allEnNum;
    }

    //减少敌人数
    public static void reduceEnNum(){
        enNum--;
    }

    //生命减少数
    public static void reduceMyLife(){
        myLife--;
    }

    //消灭敌人
    public static void addEnNumRec(){
        allEnNum++;
    }

}
