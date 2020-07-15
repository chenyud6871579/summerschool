@echo off
echo gen Python Thrift program file
thrift.exe --gen py -out ../ douban.thrift
thrift.exe --gen java -out ../../douban-thrift-service-api/src/main/java douban.thrift