package org.mountcloud.ffmepg.result.defaultResult;

/**
 * 视频信息结果
 * com.ugirls.ffmepg.result.defaultResult
 * 2018/6/12.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFVideoInfoResult extends FFDefaultResult{


    /**
     * 秒数
     */
    private Integer timeLengthSec;

    /**
     * 时长
     */
    private String timeLength;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 比特率
     */
    private String bitrate;


    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;

    /**
     * fps
     */
    private String fps;

    /**
     * tbr
     */
    private String tbr;

    /**
     * tbn
     */
    private String tbn;

    /**
     * tbc
     */
    private String tbc;

    public FFVideoInfoResult() {
        super("");
    }

    public FFVideoInfoResult(String resultStr) {
        super(resultStr);
    }

    public Integer getTimeLengthSec() {
        return timeLengthSec;
    }

    public void setTimeLengthSec(Integer timeLengthSec) {
        this.timeLengthSec = timeLengthSec;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFps() {
        return fps;
    }

    public void setFps(String fps) {
        this.fps = fps;
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    public String getTbn() {
        return tbn;
    }

    public void setTbn(String tbn) {
        this.tbn = tbn;
    }

    public String getTbc() {
        return tbc;
    }

    public void setTbc(String tbc) {
        this.tbc = tbc;
    }
}
