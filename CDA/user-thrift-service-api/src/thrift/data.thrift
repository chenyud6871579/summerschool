# package name
namespace java com.wind.service.thrift.data

struct DataBlock{
    1:string name,
    2:list<i32> confirmedList,
    3:list<i32> radarNumber
}

enum DataType{
    BEIJING = 0,
    CHINA = 1,
    GLOBE = 2
}

# statement user_service's interface
service UserService{
    list<DataBlock> getDataBlock(1:DataType type);
    string getData(1:DataType type);
}