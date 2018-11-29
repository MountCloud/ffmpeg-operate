package org.mountcloud.ffmepg.task.bean.tasks;

import org.mountcloud.ffmepg.operation.ffmpeg.vidoe.FFMpegVideoFormatM3u8;
import org.mountcloud.ffmepg.task.bean.FFVideoTask;
import org.mountcloud.ffmepg.util.FFBigDecimalUtil;
import org.mountcloud.ffmepg.util.FFVideoUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 视频转m3u8任务
 * com.ugirls.ffmepg.task.bean.tasks
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMepgVideoFormatM3u8Task extends FFVideoTask<FFMpegVideoFormatM3u8> {

    /**
     * 进度正则查询
     */
    private String frameRegexDuration = "frame=([\\s,\\d]*) fps=(.*?) q=(.*?) size=([\\s\\S]*) time=(.*?) bitrate=([\\s\\S]*) speed=(.*?)x";

    /**
     * 正则模式
     */
    private Pattern framePattern = Pattern.compile(frameRegexDuration);

    @Override
    public void callExecStart() {
    }

    @Override
    public void callExecEnd() {
    }

    /**
     * 任务构造
     * @param format
     */
    public FFMepgVideoFormatM3u8Task(FFMpegVideoFormatM3u8 format){
        super(format);
    }

    /**
     * 回调
     * @param line
     */
    @Override
    public void callBackResultLine(String line) {

        if(super.getTimeLengthSec()!=null){
            //获取视频信息
            Matcher m = framePattern.matcher(line);
            if(m.find()){
                try {
                    String execTimeStr = m.group(5);
                    int execTimeInt = FFVideoUtil.getTimelen(execTimeStr);
                    double devnum = FFBigDecimalUtil.div(execTimeInt,super.getTimeLengthSec(),5);
                    double progressDouble = FFBigDecimalUtil.mul(devnum,100);
                    super.getProgress().setProgress((int) progressDouble);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
