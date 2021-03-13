import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.Class;

class Apple {

    private int price;
    public String name;

    private Apple()
    {

    }

    public Apple(int price)
    {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
public class Main {

    public static void main(String[] args) throws Exception{
        Class appleClass = Apple.class;
        Field[] fields = appleClass.getFields();
        Method[] methods = appleClass.getMethods();
        for(Method method : methods)
        {
            System.out.println(method);
        }

        //Method setPriceMethod = clz.getMethod("setPrice", int.class);
        //Constructor appleConstructor = clz.getConstructor();
        //Object appleObj = appleConstructor.newInstance();
        //setPriceMethod.invoke(appleObj, 14);
        //Method getPriceMethod = clz.getMethod("getPrice");
        //System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
    }
}
