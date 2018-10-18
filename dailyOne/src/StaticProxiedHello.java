public class StaticProxiedHello implements Hello {
    private Hello hello = new HelloImp();

    @Override
    public String sayHello(String str) {
        //you said：
        return hello.sayHello(str);
    }

    @Override
    public String action(String str) {
        return null;
    }
}
