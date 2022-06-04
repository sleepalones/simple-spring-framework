# 实现简易的 Spring 框架
## 搭建开发环境
- jdk 11
- maven 3.8.4
- 编译 spring 源码（spring-framework-5.2.0.RELEASE）
- idea 2021.2 搭建 javaWeb 环境
## 构建业务系统的架构【自研框架的起源】
- 项目业务梳理
- 项目依赖配置
- Service 层代码架子搭建
- Controller 层代码架子搭建
  - 使用一个 servlet 拦截所有请求，再从请求中获取路径和请求类型找到对应的 Controller 处理
## 从项目开发到框架内开发的转换
- 当前项目存在的问题？
  - 项目扩展性不强，违反开闭原则
  - 尝试引入工厂模式，对扩展开放，对修改关闭
  - 经过尝试发现并不能完全实现对扩展开放，对修改关闭
  - 参考 spring ioc 尝试引入反射
  - 用注解来保存类相关的信息以提供反射调用
  - 使用注解标记需要工厂管理的实例，并依据注解属性做精细控制
  - 不需要再写 if else 来调用 controller，不需要再 new 对象，不再需要关心细节
- 控制反转 Ioc
  - 依托一个类似工厂的 Ioc 容器
  - 将对象的创建、依赖关系的管理以及生命周期交由 Ioc 容器管理
  - 降低系统在实现上的复杂性和耦合度，易于扩展，满足开闭原则
## 自研框架 Ioc 容器的实现
- 创建注解
  - Controller
  - Service
  - Repository
  - Component
- 提取标记对象
  - 指定范围，获取范围内的所有类
  - 遍历所有类，获取被注解标记的类并加载进容器里
- 实现容器
  - 单例模式
    - 确保一个类只有一个实例，并对外提供统一访问方式
      - 饿汉模式：类被加载的时候就立即初始化并创建唯一实例（线程安全）
      - 懒汉模式：在被客户端首次调用的时候才创建唯一实例（线程不安全，需要加入双重检查锁机制确保线程安全）
    - 通过编写代码发现，现有的单例模式并不安全，构造函数私有化可以被java的反射机制轻松破坏。
    - 装备了枚举的饿汉模式能抵御反射与序列化的攻击，满足容器需求
    ```java
    //实现能抵挡反射与序列化攻击的安全的单例模式--饿汉模式
    public class EnumStarvingSingleton {
        //构造私有
        private EnumStarvingSingleton(){
            
        }
    
        //提供返回统一对象方法
        public static EnumStarvingSingleton getInstance() {
            
        }
        
        private enum ContainerHolder{
            HOLDER;
            private EnumStarvingSingleton instance;
            ContainerHolder() {
                instance = new EnumStarvingSingleton();
            }   
        }
    }
      ```
    - 使用单例模式创建容器
      - 保存Class对象及其实例的载体
      - 容器的加载
        - 配置的管理与获取
        - 获取指定范围内的Class对象
        - 依据配置提前Class对象，连同实例一并放入容器
      - 容器的操作方式
        - 增加、删除操作
        - 根据Class获取对应实例
        - 获取所有的Class和实例
        - 通过注解来获取被注解标注的Class
        - 通过超类获取对应的子类Class
        - 获取容器载体保存了Class的数量
- 依赖注入
  - 实现容器的依赖注入
    - 定义相关的注解标签
    - 实现创建被注解标记的成员变量实例，并将其注入到成员变量里
    - 依赖注入的使用
    - 仅支持成员变量的注入
## SpringIoC的源码解析
- 基本流程
  - 解析读取配置文件
  - 定位和注册对象到容器中（Resource,BeanDefinition）
  - 依赖注入对象
- Spring解决了什么问题？
  - 将对象之间的关系转而用配置来管理
    - 依赖注入---依赖关系在Spring的IoC容器中管理
    - 通过把对象包装在Bean中以达到管理对象和进行额外操作的目的
    - Bean的本质就是java对象，只是这个对象的生命周期由容器来管理
    - 不需要为了创建Bean而在原来的java类上添加任何额外的限制，最小侵入
    - 对java对象的控制方式体现在配置上
- Bean 与 BeanDefinition
  - Spring 通过把对象包装在Bean中以达到管理对象和进行额外操作的目的
  - Bean的本质就是Java对象，Spring根据配置，生成用来描述Bean的BeanDefinition（JDK中使用 Class 来描述对象）
  - BeanDefinition常用属性：
    - 作用范围scope（@Scope）
    - 懒加载lazy-init（@Lazy）：决定Bean实例是否延迟加载
    - 首选primary（@Primary）：设置为true的bean会是优先的实现类
    - factory-bean和factory-method（@Configuration和@Bean）
- BeanFactory---简单容器
  - Bean主要功能：
    - 表面上只有 getBean
    - 实际上控制反转、基本的依赖注入、直至 Bean 的生命周期的各种功能，都由它的实现类提供
  - BeanFactory和FactoryBean的区别？
    - BeanFactory是提供了IOC容器最基本的形式，给具体的IOC容器的实现提供了规范，FactoryBean可以说为IOC容器中Bean的实现提供了更加灵活的方式，FactoryBean在IOC容器的基础上给Bean的实现加上了一个简单工厂模式和装饰模式，我们可以在getObject()方法中灵活配置。
    - 他们两个都是个工厂，但FactoryBean本质上还是一个Bean，也归BeanFactory管理
    - BeanFactory是Spring容器的顶层接口，FactoryBean更类似于用户自定义的工厂接口
  - ListableBeanFactory
    - 以列表的形式提供相关信息
    - 主要用来查看工厂生产的实例的信息
  - AutowireCapableBeanFactory
    - 自动装配相关的接口
  - DefaultListableBeanFactory
    - 真正可以独立运行的IOC容器
    - 对外提供注册能力
- ApplicationContext---高级容器
  - ApplicationContext组合并扩展了BeanFactory的功能：
    - 包括国际化（MessageSource）
    - 通配符方式获取一组Resource资源
    - 整合Environment环境、
    - 事件发布与监听事件解耦（ApplicationEventPublisher），事件发送等
  - 传统的基于XML配置的经典容器：
    - FileSystemXmlApplicationContext：从文件系统加载配置
    - ClassPathXmlApplicationContext：从classpath加载配置
    - XmlWebApplicationContext：用于Web应用程序的容器
  - 基于注解的容器：
    - AnnotationConfigServletWebServerApplicationContext（Web应用的容器，属于SpringBoot）
    - AnnotationConfigReactiveWebServerApplicationContext（响应式容器，属于SpringBoot）
    - AnnotationConfigApplicationContext（普通的非Web容器，属于Spring）
  - 以上容器的共性：refresh()方法，IOC容器的启动
    - 容器初始化、配置解析
    - BeanFactoryPostProcessor和BeanPostProcessor的注册和激活
    - 国际化配置等
- Resource接口
  - 在java中资源会被抽象成URL，通过解析URL的协议来处理不同资源的操作逻辑
  - Spring将对物理资源的访问方式抽象为Resource
  - 常用实现类：
    - ServletContextResource---访问web容器上下文中的资源
    - ClassPathResource---访问类加载路径下的资源
    - FileSystemResource---访问文件系统资源
  - 根据资源地址自动选择正确的Resource
    - 自动识别“classpath:”、”file:“等资源地址前缀
    - 支持自动解析Ant风格带通配符的资源地址
    - ResourceLoader
      - 实现不同的Resource加载策略，按需返回特定类型的Resource
      - 它的方法只支持单个Resource的返回
    - ResourcePatternResolver
      - 用于根据通配符返回多个Resource对象
- BeanDefinitionReader
  - 利用ResourceLoader和ResourcePatternResolver将配置信息解析成一个个BeanDefinition
  - 借助BeanDefinitionRegistry接口将BeanDefinition注册到容器中
  - BeanDefinitionRegistry负责对BeanDefinition的注册，即将beanName和beanName的实例作为键值放到ConcurrentHashMap中
- 后置处理器 PostProcessor
  - 本身也是一种需要注册到容器里的Bean
  - 其里面的方法会在特定的时机被容器调用
  - 实现不改变容器或者Bean核心逻辑的情况下对Bean进行扩展（例如框架的整合---mybatis）
  - 对Bean进行包装，影响其行为、修改Bean的内容等
  - PostProcessor 的种类
    - 容器的后置处理器
      - BeanDefinitionRegistryPostProcessor
      - BeanFactoryPostProcessor
    - Bean的后置处理器
      - BeanPostProcessor（Bean被创建出来后，可以通过该后置处理器对bean进行包装，例如AOP）
- Aware
  - 在Bean中设置对容器的感知
  - 从Bean里获取到的容器实例并对其进行操作
- 事件监听者模式
  - 监听器将监听感兴趣的事件，一旦事件发生，便做出响应
    - 事件源（Event Source）
    - 事件监听器（Event Listener）
    - 事件对象（Event Object）
  - Spring的事件驱动模型
    - 事件驱动模型的三大组成部分
      - 事件：ApplicationEven抽象类
      - 事件监听器：ApplicationListener
      - 事件发布器：Publisher以及Multicaster
- 依赖注入
  - AbstractBeanFactory
    - doGetBean
      - 获取Bean实例
  - DefaultSingletonRegistry
    - getSingleton
      - 获取单例实例
    - 三级缓存
      - 解决循环依赖
  - AbstractAutowireCapableBeanFactory
    - createBean
      - 创建Bean实例的准备
    - doCreateBean
      - 创建Bean实例
    - applyMergedBeanDefinitionPostProcessors
      - 处理@Autowried以及@Value
    - populateBean
      - 给Bean实例注入属性值（依赖注入）
  - AutowiredAnnotationBeanPostProcessor
    - postProcessProperties
      - Autowired的依赖注入逻辑
  - DefaultListableBeanFactory
    - doResolveDependency
      - 依赖解析
  - DependencyDescriptor
    - InjectionPoint
      - 创建依赖实例
## Spring AOP 的实现
- AOP 相关术语
  - 切面Aspect：将横切关注点逻辑进行模块化封装的实体对象
  - 通知Advice：好比是Class里面的方法，还定义了织入逻辑的时机
  - 连接点Joinpoint，允许使用Advice的地方
  - SpringAOP默认只支持方法级别的Joinpoint
  - 切入点Pointcut：定义一系列规则对Joinpoint进行筛选
  - 目标对象Target：符合Pointcut条件，要被织入横切逻辑的对象
- Advice 的种类
  - BeforeAdvice：在JoinPoint前被执行的Advice
  - AfterAdvice：好比try..catch..finaly里面的finaly
  - AfterReturningAdvice：在Joinpoint执行流程正常返回后被执行
  - AfterThrowingAdvice：Joinpoint执行过程中抛出异常才会触发
  - AroundAdvice：在Joinpoint前和后都执行，最常用的Advice
  - Introduction-引入型Advice：
    - 为目标类引入新接口，而不需要目标类做任何实现
    - 使得目标类在使用的过程中转型成新接口对象，调用新接口的方法
- SpringAOP的实现原理
  - 代理模式
    - 静态代理
    - 动态代理
      - JDK 动态代理
        - 基于反射机制实现，要求业务类必须实现接口
        - 程序运行时动态生成类的字节码，并加载到JVM中
        - 要求”被代理的类“必须实现接口
        - 并不要求”代理对象“去实现接口，所以可以复用代理对象的逻辑
      - CGLIB 动态代理：
        - 不需要业务类实现接口，相对灵活
        - 内部主要封装了ASM java字节码操控框架
        - 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类的方法调用，顺势织入横切逻辑
        - 缺点：对于final类和final方法，无法进行代理
- 实现自研框架的AOP 1.0
  - 使用CGLIB来实现：不需要业务类实现接口，相对灵活
    - 解决标记的问题，定义横切逻辑的骨架
      - 定义与横切逻辑相关的注解
      - 定义供外部使用的横切逻辑骨架
      - 定义Aspect横切逻辑以及被代理方法的执行顺序
        - 创建MethodInterceptor的实现类
        - 定义必要的成员变量--被代理的类以及Aspect列表
        - 按照Order对Aspect进行排序
        - 实现对横切逻辑以及被代理对象方法的定序执行
      - 将横切逻辑织入到被代理的对象以生成动态代理对象
- 改进 AOP 2.0
  - 让Pointcut更加灵活
  - 只需要引入 AspectJ的切面表达式和相关的定位解析机制
  - AspectJ 框架
    - 提供了完整的AOP解决方案，是AOP的java实现版本
    - 定义切面语法以及语法的解析机制
    - 提供了强大的织入工具
