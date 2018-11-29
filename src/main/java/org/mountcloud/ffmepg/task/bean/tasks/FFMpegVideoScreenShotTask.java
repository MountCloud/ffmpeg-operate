package org.mountcloud.ffmepg.task.bean.tasks;

import org.mountcloud.ffmepg.operation.ffmpeg.vidoe.FFMpegVideoScreenShot;
import org.mountcloud.ffmepg.task.bean.FFVideoTask;

/**
 * 视频截图任务
 * com.ugirls.ffmepg.task.bean.tasks
 * 2018/6/12.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMpegVideoScreenShotTask extends FFVideoTask<FFMpegVideoScreenShot> {

    public FFMpegVideoScreenShotTask(FFMpegVideoScreenShot operation) {
        super(operation);
    }

    @Override
    public void callBackResultLine(String line) {

    }

    @Override
    public void callExecStart() {

    }

    @Override
    public void callExecEnd() {

    }
}
