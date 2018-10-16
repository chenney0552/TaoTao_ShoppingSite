# TaoTao Shopping Site  :convenience_store:

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

### Technology architecture

Distribute architecture:

  &emsp; Design different modules by their function.

Advantages:

- Use interfaces to do the communication, reduced the coupling among different modules.

- Differnet teams can focus on different functions.

- Easy to add new functions, only need to add a new module and invoke other interfaces.

- Can be deployed in a distribute environemt.

Disadvantages:

- The communication between two modules use the http request, add the workload.
  
![image](https://github.com/ZehuaWang/TaoTao_ShoppingSite/blob/master/pic/picture.txt)


