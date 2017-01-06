#类加载器Classloader

####类加载器classloader分三种:
1. 引导类加载器bootstrap classloader：用来加载java的核心类库，比如位于/jre/lib目录下的vm.jar，core.jar。由原生代码实现，不继承自java.lang.Classloader。
2. 扩展类加载器extends classloader：用来加载java的扩展库，一般位于/jre/lib/ext，或者通过java.ext.dirs这个系统属性指定的路径下的代码。JVM实现提供一个扩展目录，该classloader在该目录中查找加载类。这个类加载器由sun.misc.Launcher$ExtClassLoader实现。
3. 系统类加载器system classloader：根据classpath（java.class.path）加载类，一般来说Java应用的类都是由它来完成加载，可通过getSystemClassLoader()来获取。这个类加载器由sun.misc.Launcher$AppClassLoader实现。

引导类加载器<-扩展类加载器<-系统类加载器

####classloader的代理模式
classloader在自己尝试加载某个类的字节码定义时，会先代理给其父classloader，由父classloader来尝试加载。但是对于web容器中类加载过程则相反。

####线程上下文加载器
类java.lang.Thread中的方法getContextClassLoader()和setContextClassLoader(ClassLoader cl)用来获取和设置线程上下文context的classloader。如果没有设置线程的classloader，则继承其父线程的classloader。
Java程序运行的初始线程的上下文classloader是system classloader。