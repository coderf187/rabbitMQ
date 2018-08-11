## spring使用方式：

在包com.bsx.mq.spring 下面的几个类是在springboot环境下使用的例子

## rabbitmq 安装
1.[下载erlang-20.3-1.el7.centos.x86_64.rpm](https://github.com/rabbitmq/erlang-rpm/releases)

2.[下载mq](https://packagecloud.io/rabbitmq/rabbitmq-server?filter=rpms)

```
#添加hosts文件,三台机器都需要配置
#10.10.164.27 10-10-164-27
#10.10.189.151 10-10-189-151
#10.10.169.54 10-10-169-54
 
 
 
#找到对应目录执行,三台机器都需要执行以下命令
[root@10-10-164-27 ~]# yum install erlang-20.3-1.el7.centos.x86_64.rpm   //安装erlang
[root@10-10-164-27 ~]# yum install rabbitmq-server-3.7.4-1.el7.noarch.rpm  //安装rabbitmq
 
cluster01:
#随意找其中任意一台启动rabbitmq 服务
[root@10-10-164-27 ~]# systemctl start rabbitmq-server  或者 service rabbitmq-server start
#copy默认目录下 .erlang.cookie到其他另外两台机器上同样目录下,如果此时另外两台目录有 .erlang.cookie 文件，删除后复制过去即可（注意此文件操作权限）
[root@10-10-164-27 ~]# cd /var/lib/rabbitmq/
[root@10-10-164-27 ~]# scp .erlang.cookie root@10.10.189.151:/var/lib/rabbitmq/
[root@10-10-164-27 ~]# scp .erlang.cookie root@10.10.169.54:/var/lib/rabbitmq/
#先停止一次应用
[root@10-10-164-27 ~]# rabbitmqctl stop_app
#重新启动一次应用,默认是硬盘节点
[root@10-10-164-27 ~]# rabbitmqctl start_app
#查看集群状态信息，此时集群只有一个节点，运行的也只有一个节点，集群名称为：rabbitmq-cluster
[root@10-10-164-27 ~]# rabbitmqctl cluster_status
Cluster status of node rabbit@10-10-164-27 ...
[{nodes,[{disc,['rabbit@10-10-164-27']}]},
 {running_nodes,['rabbit@10-10-164-27']},
 {cluster_name,<<"rabbitmq-cluster">>},
 {partitions,[]},
 {alarms,[{'rabbit@10-10-164-27',[]}]}]
 
cluster02:
#copy完后修改另外两台机器此文件权限
[root@10-10-189-151 ~]# cd /var/lib/rabbitmq/
#修改权限
[root@10-10-189-151 rabbitmq]# chmod 400 .erlang.cookie
#启动rabbitmq 服务
[root@10-10-189-151 rabbitmq]# systemctl start rabbitmq-server
#停止rabbmq进程
[root@10-10-189-151 rabbitmq]# rabbitmqctl stop_app
#如果停止过程中出现：Error when reading /var/lib/rabbitmq/.erlang.cookie: eacces
执行如下：
#chown rabbitmq:rabbitmq .erlang.cookie
#chmod 400 .erlang.cookie
#ps -ef|grep rabbitmq  //查询当前是否有rabbitmq 进程
#kill -9 PID // 杀掉
 
#加入集群 并且改节点为内存节点
[root@10-10-189-151 rabbitmq]# rabbitmqctl join_cluster rabbit@10-10-164-27 --ram
[root@10-10-189-151 rabbitmq]# rabbitmqctl start_app
 
cluster03:
[root@10.10.169.54 ~]# cd /var/lib/rabbitmq/
#修改权限
[root@10.10.169.54 rabbitmq]# chmod 400 .erlang.cookie
[root@10.10.169.54 rabbitmq]# systemctl start rabbitmq-server
#停止rabbmq进程
[root@10.10.169.54 rabbitmq]# rabbitmqctl stop_app
#如果停止过程中出现：Error when reading /var/lib/rabbitmq/.erlang.cookie: eacces
执行如下：
#chown rabbitmq:rabbitmq .erlang.cookie
#chmod 400 .erlang.cookie
#ps -ef|grep rabbitmq  //查询当前是否有rabbitmq 进程
#kill -9 PID // 杀掉
 
#加入集群 并且改节点为内存节点
[root@10.10.169.54 rabbitmq]# rabbitmqctl join_cluster rabbit@10-10-164-27 --ram
[root@10.10.169.54 rabbitmq]# rabbitmqctl start_app
 
 
至此：普通模式的集群搭建完毕
 
在三台机器中任意一台机器执行：查询集群状态 三台节点都在运行
[root@10.10.169.54 rabbitmq]# rabbitmqctl cluster_status
 
Cluster status of node rabbit@10-10-169-54 ...
[{nodes,[{disc,['rabbit@10-10-164-27']},
         {ram,['rabbit@10-10-189-151','rabbit@10-10-169-54']}]},
 {running_nodes,['rabbit@10-10-189-151','rabbit@10-10-164-27',
                 'rabbit@10-10-169-54']},
 {cluster_name,<<"rabbitmq-cluster">>},
 {partitions,[]},
 {alarms,[{'rabbit@10-10-189-151',[]},
          {'rabbit@10-10-164-27',[]},
          {'rabbit@10-10-169-54',[]}]}]
 
在任意一台执行：
rabbitmqctl set_policy ha-all "^" '{"ha-mode":"all"}'
rabbitmq集群即由普通模式变为镜像模式
```


