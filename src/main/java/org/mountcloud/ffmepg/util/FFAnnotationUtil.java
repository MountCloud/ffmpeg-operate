package org.mountcloud.ffmepg.util;


import org.mountcloud.ffmepg.annotation.FFAnnotation;
import org.mountcloud.ffmepg.annotation.FFCmd;
import org.mountcloud.ffmepg.annotation.FFCmdBean;
import org.mountcloud.ffmepg.excption.FFMpegOperationNotFoundExcption;
import org.mountcloud.ffmepg.operation.FFOperationBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解工具
 * com.ugirls.ffmepg.util
 * 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFAnnotationUtil {

    /**
     * 提取一个操作中的注解
     * @param bean 操作
     * @param <T> 操作的类型
     * @return 返回操作中的所有注解
     */
    public <T extends FFOperationBase> FFCmdBean getClassAnnocation(T bean) throws IllegalAccessException {
        FFCmdBean ffCmdBean = new FFCmdBean();

        Class<?> beanClass = bean.getClass();

        //获取类的注解
        FFCmd classFFCmd = getClassFFCmd(beanClass);
        FFAnnotation classFFAnnotation = getFFOperation(classFFCmd,null,bean);

        if(classFFAnnotation==null){
            throw new FFMpegOperationNotFoundExcption(beanClass.getName()+" not found FFCmd.");
        }

        //获取属性注解
        List<Field> fields = new ArrayList<Field>();

        ObjectUtil objectUtil = new ObjectUtil();
        //获取全部属性
        objectUtil.getFields(beanClass,fields,null);

        if(fields==null||fields.size()==0){
            throw new FFMpegOperationNotFoundExcption(beanClass.getName()+" fields length is 0");
        }

        List<FFAnnotation> ffAnnotations = new ArrayList<FFAnnotation>();

        for(int i=0;i<fields.size();i++){
            Field field = fields.get(i);
            FFCmd fieldFFCmd = field.getAnnotation(FFCmd.class);
            FFAnnotation fieldFFAnnotation = getFFOperation(fieldFFCmd,field,bean);
            if(fieldFFAnnotation!=null){
                ffAnnotations.add(fieldFFAnnotation);
            }
        }

        if(ffAnnotations==null||ffAnnotations.size()==0){
            throw new FFMpegOperationNotFoundExcption(bean.getClass().getName()+" fields not found FFCmd");
        }

        ffCmdBean.setCmdName(classFFAnnotation);
        ffCmdBean.setCmdParameter(ffAnnotations);

        return ffCmdBean;
    }

    /**
     * 递归查询FFCmd
     * @param cls cls
     * @param <T> type
     * @return FFcmd
     */
    private <T> FFCmd getClassFFCmd(Class<T> cls){
        FFCmd ffCmd = cls.getAnnotation(FFCmd.class);
        if(ffCmd==null){
            Class<?> superCls = cls.getSuperclass();
            if(superCls.equals(Object.class)){
                return null;
            }else{
                return getClassFFCmd(superCls);
            }
        }
        return ffCmd;
    }

    /**
     * 从一组Annotation中提取FFCmd
     * @param ffCmd 注解
     * @return FFAnnotation
     */
    private <T> FFAnnotation getFFOperation(FFCmd ffCmd, Field field,T bean) throws IllegalAccessException {
        FFAnnotation ffAnnotation = null;
        //如果没有注解的返回空
        if(ffCmd==null){
            return ffAnnotation;
        }

        //获取key值
        String key = ffCmd.key();

        ffAnnotation = new FFAnnotation();

        if(field==null){
            ffAnnotation.setKey(key);
            return ffAnnotation;
        }
        //设置访问最高权限可访问private属性
        field.setAccessible(true);

        ffAnnotation.setKey(key);
        Object value = field.get(bean);
        if(value!=null){
            String valueStr = value.toString();
            ffAnnotation.setValue(valueStr);
        }

        return ffAnnotation;
    }

}
