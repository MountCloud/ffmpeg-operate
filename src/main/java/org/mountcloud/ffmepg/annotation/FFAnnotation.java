package org.mountcloud.ffmepg.annotation;

/**
 * ffmpeg的注解，用于注解属性
 * com.ugirls.ffmepg.annotation
 * 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFAnnotation{

    /**
     * 注解的key
     */
    private String key;

    /**
     * 注解的值
     */
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
