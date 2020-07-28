//import com.alibaba.fastjson.JSON;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.wind.user.UserApplication;
////import com.wind.user.dao.DataDao;
////import com.wind.user.pojo.BeijingData;
////import com.wind.user.pojo.ChinaData;
////import com.wind.user.pojo.GlobeData;
//import org.apache.commons.beanutils.BeanUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//class DataBean{
//    private String name;
//    private List<Integer> confirmed;
//    private List<Integer> radar;
//
//    public DataBean(String name, List<Integer> confirmed) {
//        this.name = name;
//        this.confirmed = confirmed;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Integer> getConfirmed() {
//        return confirmed;
//    }
//
//    public void setConfirmed(List<Integer> confirmed) {
//        this.confirmed = confirmed;
//    }
//
//    public List<Integer> getRadar() {
//        return radar;
//    }
//
//    public void setRadar(List<Integer> radar) {
//        this.radar = radar;
//    }
//
//    public DataBean() {
//    }
//
//    public DataBean(String name, List<Integer> confirmed, List<Integer> radar) {
//        this.name = name;
//        this.confirmed = confirmed;
//        this.radar = radar;
//    }
//
//    @Override
//    public String toString() {
//        return "DataBean{" +
//                "name='" + name + '\'' +
//                ", confirmed=" + confirmed +
//                ", radar=" + radar +
//                '}';
//    }
//}
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class)
//public class MongoTest {
//
////    @Resource
////    private DataDao<BeijingData> dataDaoBeijing;
////    @Resource
////    private DataDao<ChinaData> dataDaoChina;
////    @Resource
////    private DataDao<GlobeData> dataDaoGlobe;
//    @Resource
//    private MongoTemplate mongoTemplate;
//
//
//
////    @Test
////    public void findAll_Base(){
////        List<BeijingData> beijingData = dataDaoBeijing.findAll(BeijingData.class);
////        List<ChinaData> chinaData = dataDaoChina.findAll(ChinaData.class);
////        List<GlobeData> globeData = dataDaoGlobe.findAll(GlobeData.class);
////        System.out.println("BeijingData - " + beijingData.size());
////        System.out.println("chinaData - " + chinaData.size());
////        System.out.println("globeData - " + globeData.size());
////    }
//
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
//
//
//    public static <T> T dbObject2Bean(DBObject dbObject, T bean) throws IllegalAccessException,
//            InvocationTargetException, NoSuchMethodException {
//        if (bean == null) {
//            return null;
//        }
//        Field[] fields = bean.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            String varName = field.getName();
//            Object object = dbObject.get(varName);
//            if (object != null) {
////                BeanUtils.setProperty(bean, varName, object);
//            }
//        }
//        return bean;
//    }
//
//}
