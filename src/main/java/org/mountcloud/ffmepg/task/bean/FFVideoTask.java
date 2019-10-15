package org.mountcloud.ffmepg.task.bean;


import org.mountcloud.ffmepg.operation.FFOperationBase;
import org.mountcloud.ffmepg.util.FFVideoUtil;
import org.mountcloud.ffmepg.util.StringUtil;

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

    /**
     * 正则
     */
    private String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*)";


    /**
     * 尺寸
     */
    private String sizeDuration = "Stream (.*?): Video: (.*?) \\d*x\\d*";

    /**
     * 正则模式
     */
    private Pattern pattern = Pattern.compile(regexDuration);

    /**
     * 正则模式
     */
    private Pattern sizePattern = Pattern.compile(sizeDuration);


    public FFVideoTask(T operation){
        super(operation);
    }

    /**
     * 设置结果
     * @param line 一行结果
     */
    @Override
    public void callRsultLine(String line) {

        //获取视频时长信息
        //Duration: 00:03:38.80, start: 1.427433, bitrate: 0 kb/s
        Matcher m = pattern.matcher(line.trim());
        if (m.find()) {
            timeLength = m.group(1);
            if(timeLength!=null){
                timeLengthSec = FFVideoUtil.getTimelen(timeLength);
            }
            startTime = m.group(2);
            bitrate = m.group(3);
        }

        /**
         * 获取视频尺寸
         */
        //Stream #0:0: Video: h264 (High) ([27][0][0][0] / 0x001B), yuv420p, 1024x576 [SAR 1:1 DAR 16:9], 20 fps, 20 tbr, 90k tbn, 40 tbc
        Matcher sizem = sizePattern.matcher(line.trim());
        if(sizem.find()){
            findSize(line);
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

    /**
     * 获取长度的信息
     * @param line
     */
    private void findSize(String line){

        String sizeRegs = ", \\d*x\\d*";
        String sizeResul = StringUtil.findStringsByRegsOne(sizeRegs,line);
        sizeResul = sizeResul.replace(", ","");
        String[] size = sizeResul.split("x");
        String w = size[0];
        String h = size[1];
        setWidth(w);
        setHeight(h);

        String fpsRegs = "\\d* fps";
        String fpsResul = StringUtil.findStringsByRegsOne(fpsRegs,line);
        if(fpsResul!=null){
            fpsResul = fpsResul.replace(" fps","");
            setFps(fpsResul);
        }

        String tbrRegs = "\\d* tbr";
        String tbrResul = StringUtil.findStringsByRegsOne(tbrRegs,line);
        if(tbrResul!=null){
            tbrResul = tbrResul.replace(" tbr","");
            setTbr(tbrResul);
        }

        String tbnRegs = "[0-9a-z]* tbn";
        String tbnResul = StringUtil.findStringsByRegsOne(tbnRegs,line);
        if(tbnResul!=null){
            tbnResul = tbnResul.replace(" tbn","");
            setTbn(tbnResul);
        }

        String tbcRegs = "\\d* tbc";
        String tbcResul = StringUtil.findStringsByRegsOne(tbcRegs,line);
        if(tbcResul!=null){
            tbcResul = tbcResul.replace(" tbc","");
            setTbc(tbcResul);
        }

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
