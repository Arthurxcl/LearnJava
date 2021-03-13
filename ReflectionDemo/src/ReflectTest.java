import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 框架类
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        /**
         * 可以帮我们创建任意类的对象，并且可以执行其中任意方法
         * 前提：不能改变该类的任何代码
         * 实现方法： 1.配置文件  2.反射
         * 步骤： 1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
         *       2. 在程序中加载读取配置文件
         *       3. 使用反射技术来加载类文件进内存
         *       4. 创建对象
         *       5. 执行方法
         */
        //1.加载配置文件
        //1.1 创建Properties对象
        Properties pro = new Properties();
        //1.2 加载配置文件， 转换成一个集合
        //1.2.1 获取 class 目录下的配置文件的方式，获取类加载器，然后根据资源路径获取资源数据流
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);

        //2. 获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3. 加载该类进入内存
        Class cls = Class.forName(className);

        //4. 创建对象
        //Object obj = cls.newInstance();
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();

        //5. 获取方法对象
        Method method = cls.getMethod(methodName);

        //执行方法
        method.invoke(obj);
    }
}
