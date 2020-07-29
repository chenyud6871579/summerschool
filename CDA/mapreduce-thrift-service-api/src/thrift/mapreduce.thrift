# package name
namespace java com.wind.service.thrift.windmr

# statement user_service's interface
service WindMRService{
    void getLocalData();
    void runMapReduce();
}