## 1.本地库找不到

代码：

```shell
[root@hadoop6 bin]# hdfs dfs -mkdir -p /user/zpt/input3
2020-06-26 20:52:50,047 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
```

debug：

```
2020-06-26 20:59:14,392 DEBUG util.Shell: setsid exited with exit code 0
2020-06-26 20:59:14,395 DEBUG conf.Configuration: parsing URL jar:file:/opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar!/core-default.xml
2020-06-26 20:59:14,397 DEBUG conf.Configuration: parsing input stream sun.net.www.protocol.jar.JarURLConnection$JarURLInputStream@5e5792a0
2020-06-26 20:59:14,509 DEBUG conf.Configuration: parsing URL file:/opt/module/hadoop-3.2.1/etc/hadoop/core-site.xml
2020-06-26 20:59:14,510 DEBUG conf.Configuration: parsing input stream java.io.BufferedInputStream@2b552920
2020-06-26 20:59:14,599 DEBUG lib.MutableMetricsFactory: field org.apache.hadoop.metrics2.lib.MutableRate org.apache.hadoop.security.UserGroupInformation$UgiMetrics.loginSuccess with annotation @org.apache.hadoop.metrics2.annotation.Metric(sampleName=Ops, always=false, valueName=Time, about=, interval=10, type=DEFAULT, value=[Rate of successful kerberos logins and latency (milliseconds)])
2020-06-26 20:59:14,607 DEBUG lib.MutableMetricsFactory: field org.apache.hadoop.metrics2.lib.MutableRate org.apache.hadoop.security.UserGroupInformation$UgiMetrics.loginFailure with annotation @org.apache.hadoop.metrics2.annotation.Metric(sampleName=Ops, always=false, valueName=Time, about=, interval=10, type=DEFAULT, value=[Rate of failed kerberos logins and latency (milliseconds)])
2020-06-26 20:59:14,608 DEBUG lib.MutableMetricsFactory: field org.apache.hadoop.metrics2.lib.MutableRate org.apache.hadoop.security.UserGroupInformation$UgiMetrics.getGroups with annotation @org.apache.hadoop.metrics2.annotation.Metric(sampleName=Ops, always=false, valueName=Time, about=, interval=10, type=DEFAULT, value=[GetGroups])
2020-06-26 20:59:14,610 DEBUG lib.MutableMetricsFactory: field private org.apache.hadoop.metrics2.lib.MutableGaugeLong org.apache.hadoop.security.UserGroupInformation$UgiMetrics.renewalFailuresTotal with annotation @org.apache.hadoop.metrics2.annotation.Metric(sampleName=Ops, always=false, valueName=Time, about=, interval=10, type=DEFAULT, value=[Renewal failures since startup])
2020-06-26 20:59:14,610 DEBUG lib.MutableMetricsFactory: field private org.apache.hadoop.metrics2.lib.MutableGaugeInt org.apache.hadoop.security.UserGroupInformation$UgiMetrics.renewalFailures with annotation @org.apache.hadoop.metrics2.annotation.Metric(sampleName=Ops, always=false, valueName=Time, about=, interval=10, type=DEFAULT, value=[Renewal failures since last successful login])
2020-06-26 20:59:14,616 DEBUG impl.MetricsSystemImpl: UgiMetrics, User and group related metrics
2020-06-26 20:59:14,643 DEBUG security.SecurityUtil: Setting hadoop.security.token.service.use_ip to true
2020-06-26 20:59:14,661 DEBUG security.Groups:  Creating new Groups object
2020-06-26 20:59:14,662 DEBUG util.NativeCodeLoader: Trying to load the custom-built native-hadoop library...
2020-06-26 20:59:14,663 DEBUG util.NativeCodeLoader: Failed to load native-hadoop with error: java.lang.UnsatisfiedLinkError: /opt/module/hadoop-3.2.1/lib/native/libhadoop.so.1.0.0: /lib64/libc.so.6: version `GLIBC_2.14' not found (required by /opt/module/hadoop-3.2.1/lib/native/libhadoop.so.1.0.0)
2020-06-26 20:59:14,663 DEBUG util.NativeCodeLoader: java.library.path=/opt/module/hadoop-3.2.1/lib/native
2020-06-26 20:59:14,663 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
2020-06-26 20:59:14,664 DEBUG util.PerformanceAdvisory: Falling back to shell based
2020-06-26 20:59:14,668 DEBUG security.JniBasedUnixGroupsMappingWithFallback: Group mapping impl=org.apache.hadoop.security.ShellBasedUnixGroupsMapping
2020-06-26 20:59:14,831 DEBUG security.Groups: Group mapping impl=org.apache.hadoop.security.JniBasedUnixGroupsMappingWithFallback; cacheTimeout=300000; warningDeltaMs=5000
2020-06-26 20:59:14,915 DEBUG core.Tracer: sampler.classes = ; loaded no samplers
2020-06-26 20:59:14,919 DEBUG core.Tracer: span.receiver.classes = ; loaded no span receivers
2020-06-26 20:59:14,953 DEBUG security.UserGroupInformation: hadoop login
2020-06-26 20:59:14,953 DEBUG security.UserGroupInformation: hadoop login commit
2020-06-26 20:59:14,964 DEBUG security.UserGroupInformation: using local user:UnixPrincipal: root
2020-06-26 20:59:14,964 DEBUG security.UserGroupInformation: Using user: "UnixPrincipal: root" with name root
2020-06-26 20:59:14,964 DEBUG security.UserGroupInformation: User entry: "root"
2020-06-26 20:59:14,964 DEBUG security.UserGroupInformation: UGI loginUser:root (auth:SIMPLE)
2020-06-26 20:59:14,965 DEBUG core.Tracer: sampler.classes = ; loaded no samplers
2020-06-26 20:59:14,965 DEBUG core.Tracer: span.receiver.classes = ; loaded no span receivers
2020-06-26 20:59:14,965 DEBUG fs.FileSystem: Loading filesystems
2020-06-26 20:59:14,988 DEBUG fs.FileSystem: file:// = class org.apache.hadoop.fs.LocalFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar
2020-06-26 20:59:14,995 DEBUG fs.FileSystem: viewfs:// = class org.apache.hadoop.fs.viewfs.ViewFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar
2020-06-26 20:59:15,005 DEBUG fs.FileSystem: har:// = class org.apache.hadoop.fs.HarFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar
2020-06-26 20:59:15,007 DEBUG fs.FileSystem: http:// = class org.apache.hadoop.fs.http.HttpFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar
2020-06-26 20:59:15,009 DEBUG fs.FileSystem: https:// = class org.apache.hadoop.fs.http.HttpsFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/common/hadoop-common-3.2.1.jar
2020-06-26 20:59:15,018 DEBUG fs.FileSystem: hdfs:// = class org.apache.hadoop.hdfs.DistributedFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/hdfs/hadoop-hdfs-client-3.2.1.jar
2020-06-26 20:59:15,030 DEBUG fs.FileSystem: webhdfs:// = class org.apache.hadoop.hdfs.web.WebHdfsFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/hdfs/hadoop-hdfs-client-3.2.1.jar
2020-06-26 20:59:15,040 DEBUG fs.FileSystem: swebhdfs:// = class org.apache.hadoop.hdfs.web.SWebHdfsFileSystem from /opt/module/hadoop-3.2.1/share/hadoop/hdfs/hadoop-hdfs-client-3.2.1.jar
2020-06-26 20:59:15,040 DEBUG fs.FileSystem: Looking for FS supporting hdfs
2020-06-26 20:59:15,041 DEBUG fs.FileSystem: looking for configuration option fs.hdfs.impl
2020-06-26 20:59:15,094 DEBUG fs.FileSystem: Looking in service filesystems for implementation class
2020-06-26 20:59:15,094 DEBUG fs.FileSystem: FS for hdfs is class org.apache.hadoop.hdfs.DistributedFileSystem
2020-06-26 20:59:15,137 DEBUG impl.DfsClientConf: dfs.client.use.legacy.blockreader.local = false
2020-06-26 20:59:15,137 DEBUG impl.DfsClientConf: dfs.client.read.shortcircuit = false
2020-06-26 20:59:15,137 DEBUG impl.DfsClientConf: dfs.client.domain.socket.data.traffic = false
2020-06-26 20:59:15,137 DEBUG impl.DfsClientConf: dfs.domain.socket.path = 
2020-06-26 20:59:15,159 DEBUG hdfs.DFSClient: Sets dfs.client.block.write.replace-datanode-on-failure.min-replication to 0
2020-06-26 20:59:15,174 DEBUG retry.RetryUtils: multipleLinearRandomRetry = null
2020-06-26 20:59:15,211 DEBUG ipc.Server: rpcKind=RPC_PROTOCOL_BUFFER, rpcRequestWrapperClass=class org.apache.hadoop.ipc.ProtobufRpcEngine$RpcProtobufRequest, rpcInvoker=org.apache.hadoop.ipc.ProtobufRpcEngine$Server$ProtoBufRpcInvoker@41d477ed
2020-06-26 20:59:15,227 DEBUG ipc.Client: getting client out of cache: org.apache.hadoop.ipc.Client@43bc63a3
2020-06-26 20:59:15,635 DEBUG util.PerformanceAdvisory: Both short-circuit local reads and UNIX domain socket are disabled.
2020-06-26 20:59:15,649 DEBUG sasl.DataTransferSaslUtil: DataTransferProtocol not using SaslPropertiesResolver, no QOP found in configuration for dfs.data.transfer.protection
2020-06-26 20:59:15,802 DEBUG ipc.Client: The ping interval is 60000 ms.
2020-06-26 20:59:15,804 DEBUG ipc.Client: Connecting to hadoop6/192.168.100.130:9000
2020-06-26 20:59:15,819 DEBUG ipc.Client: IPC Client (6519275) connection to hadoop6/192.168.100.130:9000 from root: starting, having connections 1
2020-06-26 20:59:15,821 DEBUG ipc.Client: IPC Client (6519275) connection to hadoop6/192.168.100.130:9000 from root sending #0 org.apache.hadoop.hdfs.protocol.ClientProtocol.getFileInfo
2020-06-26 20:59:15,832 DEBUG ipc.Client: IPC Client (6519275) connection to hadoop6/192.168.100.130:9000 from root got value #0
2020-06-26 20:59:15,832 DEBUG ipc.ProtobufRpcEngine: Call: getFileInfo took 67ms
2020-06-26 20:59:15,913 DEBUG ipc.Client: stopping client from cache: org.apache.hadoop.ipc.Client@43bc63a3
2020-06-26 20:59:15,913 DEBUG ipc.Client: removing client from cache: org.apache.hadoop.ipc.Client@43bc63a3
2020-06-26 20:59:15,914 DEBUG ipc.Client: stopping actual client because no more references remain: org.apache.hadoop.ipc.Client@43bc63a3
2020-06-26 20:59:15,914 DEBUG ipc.Client: Stopping client
2020-06-26 20:59:15,914 DEBUG ipc.Client: IPC Client (6519275) connection to hadoop6/192.168.100.130:9000 from root: closed
2020-06-26 20:59:15,914 DEBUG ipc.Client: IPC Client (6519275) connection to hadoop6/192.168.100.130:9000 from root: stopped, remaining connections 0
2020-06-26 20:59:15,919 DEBUG util.ShutdownHookManager: Completed shutdown in 0.006 seconds; Timeouts: 0
2020-06-26 20:59:15,950 DEBUG util.ShutdownHookManager: ShutdownHookManger completed shutdown.
```

