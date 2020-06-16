package org.mountcloud.ffmepg.task.bean.read;

/**
 * 任务读取状态
 * com.ugirls.ffmepg.task.bean.read
 * 2020/6/16.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFTaskReadState {

    /**
     * 状态，true是成功，false是失败
     */
    private Boolean state = false;

    /**
     * 是否结束，true是结束，false是进行中
     */
    private Boolean end = false;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean isEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }
}
