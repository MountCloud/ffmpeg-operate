package org.mountcloud.ffmepg.excption;

/**
 * 未找到异常
 * org.mountcloud.ffmepg.excption
 * 2018/11/29.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMpegOperationNotFoundExcption extends RuntimeException {
    public FFMpegOperationNotFoundExcption(String ex){
        super(ex);
    }
}
