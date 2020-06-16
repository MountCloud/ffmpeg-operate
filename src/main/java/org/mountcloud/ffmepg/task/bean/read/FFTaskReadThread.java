package org.mountcloud.ffmepg.task.bean.read;

import org.mountcloud.ffmepg.task.bean.FFTask;
import org.mountcloud.ffmepg.task.threads.FFThread;

import java.io.IOException;

/**
 * 任务读取异常线程
 * com.ugirls.ffmepg.task.bean.read
 * 2020/6/16.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFTaskReadThread implements FFThread {

    /**
     * 任务
     */
    private FFTask<?> ffTask;

    /**
     * 读取类型
     */
    private FFTaskReadType readType;

    /**
     * 构造一个读取线程
     * @param ffTask 任务
     * @param readType 读取类型
     */
    public FFTaskReadThread(FFTask<?> ffTask,FFTaskReadType readType){
        this.ffTask = ffTask;
        this.readType = readType;
    }

    @Override
    public void run() {
        //结果
        String str = null;
        boolean state = false;
        try {
            if(readType.equals(FFTaskReadType.ERROR_IN)){
                while ((str=ffTask.getTerminal().readErrorLine())!=null){
                    ffTask.putResultLine(str);
                }
            }else{
                while ((str=ffTask.getTerminal().readLine())!=null){
                    ffTask.putResultLine(str);
                }
            }
            state = true;
        }catch (IOException e){
            state = false;
            e.printStackTrace();
        }
        //结束了
        FFTaskReadState readState = ffTask.getReadState(this.getReadType());
        readState.setEnd(true);
        readState.setState(state);
    }

    public FFTaskReadType getReadType(){
        return this.readType;
    }

}
