package org.mountcloud.ffmepg.task.context;

import org.mountcloud.ffmepg.task.bean.FFTask;
import org.mountcloud.ffmepg.task.threads.FFThreadManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务上下文
 * com.ugirls.ffmepg.task
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFTaskContext {

    /**
     * 任务列表锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 上下文单例
     */
    private static FFTaskContext context;

    /**
     * 任务线程池
     */
    private FFThreadManager.ThreadPool ffThreadPool;

    /**
     * 任务列表
     */
    private Map<String,FFTask> taskMap = new HashMap<String,FFTask>();

    /**
     * 添加一个任务
     * @param task 任务
     */
    public void addTask(FFTask task){
        try{
            lock.lock();
            taskMap.put(task.getTaskId(),task);
        }finally {
            lock.unlock();
        }
        ffThreadPool.execute(task);
    }

    /**
     * 提交一个任务
     * @param task 提交任务
     */
    public void submit(FFTask task){
        try{
            lock.lock();
            taskMap.put(task.getTaskId(),task);
        }finally {
            lock.unlock();
        }

        try {
            ffThreadPool.submit(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交一个任务
     * @param task 提交任务
     * @param bean 数据
     * @param <T> 数据
     * @return 执行结果
     */
    public <T> T submit(FFTask task,T bean){
        T data = null;

        try{
            lock.lock();
            taskMap.put(task.getTaskId(),task);
        }finally {
            lock.unlock();
        }

        try {
            data = ffThreadPool.submit(task,bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 任务ID
     * @param taskId 任务id
     */
    public void removeTask(String taskId){
        try{
            lock.lock();
            taskMap.remove(taskId);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 提供上下文
     * @return 上下文
     */
    public static FFTaskContext getContext(){
        if(context == null){
            context = new FFTaskContext();
        }
        return context;
    }

    /**
     * 私密的上下文
     */
    private FFTaskContext(){
        ffThreadPool = FFThreadManager.getInstance();
    }

}
