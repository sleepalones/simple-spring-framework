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
