import com.alibaba.fastjson.JSON;
import com.wind.user.UserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class)
public class MongoTest {



//    @Test
//    public void findAll_Base(){
//        List<BeijingData> beijingData = dataDaoBeijing.findAll(BeijingData.class);
//        List<ChinaData> chinaData = dataDaoChina.findAll(ChinaData.class);
//        List<GlobeData> globeData = dataDaoGlobe.findAll(GlobeData.class);
//        System.out.println("BeijingData - " + beijingData.size());
//        System.out.println("chinaData - " + chinaData.size());
//        System.out.println("globeData - " + globeData.size());
//    }

//    @Test
//    public void group_sun() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Aggregation aggregation1 = Aggregation.newAggregation(
//                Aggregation.limit(100),
//                Aggregation.group("city").push("confirmed").as("confirmed")
//                );
//        AggregationResults<BasicDBObject> outputTypeCount1 =
//                mongoTemplate.aggregate(aggregation1, "Beijing", BasicDBObject.class);
//        List<BasicDBObject> result = new ArrayList<>();
//        List<Integer> radar = new ArrayList<Integer>();
//        radar.add(1);
//        radar.add(2);
//        radar.add(3);
//        radar.add(4);
//        radar.add(5);
//        for (Iterator<BasicDBObject> iterator = outputTypeCount1.iterator(); iterator.hasNext(); ) {
//            BasicDBObject obj = iterator.next();
//            obj.put("radar",radar);
//            Object tem = obj.get("confirmed.value");
////            DataBean dataBean = dbObject2Bean(obj,new DataBean());
////            System.out.println(JSON.toJSONString(obj));
////            System.out.println(dataBean.toString());
//            result.add(obj);
////            String name = obj.getString("_id");
////            Object confirmed = obj.get("value");
////            System.out.println(name);
////            System.out.println(confirmed.toString());
////            DataBlock dataBlock = new DataBlock(obj.get("_id"),obj.,radar);
//        }
//        System.out.println(JSON.toJSONString(result));
//    }

    @Test
    public void writeOutFile() {
//        System.out.println("用户的当前工作目录:"+System.getProperty("user.dir"));
        String parent = new File(System.getProperty("user.dir")).getParent();
//        System.out.println(parent);
        // 提供FileWriter的对象，用于数据写出
        // 第二个参数，是否追加
        FileWriter fw = null;
        try {
            // 提供File类对象，指明写出文件
            File file = new File(parent + "\\mapreduce-thrift-service\\input\\" + "hello1.txt");
            if(file.exists())
                file.delete();
            fw = new FileWriter(file, true);
            // 写出
            fw.write("I have a dream!\n");
            fw.write("You need to have a dream too!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
