package org.mountcloud.ffmepg.task.bean;

/**
 * 任务状态
 * com.ugirls.ffmepg.task
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFTaskProgress {

    public FFTaskProgress(){
        this.progress = 0;
        this.state = FFTaskStateEnum.QUEUE;
    }

    private int progress;

    private FFTaskStateEnum state;

    public FFTaskStateEnum getState() {
        return state;
    }

    public void setState(FFTaskStateEnum state) {
        this.state = state;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
