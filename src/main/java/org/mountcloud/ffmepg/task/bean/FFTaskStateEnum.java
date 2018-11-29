package org.mountcloud.ffmepg.task.bean;

/**
 * 任务状态枚举
 * com.ugirls.ffmepg.task.bean
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public enum FFTaskStateEnum {

    /**
     * 队列
     */
    QUEUE(2),
    /**
     * 进行中
     */
    START(1),
    /**
     * 已完成
     */
    COMPLETE(0),
    /**
     * 失败
     */
    FAILED(-1);

    private Integer val = null;

    FFTaskStateEnum(int i) {
        this.val = i;
    }

    /**
     * 获取枚举
     * @param i 值
     * @return 枚举
     */
    public static FFTaskStateEnum getEnum(int i){
        switch (i){
            case 2:
                return QUEUE;
            case 1:
                return START;
            case 0:
                return COMPLETE;
        }
        return FAILED;
    }

    /**
     * 返回值
     * @return 值
     */
    public int getValue(){
        return val;
    }
}
