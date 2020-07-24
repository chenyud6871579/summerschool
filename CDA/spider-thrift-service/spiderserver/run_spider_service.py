from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from config import run_spider

class SpiderHandler:
    def runSpider(self):
        run_spider()

if __name__=='__main__':

    # 4. create a Thrift Server's handle function
    handler = SpiderHandler()
    # from message.api import MessageService
    processor = SpiderHandler.Processor(handler)

    # 1. create a Thrift Server's ServerSocket
    server_socket = TSocket.TServerSocket(host='127.0.0.1', port=9090)
    # server_socket = TSocket.TServerSocket(None, port=9090)
    # 2. create a Thrift Server's Transport --- 帧传输方式
    transport_factory = TTransport.TFramedTransportFactory()
    # 3. create a Thrift Server's Protocal  --- 二进制传输协议
    protocal_factory = TBinaryProtocol.TBinaryProtocolFactory()

    # 5. create a Thrift Server             谁处理(who)、监听那个端口(where)、传输方式(how)、传输协议(protocal)
    thrift_server = TServer.TSimpleServer(processor, server_socket, transport_factory, protocal_factory)
    # 6. run Thrift Server, waiting for Client's access
    print("Python Thrift Server run...")
    thrift_server.serve()
    print("Python Thrift Server exit!")
