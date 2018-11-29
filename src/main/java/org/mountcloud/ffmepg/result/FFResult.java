package org.mountcloud.ffmepg.result;

/**
 * FFMpeg结果
 * com.ugirls.ffmepg.result
 * 2018/6/7.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class FFResult {

    /**
     * 结果的字符串
     */
    private String resultString;

    /**
     * 异常字符串
     */
    private String error;

    public FFResult(String resultStr){
        this.resultString = resultStr;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
