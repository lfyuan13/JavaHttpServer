JavaHttpServer
==============

a servlet container like tomcat and others, which can run the web application under this project

1. Conntector和Container关系
2. Conntector维护一个HttpProcessor池，负责处理每一个到达的请求
3. HttpProcessor 则交给container的servlet处理
4. Container里的处理使用Pipeline技术，即chain 
5. Lifecycle生命周期的管理，容器具有层次性，这里使用观察者模式，同时交由专门的管理。
6. Logger日志管理

==========
待修补：
httpRequest 解析参数还没有完全，而且应该改为流解析；
容器的层次结构有待完善；
类加载器的单独实现；
session管理。
