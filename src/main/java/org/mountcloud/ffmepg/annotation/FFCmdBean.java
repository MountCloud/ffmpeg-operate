package org.mountcloud.ffmepg.annotation;

import java.util.List;

/**
 * 命令的父类
 * com.ugirls.ffmepg.annotation
 * 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFCmdBean {

    private FFAnnotation cmdName;

    private List<FFAnnotation> cmdParameter;

    public FFAnnotation getCmdName() {
        return cmdName;
    }

    public void setCmdName(FFAnnotation cmdName) {
        this.cmdName = cmdName;
    }

    public List<FFAnnotation> getCmdParameter() {
        return cmdParameter;
    }

    public void setCmdParameter(List<FFAnnotation> cmdParameter) {
        this.cmdParameter = cmdParameter;
    }
}
