package test1;

//定义一个加入图片的类
import java.net.URL;

public class AddImage {

    public URL getImage(String xx) {
        URL url = this.getClass().getClassLoader().getResource(xx);
        return url;
    }
}
