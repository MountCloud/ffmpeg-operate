package org.mountcloud.ffmepg.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 字符串工具集
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.ffmepg.util
 * 2018/1/18.
 */
public class StringUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 转Integer
     * @param obj 待转
     * @return 结果
     */
    public static Integer toInteger(Object obj){
        if(obj == null){
            return null;
        }
        return Integer.parseInt(obj.toString());
    }

    /**
     * 转Float
     * @param obj 待转
     * @return 结果
     */
    public static Float toFloat(Object obj){
        if(obj == null){
            return null;
        }
        return Float.parseFloat(obj.toString());
    }

    /**
     * 转Double
     * @param obj 待转
     * @return 结果
     */
    public static Double toDouble(Object obj){
        if(obj == null){
            return null;
        }
        return Double.parseDouble(obj.toString());
    }

    /**
     * 转Long
     * @param obj 待转
     * @return 结果
     */
    public static Long toLong(Object obj){
        if(obj == null){
            return null;
        }
        return Long.parseLong(obj.toString());
    }

    /**
     * 转Boolean
     * @param obj 待转
     * @return 结果
     */
    public static Boolean toBoolean(Object obj){
        if(obj == null){
            return null;
        }
        return Boolean.parseBoolean(obj.toString());
    }

    /**
     * 转String
     * @param obj 待转
     * @return 结果
     */
    public static String objToString(Object obj){
        if(obj == null){
            return null;
        }
        return obj.toString();
    }

    /**
     * 转String
     * @param date 待转
     * @return 结果
     */
    public static String dataToDate(Date date){
        return sdf.format(date);
    }

    /**
     * 根据map替换
     * @param str 需要转的
     * @param map map一次转多个，比如  abc=ccc  eee=ddd
     * @return 结果
     */
    public static String replaceByMap(String str,Map<String,String> map){
        if(map!=null){
            Set<String> keys = map.keySet();
            for(String key : keys){
                String val = map.get(key);
                str = str.replaceAll(key,val);
            }
        }
        return str;
    }

    /**
     * 提取字符串里的数字
     * @param str 字符串
     * @return 数字
     */
    public static String findNumber(String str){
        String regEx="[^0-9]";
        String newStr = findString(str,regEx);
        return newStr;
    }

    /**
     * 根据正则提取字符串
     * @param str 字符串
     * @param regEx 正则
     * @return 结果
     */
    public static String findString(String str,String regEx){
        if(str==null){
            return null;
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String newStr =  m.replaceAll("").trim();
        return newStr;
    }

    /**
     * 根据需要复制的字符串长度进行复制拼接字符串
     * @param str 字符串
     * @param cloneNum 需要拼接的次数
     * @return 结果
     */
    public static String cloneAndAppend(String str,int cloneNum){
        String newStr = str;
        for(int i=0;i<cloneNum;i++){
            newStr = newStr + newStr;
        }
        return newStr;
    }

    /**
     * 查询字符串
     * @param regex 正则
     * @param str 字符串
     * @return 结果
     */
    public static List<String> findStringsByRegs(String regex, String str){
        List<String> strs = new ArrayList<>();
        Pattern r = Pattern.compile(regex);
        Matcher ma = r.matcher(str);
        while (ma.find()){
            String findStr = ma.group(0);
            if(findStr!=null&&findStr.length()>0){
                strs.add(findStr);
            }
        }
        return strs;
    }

    /**
     * 查询字符串
     * @param regex 正则
     * @param str 字符串
     * @return 结果
     */
    public static String findStringsByRegsOne(String regex, String str){
        List<String> strs = findStringsByRegs(regex,str);
        String result = strs.size()==0? null : strs.get(0);
        return result;
    }
}
