package com.evan.onepiece.exceptions;

/**
 * 动态的向DynamicFields添加字段
 *
 * @author Evan Huang
 * @date 2018/4/18 10:58
 */
public class DynamicFields {
    /**
     * 成对的对象（第一个对象表示字段标识符，第二个字段表示字段值）
     */
    private Object[][] fields;

    public DynamicFields(int initialSize) {
        fields = new Object[initialSize][2];
        for (int i = 0; i < initialSize; i++) {
            fields[i] = new Object[]{null, null};
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object[] field : fields) {
            result.append(field[0]);
            result.append(":");
            result.append(field[1]);
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * 判断是否具有字段
     *
     * @param id
     * @return
     */
    private int hasField(String id) {
        for (int i = 0; i < fields.length; i++) {
            if (id.equals(fields[i][0])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取字段位置
     *
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    private int getFieldNumber(String id) throws NoSuchFieldException {
        int fieldNum = hasField(id);
        if (fieldNum == -1) {
            throw new NoSuchFieldException();
        }
        return fieldNum;
    }

    /**
     * 设置字段
     *
     * @param id
     * @return
     */
    private int makeField(String id) {
        //循环遍历fields找到空的位置存储id
        for (int i = 0; i < fields.length; i++) {
            if (fields[i][0] == null) {
                fields[i][0] = id;
                return i;
            }
        }
        //数组存满之后，进行扩充
        Object[][] tmp = new Object[fields.length + 1][2];
        for (int i = 0; i < fields.length; i++) {
            tmp[i] = fields[i];
        }

        for (int i = fields.length; i < tmp.length; i++) {
            tmp[i] = new Object[]{null, null};
        }

        fields = tmp;

        return makeField(id);
    }

    /**
     * 获取字段
     *
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    public Object getField(String id) throws NoSuchFieldException {
        return fields[getFieldNumber(id)][1];
    }

    /**
     * 通过表示修改已有字段值
     *
     * @param id
     * @param value
     * @throws DynamicFieldsException
     */
    public Object setField(String id, Object value) throws DynamicFieldsException {
        if (value == null) {
            DynamicFieldsException dfe = new DynamicFieldsException();
            //使用initCause将其它类型的异常链连接起来
            dfe.initCause(new NullPointerException());
            throw dfe;
        }
        int fieldNumber = hasField(id);
        if (fieldNumber == -1) {
            fieldNumber = makeField(id);
        }
        Object result = null;
        try {
            //获取旧值
            result = getField(id);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        fields[fieldNumber][1] = value;
        return result;
    }


}
