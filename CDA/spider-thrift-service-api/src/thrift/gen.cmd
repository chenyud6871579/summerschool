@echo off
echo gen Python Thrift program file
thrift --gen py out ../ spider.thrift
thrift --gen java -out ../main/java spider.thrift