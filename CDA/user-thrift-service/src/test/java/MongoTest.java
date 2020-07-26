import com.wind.user.UserApplication;
import com.wind.user.dao.DataDao;
import com.wind.user.pojo.BeijingData;
import com.wind.user.pojo.ChinaData;
import com.wind.user.pojo.GlobeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class)
//public class MongoTest {

//    @Resource
//    private DataDao<BeijingData> dataDaoBeijing;
//    @Resource
//    private DataDao<ChinaData> dataDaoChina;
//    @Resource
//    private DataDao<GlobeData> dataDaoGlobe;


//    @Test
//    public void findAll_Base(){
//        List<BeijingData> beijingData = dataDaoBeijing.findAll(BeijingData.class);
//        List<ChinaData> chinaData = dataDaoChina.findAll(ChinaData.class);
//        List<GlobeData> globeData = dataDaoGlobe.findAll(GlobeData.class);
//        System.out.println("BeijingData - " + beijingData.size());
//        System.out.println("chinaData - " + chinaData.size());
//        System.out.println("globeData - " + globeData.size());
//    }
//}
