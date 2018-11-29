package org.mountcloud.ffmepg.util;

/**
 * 视频工具
 * com.ugirls.ffmepg.util
 * 2018/6/12.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFVideoUtil {

    /**
     * 获取时长
     * @param timelen 整个时长的文本
     * @return 时长
     */
    public static int getTimelen(String timelen){
        int min=0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min+=Integer.valueOf(strs[0])*60*60;//秒
        }
        if(strs[1].compareTo("0")>0){
            min+=Integer.valueOf(strs[1])*60;
        }
        if(strs[2].compareTo("0")>0){
            min+=Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }

}
