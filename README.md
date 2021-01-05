# 课设- 利用Socket通信实现停机/复话处理
停机/复话业务处理流程如下：首先生成业务订单，然后调用相应接口执行停机/复话施工操作，在施工结果后，进行业务完工处理。
本课题要求依据上述需求设计并实现停机/复话业务处理流程。

项目描述：
客户端：先建立客户端和服务器的连接：生成停机复话请求，将请求作为IO流填充到IO对象中，利用Socket提供的gIS和gOS方法输入需操作的号码和操作，发送到服务器
服务器：
1.应用Socket进程通信技术编写模拟停机/复话的服务器程序

2.用一个端口实例化ServerSocket对象，通过该端口监听客户端发来的连接请求 

3.ServerSocket的accept方法监听连接请求

4.利用accept方法返回的客户端的Socket对象，读写操作：显示请求内容：号码和操作类型

5.返回0（成功）/1（失败）给客户端

