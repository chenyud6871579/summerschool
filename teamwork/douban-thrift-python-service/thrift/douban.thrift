# 声明 Java 的包名
namespace java com.test.thrift.douban
# 声明 Python 的包名
namespace py douban.api

# 定义服务，给出接口名称
service DoubanService{
    # run spider
    void getInfo(1:i32 num, 2:string ip, 3:string db, 4:string table);
}