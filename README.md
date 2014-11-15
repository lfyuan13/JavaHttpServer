JavaHttpServer
==============

a servlet container like tomcat and others, which can run the web application under this project

config.xml ： it is the configure file, just like a web.xml
the web-app is in com.test

now we have implemented:
(1) connector (HttpProcessor pool to implement the multi-connection)
(2) container(pipeline and the scheme)
(3) request resolve
(4) config servlet
