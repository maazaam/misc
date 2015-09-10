package xmlconfig;

public class Main {

    public static void main(String[] args) throws Exception {
        Config config = new Config("config.xml");
        System.out.println(config.getString("factory/@clazz"));
        System.out.println(config.getString("service/@clazz"));
        System.out.println(config.getString("string"));
        System.out.println(config.getString("char"));
        System.out.println(config.getList("list"));
    }
}
