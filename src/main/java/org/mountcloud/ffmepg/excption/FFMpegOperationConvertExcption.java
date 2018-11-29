package org.mountcloud.ffmepg.excption;

/**
 * 操作转换异常
 * org.mountcloud.ffmepg.excption
 * 2018/11/29.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMpegOperationConvertExcption extends RuntimeException{

    public FFMpegOperationConvertExcption(String ex){
        super(ex);
    }

}
