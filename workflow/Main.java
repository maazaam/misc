package workflow;

public class Main {

    public static void main(String[] args) {
        Flow f = new Flow("f1.0");
        f.add(new Step("s1.0"));
        f.add(new Step("s2.0") {
            {
                on("success").add(new Step("s2.1"));
                on("failure").add(new Step("s2.2"));
            }

            public String process() {
                return "success";
            }
        });
        f.add(new Step("s3.0"));
        System.out.println(f);
        f.process();
    }
}
