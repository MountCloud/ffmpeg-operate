package org.mountcloud.ffmepg.task.bean;

import org.mountcloud.ffmepg.operation.FFOperationBase;
import org.mountcloud.ffmepg.task.context.FFTaskContext;
import org.mountcloud.ffmepg.task.threads.FFThread;
import org.mountcloud.ffmepg.util.FFTerminalCreater;
import org.mountcloud.ffmepg.util.UUIDUtil;

import java.io.IOException;
import java.util.Date;

/**
 * 任务父类
 * com.ugirls.ffmepg.task
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class FFTask<T extends FFOperationBase> implements FFThread {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 执行结果的全部内容
     */
    protected StringBuffer result = new StringBuffer("");

    /**
     * 内容操作
     */
    protected T operationBase;

    /**
     * 任务进度
     */
    private FFTaskProgress progress;

    /**
     * 任务命令行
     */
    private FFTerminalCreater.FFTerminal terminal = null;

    /**
     * 任务创建时间
     */
    private Date createTime;

    @Override
    public void run() {

        //执行开始前回调
        callExecStart();

        //任务开始
        progress.setState(FFTaskStateEnum.START);

        //执行的命令
        String cmd = operationBase.toString();

        //任务执行状态
        boolean state = true;

        try {
            terminal = FFTerminalCreater.getCreater().getTerminal(cmd);

            //结果
            String str = null;
            while ((str=terminal.readErrorLine())!=null){
                result.append(str);
                callRsultLine(str);
            }

        } catch (IOException e) {
            state =false;
        }

        //设置状态
        if(state){
            progress.setState(FFTaskStateEnum.COMPLETE);
        } else {
            progress.setState(FFTaskStateEnum.FAILED);
        }
        progress.setProgress(100);
        FFTaskContext.getContext().removeTask(this.getTaskId());
        //执行结束回调
        callExecEnd();
    }

    /**
     * 执行开始
     */
    public abstract void callExecStart();

    /**
     * 正确结果行
     * @param line 一行结果
     */
    public abstract void callRsultLine(String line);

    /**
     * 执行结束
     */
    public abstract void callExecEnd();


    /**
     * 任务构造
     * @param operation 操作
     */
    public FFTask(T operation){
        this.operationBase = operation;
        this.taskId = UUIDUtil.getUUIDSimpl();
        this.createTime = new Date();
        this.progress = new FFTaskProgress();
    }

    public FFTask(){
        this.progress = new FFTaskProgress();
    }

    public String getTaskId() {
        return taskId;
    }

    public FFTaskProgress getProgress() {
        return progress;
    }

    public void setProgress(FFTaskProgress progress) {
        this.progress = progress;
    }


}
