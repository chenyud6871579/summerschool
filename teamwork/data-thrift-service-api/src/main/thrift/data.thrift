# 声明 Java 的包名
namespace java com.test.thrift.data

# 定义服务，给出接口名称
struct Movie{
    1:string movieName,
    2:i32 commentNumber,
    3:double movieStars
}

service DataService{
    list<Movie> getMovieList();
}