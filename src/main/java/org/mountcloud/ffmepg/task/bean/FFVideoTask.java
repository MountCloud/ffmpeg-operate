package org.mountcloud.ffmepg.task.bean;

import org.mountcloud.ffmepg.operation.FFOperationBase;
import org.mountcloud.ffmepg.util.FFVideoUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 视频操作任务父类
 * com.ugirls.ffmepg.task.bean
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class FFVideoTask<T extends FFOperationBase> extends FFTask<T>{

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
     * 正则
     */
    private String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";

    /**
     * 正则模式
     */
    private Pattern pattern = Pattern.compile(regexDuration);


    public FFVideoTask(T operation){
        super(operation);
    }

    /**
     * 设置结果
     * @param line 一行结果
     */
    @Override
    public void callRsultLine(String line) {

        //获取视频信息
        Matcher m = pattern.matcher(line.trim());
        if (m.find()) {
            timeLength = m.group(1);
            if(timeLength!=null){
                timeLengthSec = FFVideoUtil.getTimelen(timeLength);
            }
            startTime = m.group(2);
            bitrate = m.group(3);
        }
        callBackResultLine(line);
    }

    /**
     * 子类需要返回的
     * @param line 一行结果
     */
    public abstract void callBackResultLine(String line);

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

    public Integer getTimeLengthSec() {
        return timeLengthSec;
    }

    public void setTimeLengthSec(Integer timeLengthSec) {
        this.timeLengthSec = timeLengthSec;
    }

    //格式:"00:00:10.68"
    protected int getTimelen(String timelen){
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
