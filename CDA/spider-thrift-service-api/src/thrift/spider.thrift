# 声明 Java 的包名
namespace java com.wind.thrift.spider

# 声明 Python 的包名
namespace py spider.api


service SpiderService{
    void runSpider();
}