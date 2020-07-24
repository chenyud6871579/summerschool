# package name
namespace java com.wind.service.thrift.data

struct DataBlock{
    1:i32 id,
    2:string username,
    3:string password,
    4:string realName,
    5:string mobile,
    6:string email
}

# statement user_service's interface
service UserService{

    # registered
    void registerUser(1:UserInfo userInfo);

    # get user by name
    UserInfo getUserByName(1:string username);

    # get user by id
    UserInfo getUserById(1:i32 id);
}