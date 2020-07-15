@echo off
echo gen Python Thrift program file
thrift --gen py out ../ message.thrift
thrift --gen java out ../../message-thrift-service-api/src/main/java message.thrift