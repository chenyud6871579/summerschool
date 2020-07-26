package com.wind.user.service;

import com.wind.service.thrift.data.DataBlock;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.data.UserService;
import com.wind.user.dao.BeijingDao;
import com.wind.user.dao.DataDao;
import com.wind.user.pojo.BeijingData;
import com.wind.user.pojo.ChinaData;
import com.wind.user.pojo.GlobeData;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService.Iface {

//    private Class target;

    @Resource
    private DataDao<BeijingData> dataDaoBeijing;
    @Resource
    private BeijingDao beijingDao;
//    @Resource
//    private DataDao<ChinaData> dataDaoChina;
//    @Resource
//    private DataDao<GlobeData> dataDaoGlobe;

    @Override
    public List<DataBlock> getDataBlock(DataType type) throws TException {
        System.out.println("get in");
        if(type == DataType.BEIJING){
//            List<BeijingData> beijingData = beijingDao.getAll();
            List<BeijingData> beijingData = dataDaoBeijing.findAll(BeijingData.class);
            for (BeijingData item : beijingData){
                System.out.printf(item.toString());
            }
        }
        return null;
    }
}
