from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport
from douban.api import DoubanService
from douban.spider import get_info


class DouBanServiceHandler:
    def getInfo(self, num, ip, db, table):
        # 测试用，还未进行函数调用测试
        # print("num", num)
        # print("ip", ip)
        # print("db", db)
        # print("table", table)
        get_info(num, ip, db, table)


if __name__ == "__main__":
    # 1. create a Thrift Server's handle function
    handler = DouBanServiceHandler()
    # from message.api import MessageService
    processor = DoubanService.Processor(handler)
    # 2. create a Thrift Server's ServerSocket
    server_socket = TSocket.TServerSocket(host='127.0.0.1', port=9090)
    # server_socket = TSocket.TServerSocket(None, port=9090)
    # 3. create a Thrift Server's Transport --- 帧传输方式
    transport_factory = TTransport.TFramedTransportFactory()
    # 4. create a Thrift Server's Protocal  --- 二进制传输协议
    protocal_factory = TBinaryProtocol.TBinaryProtocolFactory()
    # 5. create a Thrift Server             谁处理(who)、监听那个端口(where)、传输方式(how)、传输协议(protocal)
    thrift_server = TServer.TSimpleServer(processor, server_socket, transport_factory, protocal_factory)
    print("Spider Thrift Server run...")
    thrift_server.serve()
    print("Spider Thrift Server exit!")
