import java.lang.reflect.Proxy;

public class main {

    public static void main (String[] args) {
        // 2. 然后在需要使用Hello的时候，通过JDK动态代理获取Hello的代理对象。
        Hello hello = (Hello) Proxy.newProxyInstance(
                main.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {Hello.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new HelloImp()));// 3. 方法调用的实际处理者
        System.out.println(hello.sayHello("I love you!"));
    }
}
