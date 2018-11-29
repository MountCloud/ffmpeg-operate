package org.mountcloud.ffmepg.util;

import java.math.BigDecimal;

/**
 * BigDecimal计算工具
 * @author zhanghaishan
 * @version V1.0
 * com.ugirls.statisticalservice.util
 * 2018/1/16.
 */
public class FFBigDecimalUtil {

    //------------------------------------------------------------------------------------
    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException 异常
     */
    public static double div(double value1,double value2,int scale) throws IllegalAccessException{
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        BigDecimal devVal = b1.divide(b2, scale,BigDecimal.ROUND_HALF_UP);
        return devVal.doubleValue();
    }


    //-----------------------------------------------------------------------------------------------


    /**
     * 多个求和
     * @param decimals 多个值
     * @return 结果
     */
    public static BigDecimal addDecimals(BigDecimal ...decimals){
        BigDecimal val = new BigDecimal(0);
        for(BigDecimal dc:decimals){
            val = addDecimal(val,dc);
        }
        return val;
    }

    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static BigDecimal addDecimal(BigDecimal value1,BigDecimal value2){
        if(value1==null&&value2==null){
            return new BigDecimal(0);
        }
        if(value1==null){
            return value2;
        }
        if(value2==null){
            return value1;
        }
        return value1.add(value2);
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static BigDecimal subDecimal(BigDecimal value1,BigDecimal value2){
        return value1.subtract(value2);
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mulDecimal(BigDecimal value1,BigDecimal value2){
        return value1.multiply(value2);
    }

    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException  异常
     */
    public static BigDecimal divDecimal(BigDecimal value1,BigDecimal value2,int scale) throws IllegalAccessException{
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        return value1.divide(value2, scale);
    }

    /**
     * 整数相加
     * @param i1 值1
     * @param i2 值2
     * @return 相加结果
     */
    public static Integer addInteger(Integer i1,Integer i2){
        if(i1==null&&i2==null){
            return 0;
        }
        if(i1==null){
            return i2;
        }
        if(i2==null){
            return i1;
        }
        return i1+i2;
    }

    /**
     * 返回浮点型累加数量
     * @param values 多个相加的值
     * @return 结果
     */
    public static Float addAllFloat(Float ...values){
        BigDecimal od = new BigDecimal(0.0);
        for(int i=0;i<values.length;i++){
            BigDecimal nd = new BigDecimal(values[i]);
            od = addDecimal(od,nd);
        }
        return od.floatValue();
    }

    /**
     * 返回整数型累加数量
     * @param values 多个值
     * @return 结果
     */
    public static Integer addAllInteger(Integer ...values){
        BigDecimal od = new BigDecimal(0.0);
        for(int i=0;i<values.length;i++){
            BigDecimal nd = new BigDecimal(values[i]);
            od = addDecimal(od,nd);
        }
        return od.intValue();
    }
}
