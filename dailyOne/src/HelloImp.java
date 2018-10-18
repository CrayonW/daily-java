public class HelloImp implements Hello {

    @Override
    public String sayHello(String str) {
        return "HelloImp" + str;
    }

    @Override
    public String action(String str) {
        return null;
    }
}
