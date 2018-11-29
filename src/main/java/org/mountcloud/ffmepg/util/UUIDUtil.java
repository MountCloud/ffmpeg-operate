package org.mountcloud.ffmepg.util;

import java.util.UUID;

/**
 * UUID工具
 * @author zhanghaishan
 * @version V1.0
 * com.ugirls.util
 * 2017/12/28.
 */
public class UUIDUtil {

    /**
     * 返回一个全UUID
     * @return 完全UUID
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 返回一个没有减号的UUID
     * @return 精简后的UUID
     */
    public static String getUUIDSimpl(){
        UUID uuid  =  UUID.randomUUID();
        return uuid.toString().replaceAll("\\-", "");
    }
}
