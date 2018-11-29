package org.mountcloud.ffmepg.task.threads;

import java.util.concurrent.*;

/**
 * 任务的线程池
 * com.ugirls.ffmepg.task.threads
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFThreadManager {

   public static ThreadPool instance;

    // 获取单例的线程池对象
    public static ThreadPool getInstance() {
        if (instance == null) {
            synchronized (FFThreadManager.class) {
                if (instance == null) {
                    int cpuNum = Runtime.getRuntime().availableProcessors();// 获取处理器数量
                    int threadNum = cpuNum * 2 + 1;// 根据cpu数量,计算出合理的线程并发数
                    instance = new ThreadPool(threadNum-1, threadNum, Integer.MAX_VALUE);//默认是双核的cpu 每个核心走一个线程 一个等待线程
                }
            }
        }
        return instance;
    }

    public static class ThreadPool {
        private ThreadPoolExecutor mExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;

            mExecutor = new ThreadPoolExecutor(corePoolSize,// 核心线程数
                    maximumPoolSize, // 最大线程数
                    keepAliveTime, // 闲置线程存活时间
                    TimeUnit.MILLISECONDS,// 时间单位
                    new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),// 线程队列
                    Executors.defaultThreadFactory(),// 线程工厂
                    new ThreadPoolExecutor.AbortPolicy() {// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                            super.rejectedExecution(r, e);
                        }
                    }
            );
        }

        public void execute(Runnable runnable) {
            if (runnable == null) {
                return;
            }
            mExecutor.execute(runnable);
        }

        /**
         * 提交一个可返回值的线程
         * @param runnable 线程
         * @param bean 结果类
         * @param <T> 类型
         * @return 返回结果
         * @throws ExecutionException 提交异常
         * @throws InterruptedException 提交异常
         */
        public <T> T submit(Runnable runnable,T bean) throws ExecutionException, InterruptedException {
            if (runnable == null) {
                return null;
            }
            Future<T> future = mExecutor.submit(runnable,bean);
            return future.get();
        }

        /**
         * 提交一个可返回值的线程
         * @param runnable 线程
         * @return 返回结果
         */
        public void submit(Runnable runnable){
            if (runnable == null) {
                return;
            }
            mExecutor.submit(runnable);
        }


        // 从线程队列中移除对象
        public void cancel(Runnable runnable) {
            if (mExecutor != null) {
                mExecutor.getQueue().remove(runnable);
            }
        }
    }

}
