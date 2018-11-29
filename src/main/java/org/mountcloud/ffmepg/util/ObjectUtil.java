package org.mountcloud.ffmepg.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Object对象工具
 * @author zhanghaishan
 * @version V1.0
 * 2017/12/18.
 */
public class ObjectUtil<T> {

    /**
     * 获取所有函数
     * @param t 类
     * @param methods 方法
     * @param index 深度
     */
    public void getMethods(Class t, List<Method> methods, Integer index){

        methods.addAll(Arrays.asList(t.getMethods()));

        if(index!=null){
            index = index -1;
            if(index<=0){
                return;
            }
        }

        Class t1 = t.getSuperclass();
        if (t1.getSimpleName().equals("Object")) {
            return;
        }
        getMethods(t1, methods,index);
    }

    /**
     * 获取所有属性
     * @param t 类
     * @param fields 属性
     * @param index 深度
     */
    public void getFields(Class t, List<Field> fields, Integer index) {

        fields.addAll(Arrays.asList(t.getDeclaredFields()));

        if(index!=null){
            index = index -1;
            if(index<=0){
                return;
            }
        }

        Class t1 = t.getSuperclass();
        if (t1.getSimpleName().equals("Object")) {
            return;
        }
        getFields(t1, fields,index);
    }

    /**
     * 将空属性附上默认值
     * @param obj 对象
     * @param notSet 不设置为空的属性
     */
    public void setNullFields(T obj, List<String> notSet){
        if(obj==null){
            return;
        }
        Class entityClass = obj.getClass();

        List<Field>  entityFields = new ArrayList<Field>();
        getFields(entityClass, entityFields,null);

        for(Field field:entityFields){
            if(field.getName().equals("serialVersionUID")){
                continue;
            }

            if(notSet!=null&&notSet.contains(field.getName())){
                continue;
            }

            try{
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entityClass);
                Method get = pd.getReadMethod();
                Object getValue = get.invoke(obj);

                if(getValue==null){

                    Method set = pd.getWriteMethod();

                    Class fieldClass = field.getType();
                    if(fieldClass.equals(String.class)){
                        set.invoke(obj,new String(""));
                    }

                    if(fieldClass.equals(Integer.class)){
                        set.invoke(obj,new Integer(0));
                    }

                    if(fieldClass.equals(Double.class)){
                        set.invoke(obj,new Double(0));
                    }

                    if(fieldClass.equals(Float.class)){
                        set.invoke(obj,new Float(0));
                    }

                    if(fieldClass.equals(Date.class)){
                        set.invoke(obj,new Date());
                    }
                }

            }catch (Exception e){
                e.printStackTrace();;
            }

        }

    }

    /**
     * 给空属性附上默认值
     * @param objs 对象集合
     * @param notSet 不设置空的属性
     */
    public void setNullFields(List<T> objs,List<String> notSet){
        if(objs!=null){
            for(int i=0;i<objs.size();i++){
                T obj = objs.get(i);
                setNullFields(obj,notSet);
            }
        }
    }
}
