package com.evan.onepiece;

import com.evan.onepiece.exceptions.DynamicFields;
import com.evan.onepiece.exceptions.DynamicFieldsException;
import org.junit.Test;

/**
 * @author Evan Huang
 * @date 2018/4/18 20:04
 */
public class ExceptionTest {

    @Test
    public void testDynamicFields() {
        DynamicFields dynamicFields = new DynamicFields(3);
        System.out.println(dynamicFields);
        try {
            dynamicFields.setField("d", "A value for d");
            dynamicFields.setField("number", 47);
            dynamicFields.setField("number2", 48);
            System.out.println(dynamicFields);
            dynamicFields.setField("d", "A new value for d");
            dynamicFields.setField("number3", 11);
            System.out.println(dynamicFields);
            dynamicFields.getField("d");
            Object field = dynamicFields.setField("d", null);
        } catch (DynamicFieldsException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
