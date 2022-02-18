# 	 	设计模式DesignPattern(马士兵版)

> 引言：Java架构师必须了解和掌握的23种设计模式

### 1、Singleton-单例模式

#### 1、singleton-单例模式-第一模式

**只需要一个实例**

​	 保证在程序运行期间，内存中仅存在一个对象的实例。这个就是单例模式。单例模式的实现是吧需要创建单例的类的构造器设为private受保护类型的，并提供一个可以获取这个受保护的类的实例对象的方法。

**恶汉模式**

```java
package com.study.singleton;

/**
 * @ClassName: Singleton1.java
 * @author: FULITAO
 * @description:恶汉模式,不管你是否使用这个类的单例，在类加载到内纯种就给你创建这个类的实例对象
 * 恶汉模式创建的实例对象是线程安全的 因为他是JVM保护的
 * @createTime: 2020年10月29日 13:34:00
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
} 
```

**懒汉模式**

 ```java
package com.study.singleton;

/**
 * @ClassName: Singleton2.java
 * @author: FULITAO
 * @description:懒汉模式 ，虽然节约了内存空间但是却带来了线程不安全的因素,在多个线程访问的时候可能会产生创建多个示例的问题
 * @createTime: 2020年10月29日 13:42:00
 */
public class Singleton2 {
    private static Singleton2 INSTANCE;

    private Singleton2() {
    }

    public static Singleton2 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}
 ```

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

看下面这段代码思考在多线程的调用getInstance()方法的时候会有什么问题？

```java
public class Singleton2 {
    private static Singleton2 INSTANCE;

    private Singleton2() {
    }

    public static Singleton2 getINSTANCE() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Singleton2.getINSTANCE().hashCode())
            ).start();
        }
    }
}
```

> 17855515
> 227877002
> 1637331608
> 2054250075

​	可以看得出来在100个线程调用getInstance()方法的时候会造成创建的实例对象不是同一个，这个就是懒汉模式在多线程访问的时候所带来的问题。解决办法是给 **getInstance()**方法加上 **synchronize**关键字上锁。这可以有效的解决懒汉模式在多线程创建示例的时候带来线程不安全的问题，但是又在性能上有所欠缺。每一次获取这个对象的实例都得先判断是否获取这个类的class锁，这样带来性能上很大的消耗是得不偿失的。

```java
package com.study.singleton;

/**
 * @ClassName: Singleton3.java
 * @author: FULITAO
 * @description:单例模式 线程安全的 双重判断写法
 * @createTime: 2020年10月29日 14:05:00
 */
public class Singleton3 {
    private static volatile Singleton3 INSTANCE; //指令重排

    private Singleton3() {

    }

    public static Singleton3 getINSTANCE() {
        //双重判断 减少上锁的代码块范围
        if (INSTANCE == null) {
            synchronized (Singleton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
```

**静态内部类写法**

```java
package com.study.singleton;

/**
 * @ClassName: Singleton4.java
 * @author: FULITAO
 * @description:静态内部类写法
 				JVM保证单例
 				加载外部类的时候不会加载内部类，这个时候就实现了懒加载
 * @createTime: 2020年10月29日 14:10:00
 */
public class Singleton4 {
    private Singleton4() {

    }

    public static class Singleton4Holder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return Singleton4Holder.INSTANCE;
    }
}

```

**Effective Java写法**

```java
package com.study.singleton;

/**
 * @ClassName: Singleton5.java
 * @author: FULITAO
 * @description:不仅可以防止线程安全 还可以防止反序列化
 * @createTime: 2020年10月29日 14:16:00
 */
public enum Singleton5 {
    INSTANCE;
    public void m(){

    }
}
```

### 2、Strategy-策略模式

**策略模式需要定义一个顶层Strategy接口来规定每个策略必须实现的方法,然后每个实现的策略实现了这个接口，当在Spring环境中可以利用BEAN容器来进行策略模式的优化代替工厂模式**

**顶层Strategy类**

```java
public interface FireStrategy {
    void fire();
}
```

**坦克的实现**

```java
public class TankFireStrategy implements FireStrategy {
    @Override
    public String getSampleName() {
        return "tank";
    }

    @Override
    public void fire() {
        System.out.println("坦克开火咯");
    }
}
```

**利用Spring Bean容器进行托管实现工厂功能**

```java
@Compoent
public class FireUseService implements ApplicationContextAware {
    private final static Map<String, FireStrategy> FIRE_STRATEGY_MAP = new ConcurrentHashMap<>();

    public void process(String sampleName) {
        FireStrategy fireStrategy = FIRE_STRATEGY_MAP.get(sampleName);
        if (fireStrategy != null) {
            fireStrategy.fire();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, FireStrategy> beansOfType = applicationContext.getBeansOfType(FireStrategy.class);
        beansOfType.values().forEach(fireStrategy -> FIRE_STRATEGY_MAP.put(fireStrategy.getSampleName(), fireStrategy));
    }
}

```

### 3、工厂方法-FactoryMethod:抽象工厂-AbstractFactory

**简单工厂**

```java
package com.flt.factormethod;
//简单工程代码扩展性较差 每增加一个新的设备都要增加代码
public class SimpleFactory {
    public Car createCar() {
        //before process
        return new Car();
    }

    public Plane createPlane() {
        //before process
        return new Plane();
    }
}

```

​	**工厂方法**

```java
package com.flt.factormethod;
//工厂方法来创建对象
public class CarFactor {
    public Car create() {
        System.out.println("a car create....");
        return new Car();
    }
}

```

**上述都是工厂方法的代码 我们发现这样写的工厂方法每次新增一个产品族都得新建一个类得不偿失接下来介绍抽象工厂**

**现在有以下情况,定义一个人开着车拿着Ak47进行射击，假如这个人是魔法世界的人他会骑着扫把 拿着魔法棒该怎么实现**

```java
package com.flt.abstractfactory;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.go();
        Ak47 ak47 = new Ak47();
        ak47.shoot();
    }
}

```

**假如把它换成魔法族的人上面的代码都得重写这样太麻烦了有没有好一点的办法**

**抽象工厂:**

**定义每个产品族的抽象类**

**武器族:**

```java
package com.flt.abstractfactory;

public abstract class Weapon {
    public abstract void shoot();
}

```

**具体的武器**

```java
package com.flt.abstractfactory;

public class Ak47 extends Weapon {
    @Override
    public void shoot() {
        System.out.println("dududdudu.....");
    }
}

```

```java
package com.flt.abstractfactory;

public class MagicStick extends Weapon{
    @Override
    public void shoot() {
        System.out.println("magicStick dingdingding....");
    }
}

```



**座驾族:**

```java
package com.flt.abstractfactory;

public abstract class Vehicle {
    public abstract void go();
}

```

**具体的座驾:**

```java
package com.flt.abstractfactory;

import com.flt.factormethod.Moveable;

public class Car  extends Vehicle {
    @Override
    public void go() {
        System.out.println("Car go wuwuwuwwu...");
    }
}

```

```java
package com.flt.abstractfactory;

public class Broom  extends Vehicle{
    @Override
    public void go() {
        System.out.println("broom shuashuashua...");
    }
}

```

**顶层抽象工厂:**

```java
package com.flt.abstractfactory;
//可以生产一系列不同产品的 抽象工厂
public abstract class AbstractFactor {
    //生产座驾
     abstract Vehicle createVehicle();

    //生产武器
     abstract Weapon createWeapon();
}
```

**具体的工厂:**

```java
package com.flt.abstractfactory;

public class ModernFactory extends AbstractFactor {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new Ak47();
    }
}

```

```java
package com.flt.abstractfactory;

public class MagicFactory extends AbstractFactor {
    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}

```

**工厂方法vs抽象工厂:**

**工厂方法在产品上好扩展，但是在产品族上不好扩展；抽象工厂在产品上不好扩展在产品族上好扩展**

### 4、门面模式-Facade

**门面模式，是指提供一个统一的接口去访问多个子系统的多个不同的接口，它为子系统中的一组接口提供一个统一的高层接口。使得子系统更容易使用。**

**比喻：把一个系统划分为几个较小的子系统。如果把医院作为一个子系统，按照部门职能，这个系统可以划分为挂号、门诊、划价、化验、收费、取药等。看病的病人要与这些部门打交道，就如同一个子系统的客户端与一个子系统的各个类打交道一样，不是一件容易的事情。首先病人必须先挂号，然后门诊。如果医生要求化验，病人必须首先划价，然后缴费，才可以到化验部门做化验。化验后再回到门诊室。**

![image-20220216142405647](设计模式DesignPattern(马士兵版).assets/image-20220216142405647.png)

**上图描述的是病人在医院里的体验，图中的方框代表医院。解决这种不便的方法便是引进门面模式，医院可以设置一个接待员的位置，由接待员负责代为挂号、划价、缴费、取药等。这个接待员就是门面模式的体现，病人只接触接待员，由接待员与各个部门打交道。**

![image-20220216142445995](设计模式DesignPattern(马士兵版).assets/image-20220216142445995.png)

### 5、调停者(中介模式)-Mediator

**调停者模式其实就是上面门面系统的对内操作，常见的消息中间件都是调停者模式的体现**

![image-20220216144346684](设计模式DesignPattern(马士兵版).assets/image-20220216144346684.png)

### **6、装饰者模式-Decorator**

**模式的结构**

1. 装饰模式主要包含以下角色。

   1. 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
   2. 具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
   3. 抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
   4. 具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。

   装饰模式的结构图如图 1 所示。

      

​						![image-20201027091803425](设计模式DesignPattern(马士兵版).assets/image-20201027091803425.png)
   ​						    								   装饰者模式结构图

   ![image-20220216152510052](设计模式DesignPattern(马士兵版).assets/image-20220216152510052.png)

**示例代码的继承图其中:Beverage是抽象组件、Coffee和Juice是具体组件、AbstractDecorator是抽象装饰器、HotDecorator和IceDecorator是具体装饰类**

**抽象组件:**

```java
package com.flt.decorator;

//顶层的饮料类
public abstract class Beverage {
    //饮料的描述信息
    protected String description="unknown beverage....";


    public String getDescription() {
        return description;
    }


    //计算价钱的方法
    public abstract int cost();
}

```

**具体组件:**

```java
package com.flt.decorator;
//具体组件的实现类
public class Coffee extends Beverage {
    public Coffee() {
        super.description="this is a coffee";
    }

    @Override
    public int cost() {
        return 15;
    }
}

```

```java
package com.flt.decorator;
//具体组件的实现类
public class Juice extends Beverage {
    public Juice() {
        super.description="this is a juice";
    }

    @Override
    public int cost() {
        return 5;
    }
}

```

**抽象装饰器:**

```java
package com.flt.decorator;

//抽象的装饰器父类
public abstract class AbstractDecorator extends Beverage {
    //要装饰的Beverage
    protected Beverage concreteComponent;


    public AbstractDecorator(Beverage concreteComponent) {
        this.concreteComponent = concreteComponent;
    }

    //要给饮料加上新的描述 所以得把这个重写了;
    public abstract String getDescription();
}

```

**具体装饰器:**

```java
package com.flt.decorator;

public class HotDecorator extends AbstractDecorator {

    public HotDecorator(Beverage concreteComponent) {
        super(concreteComponent);
    }

    @Override
    public String getDescription() {
        return super.concreteComponent.description + " hot";
    }

    @Override
    public int cost() {
        return super.concreteComponent.cost() + 3;
    }
}

```

```java
package com.flt.decorator;

public class IceDecorator extends AbstractDecorator{

    public IceDecorator(Beverage concreteComponent) {
        super(concreteComponent);
    }
    @Override
    public String getDescription() {
        return super.concreteComponent.description+" append ice";
    }

    @Override
    public int cost() {
        return super.concreteComponent.cost()+1;
    }
}

```

### 7、责任链模式-chainofresponsibility

```java
package com.flt.chainofresponsiblity;


import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Msg msg = new Msg("武汉华神智能科技有限公司 <script> 官网:igbs.com 每天都在996 T-T ");
        FilterChain chain1 = new FilterChain();
        chain1.add(new HTTMLFilter()).add(new SensitiveFilter());
        FilterChain chain2 = new FilterChain();
        chain2.add(new HttpFilter()).add(new FaceFilter());
        chain1.add(chain2);
        chain1.doFilter(msg);
        System.out.println(msg.getMsg());
    }

}

class Msg {
    private String name;
    private String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

interface Filter {
    boolean doFilter(Msg msg);
}

class HTTMLFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("<", "[");
        s = s.replaceAll(">", "]");
        msg.setMsg(s);
        return true;
    }
}

class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("996", "955");
        msg.setMsg(s);
        return true;
    }
}

class FaceFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("T-T ", "^_^");
        msg.setMsg(s);
        return true;
    }
}

class HttpFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("igbs.com", "http://www.igbs.com");
        msg.setMsg(s);
        return true;
    }
}

class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();

    @Override
    public boolean doFilter(Msg msg) {
        for (Filter filter : filters) {
            if (!filter.doFilter(msg)) {
                return false;
            }
        }
        return true;
    }

    public FilterChain add(Filter filter) {
        this.filters.add(filter);
        return this;
    }
}
```

**思考：为什么在JavaEE的Servlet中的Filter可以先处理Request请求在处理Response请求呢？**

**答案是其实在Servlet的FilterChain调用过程中是模拟递归的过程，查看他的方法可以知道他把当前的过滤链也传进去了**

```java
public class ServletChainOfResponsibility {
    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();
        FilterChain chain = new FilterChain();
        chain.add(new Filter1()).add(new Filter2());
        chain.doFilter(request, response);
    }
}

interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}

class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int index = -1;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        if (index == -1) {
            index = 0;
        }
        return this;
    }


    public void doFilter(Request request, Response response) {
        if (index == -1 || !(index < filters.size())) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, this);

    }
}

class Request {
    public String str = "Original";
}

class Response {
    public String str = "Original";
}

class Filter1 implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str + "E1";
        System.out.println("request:" + request.str);
        chain.doFilter(request, response);
        response.str = response.str + "O2";
        System.out.println("response:" + response.str);

    }
}

class Filter2 implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str + "E2";
        System.out.println("request:" + request.str);
        chain.doFilter(request, response);
        response.str = response.str + "O1";
        System.out.println("response:" + response.str);
    }
}
```

**answer**

```base
request:OriginalE1
request:OriginalE1E2
response:OriginalO1
response:OriginalO1O2
```

### 8、观察者模式-Observer

**模型**

![image-20220218145352976](设计模式DesignPattern(马士兵版).assets/image-20220218145352976.png)

**常见Jave中的AWT包中的KeyMap键盘事件就是个常见的Observer观察者模式**

**在很多系统中Observer模式都是与责任链共同负责对于事件的处理的**

### 9、组合模式-Composite(树状结构专用模式)

```java
package com.flt.composite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode capter1 = new BranchNode("capter1");
        BranchNode capter2 = new BranchNode("capter2");
        BranchNode section = new BranchNode("section");
        Node c11 = new LeafNode("c11");
        Node c12 = new LeafNode("c12");
        Node c21 = new LeafNode("c21");
        Node c22 = new LeafNode("c22");
        root.add(capter1).add(capter2);
        capter1.add(c11).add(c12);
        capter2.add(section);
        section.add(c21).add(c22);
        tree(root,0);

    }

    static void tree(Node root, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("--");
        }
        root.print();
        if (root instanceof BranchNode) {
            for (Node child : ((BranchNode) root).childs) {
                tree(child, depth + 1);
            }
        }
    }


}

interface Node {
    void print();
}

class LeafNode implements Node {
    String nodeName;

    public LeafNode(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void print() {
        System.out.println(nodeName);
    }
}

class BranchNode implements Node {
    String nodeName;
    List<Node> childs;

    public BranchNode(String nodeName) {
        this.nodeName = nodeName;
        this.childs = new ArrayList<>();
    }

    public BranchNode add(Node node) {
        childs.add(node);
        return this;
    }

    @Override
    public void print() {
        System.out.println(nodeName);
    }
}
```

### 10、享元-FlyWeight

**重复利用对象**

**常见的是在多线程共享资源的时候，还有就是Integer的-128~127的Integer对象其实都是相同的String也同理**

![image-20220218154246648](设计模式DesignPattern(马士兵版).assets/image-20220218154246648.png)

### 11、代理-proxy

**静态代理**

```java
package com.flt.proxy;

public class StaticProxy {
    public static void main(String[] args) {
        Moveable tank = new Tank();
        tank.move();
        Moveable proxy = new TankProxy(tank);
        System.out.println("-------------");
        proxy.move();
    }


}

interface Moveable {
    void move();
}

class Tank implements Moveable {
    @Override
    public void move() {
        System.out.println("tank is move....");
    }
}

class TankProxy implements Moveable {
    private Moveable moveable;

    public TankProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        moveable.move();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("move time:" + (end - start)+"");
    }
}

```

**动态代理:**

```java
package com.flt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        Tank1 tank1 = new Tank1();


        Moveable1 o = (Moveable1) Proxy.newProxyInstance(Tank1.class.getClassLoader()
                , new Class[]{Moveable1.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxy method name:" + method.getName() + " start");
                        Object o = method.invoke(tank1, args);
                        System.out.println("proxy method name " + method.getName() + " end");
                        return o;
                    }
                });
        o.move();
    }
}

interface Moveable1 {
    void move();
}

class Tank1 implements Moveable1 {
    @Override
    public void move() {
        System.out.println("tank moving claclacla....");
    }
}
```

**上述代码我们调用的是代理对象的move方法实际执行的是invocationHanlder接口里面的invoke对象这是为什么呢?**

