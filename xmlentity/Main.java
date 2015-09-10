package xmlentity;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String KEY1 = "field1";
    private static final String KEY2 = "field2";
    private static final String KEY3 = "field3";
    private static final String KEY4 = "field4";

    private static final String VAL1 = "entity/field1";
    private static final String VAL2 = "entity/field2";
    private static final String VAL3 = "entity/field3";
    private static final String VAL4 = "entity/field4";

    private static final Map<String, String> MAP = new HashMap<String, String>();

    static {
        MAP.put(KEY1, VAL1);
        MAP.put(KEY2, VAL2);
        MAP.put(KEY3, VAL3);
        MAP.put(KEY4, VAL4);
    }

    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setId(1000L);
        entity.setStatus('T');
        entity.setValue(MAP.get(KEY1), "field1");
        entity.setValue(MAP.get(KEY2), "field2");
        entity.setValue(MAP.get(KEY3), "field3");
        entity.setValue(MAP.get(KEY4), "field4");
        System.out.println(entity);
        System.out.println(entity.getValue(MAP.get(KEY1)));
        System.out.println(entity.getValue(MAP.get(KEY2)));
        System.out.println(entity.getValue(MAP.get(KEY3)));
        System.out.println(entity.getValue(MAP.get(KEY4)));
        entity.setValue(MAP.get(KEY1), null);
        entity.setValue(MAP.get(KEY2), null);
        entity.setValue(MAP.get(KEY3), null);
        entity.setValue(MAP.get(KEY4), null);
        System.out.println(entity);
    }
}
