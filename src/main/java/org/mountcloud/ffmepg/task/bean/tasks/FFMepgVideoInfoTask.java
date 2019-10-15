package org.mountcloud.ffmepg.task.bean.tasks;


import org.mountcloud.ffmepg.operation.ffmpeg.vidoe.FFMpegVideoInfo;
import org.mountcloud.ffmepg.result.defaultResult.FFVideoInfoResult;
import org.mountcloud.ffmepg.task.bean.FFVideoTask;

/**
 * 视频信息查询任务
 * com.ugirls.ffmepg.task.bean.tasks
 * 2018/6/12.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMepgVideoInfoTask extends FFVideoTask<FFMpegVideoInfo> {

    private FFVideoInfoResult data;

    public FFMepgVideoInfoTask(FFMpegVideoInfo operation) {
        super(operation);
    }

    public FFMepgVideoInfoTask(FFVideoInfoResult result,FFMpegVideoInfo operation) {
        super(operation);
        this.data = result;
    }

    @Override
    public void callExecStart() {

    }

    @Override
    public void callBackResultLine(String line) {

    }

    @Override
    public void callExecEnd() {
        if(data==null){
            //返回结果
            data = new FFVideoInfoResult(result.toString());
        }

        //结果
        data.setTimeLengthSec(getTimeLengthSec());
        data.setTimeLength(getTimeLength());
        data.setStartTime(getStartTime());
        data.setBitrate(getBitrate());
        data.setWidth(getWidth());
        data.setHeight(getHeight());
        data.setFps(getFps());
        data.setTbr(getTbr());
        data.setTbn(getTbn());
        data.setTbc(getTbc());
    }

    public FFVideoInfoResult getData() {
        return data;
    }

    public void setData(FFVideoInfoResult data) {
        this.data = data;
    }
}
