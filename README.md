# TaoTao Shopping Site  :convenience_store:

https://blog.csdn.net/LeonAurora/article/details/81028375

### Description   

TaoTao online shopping site is a comprehensive B2C platform, like Amazon and JingDong. Users can browse goods and make an order, they can also take part in some activities like flash sale on black friday.

Merchants can access the management system and add new goods or deal with the orders.

Customer service people can answer users' question and complaints.

### Function Description

- Background Management System: Manage the products, orders, categories and product specifications.

- Portal System: Users can register the account and log in the system, browse the products, make an order.

- Order System: Merhants can query the orders, change the orders' state and handle the orders.

- Search System: Integrate solr to provide full-text search.

- Single sign on system: Provide certificates for users among different systems and query users' information.

### System architecture

Distribute architecture:

  &emsp; Design different modules by their function.

Advantages:

- Use interfaces to do the communication, reduced the coupling among different modules.

- Differnet teams can focus on different functions.

- Easy to add new functions, only need to add a new module and invoke other interfaces.

- Can be deployed in a distribute environemt.

Disadvantages:

- The communication between two modules use the http request, add the workload.

System sketch map:

![image](https://github.com/ZehuaWang/TaoTao_ShoppingSite/blob/master/pic/SystemArch.png)

### Technology Stack

Technology and Framework:

- Spring SpringMVC Mybatis

- JSP JSTL jQuery EasyUI KindEditor CSS+DIV

- Redis (Cache Server)

- Solr (Search Server)

- httpClient (Invoke the service)

- Mysql (Database Server)

- Nginx (Web Server)

Tools and environment:

- Eclipse 4.5.0(Mars) and Maven Plugin

- Maven 3.3.3

- Tomcat 7.0.53

- JDK 1.7

- Mysql 5.6

- Nginx 1.8.0

- Redis 3.0.0

- Win7 and CentOS

### Overview and Future Improvement

The homepage of Taotao:

![image](https://github.com/ZehuaWang/TaoTao_ShoppingSite/blob/master/pic/portal.png)

This project adopt a Service-orinted architecture, the overview of service interface is shown below:

![image](https://github.com/ZehuaWang/TaoTao_ShoppingSite/blob/master/pic/system.png)

Future improvements and todo list:

- [ ] Adopt to dubbo framework, use socket to communicate between different modules. 
