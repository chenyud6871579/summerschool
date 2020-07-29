import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
    @Test
    public void fileTest(){
        System.out.println("用户的当前工作目录:"+System.getProperty("user.dir"));
        String parent = new File(System.getProperty("user.dir")).getParent();
        System.out.println(parent);
//        // 提供FileWriter的对象，用于数据写出
//        // 第二个参数，是否追加
//        FileWriter fw = null;
//        try {
//            // 提供File类对象，指明写出文件
//            File file = new File(parent + "\\mapreduce-thrift-service\\input\\" + "hello1.txt");
//            if(file.exists())
//                file.delete();
//            fw = new FileWriter(file, true);
//            // 写出
//            fw.write("I have a dream!\n");
//            fw.write("You need to have a dream too!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(fw != null)
//                    fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
