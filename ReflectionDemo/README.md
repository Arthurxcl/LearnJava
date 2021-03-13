
反射
---
> JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为Java语言的反射机制。

> 将类的各个组成部分封装为其他对象，这就是反射机制

> 将字节码文件加载进jvm内存后，内存中生成一个 `Class类对象`描述字节码文件（Java中有一个类名称为`Class`，用来描述字节码文件的一些共同的特征和行为）。所有的成员变量封装为 `Field` 对象，构造方法封装为 `Constructor`对象，成员函数封装为 `Method`对象，都是数组的形式，例如 `Field[] fields`

#### 作用
    1. 反射让开发人员可以通过外部类的全路径名创建对象，并使用这些类，实现一些扩展的功能。
    2. 反射让开发人员可以枚举出类的全部成员，包括构造函数、属性、方法。以帮助开发者写出正确的代码。
    3. 测试时可以利用反射 API 访问类的私有成员，以保证测试代码覆盖率。
#### 相关类
    1. Field 类：提供有关类的属性信息，以及对它的动态访问权限。它是一个封装反射类的属性的类。
    2. Constructor 类：提供有关类的构造方法的信息，以及对它的动态访问权限。它是一个封装反射类的构造方法的类。
    3. Method 类：提供关于类的方法的信息，包括抽象方法。它是用来封装反射类方法的一个类。
    4. Class 类：表示正在运行的 Java 应用程序中的类的实例。

#### 使用方法
1. 获取Class类对象的方式
```
//通过类名将字节码文件加载进内存，返回Class类对象。
//多用于配置文件，将类名定义在配置文件中，读取文件加载类
Class.forName("类名") 
//通过类名的属性class 获取。
//多用于参数的传递
className.class;
//通过对象的方法来获取，getClass()方法定义在Object类中
//多用于 对象获取字节码文件对象
object.getClass();
```
> 结论：同一个字节码文件（*.class）在一次程序的运行过程中只会被加载一次，无论通过哪一种方式获取的class对象都是同一个
2. Class 对象功能
    - 获取功能
    ```
    1. 获取成员变量
    //常用方法
    Field[]	getFields() //获取所有public 修饰的成员变量
    Field	getField(String name) : //获取指定名称的public修饰的成员变量
    Field[]	getDeclaredFields() //获取所有的成员变量，不考虑访问修饰符
    Field	getDeclaredField(String name) //获取指定名称的成员变量，不考虑访问修饰符
    //Field对象作用：设置和获取值
    Object	get(Object obj)
    void	set(Object obj, Object value)
    //如果想要访问私有变量，需要忽略访问权限修饰符的安全检查（暴力反射）
    privateField.setAccessible(true);
    
    2. 获取构造方法
    Constructor<T>	getConstructor(Class<?>... parameterTypes)
    Constructor<?>[]	getConstructors()
    Constructor<T>	getDeclaredConstructor(Class<?>... parameterTypes)
    Constructor<?>[]	getDeclaredConstructors()
    //Constructor对象的主要作用：创建对象
    //如果使用空参数构造方法构造对象，可以使用clas对象的 newInstance()方法
    T	newInstance(Object... initargs)
    eg. Object person = constructor.newInstance("张三"， 23)；
    
    3. 获取成员函数
    Method	getDeclaredMethod(String name, Class<?>... parameterTypes)
    Method[]	getDeclaredMethods()
    Method	getMethod(String name, Class<?>... parameterTypes) //方法名称，参数列表作为参数
    Method[]	getMethods()
    //Method对象的主要作用：执行方法
    Object invoke(Object obj, Object... args);//参数为 指定的对象，参数列表
    //获取方法名称
    String	getName();
    
    4. 获取类名
    String	getName()
    eg. String className = personClass.getName();
    ```
