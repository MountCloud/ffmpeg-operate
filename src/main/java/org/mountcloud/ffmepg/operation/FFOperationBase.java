package org.mountcloud.ffmepg.operation;


import org.mountcloud.ffmepg.annotation.FFAnnotation;
import org.mountcloud.ffmepg.annotation.FFCmdBean;
import org.mountcloud.ffmepg.excption.FFMpegOperationConvertExcption;
import org.mountcloud.ffmepg.util.FFAnnotationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 * com.ugirls.ffmepg.operation
 * 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class FFOperationBase {

    private String stringvalue = null;
    private String command = null;
    private List<String> commandParams = new ArrayList<>();

    /**
     * 返回命令
     * @return
     */
    public String getCommand(){
        if(command == null){
            command = toString();
        }
        return command;
    }

    /**
     * 返回命令参数
     * @return
     */
    public List<String> getCommandParams(){
        if(commandParams.size()==0){
            toString();
        }
        return commandParams;
    }


    /**
     * 直接转成命令
     * @return
     */
    @Override
    public String toString() {

        String str = null;
        FFAnnotationUtil ffAnnotationUtil = new FFAnnotationUtil();
        try {
            FFCmdBean cmdBean = ffAnnotationUtil.getClassAnnocation(this);
            String execname = command = cmdBean.getCmdName().getKey();
            List<FFAnnotation> list = cmdBean.getCmdParameter();

            str = execname;

            for(int i=0;i<list.size();i++){
                FFAnnotation annotation = list.get(i);
                String aKey = annotation.getKey();
                String aValue = annotation.getValue();

                if(aKey!=null&&aKey.length()>0&&aValue!=null){
                    str =  str +" "+aKey;
                    commandParams.add(aKey);
                }

                if(aValue!=null&&aValue.length()>0){
                    str =  str +" "+aValue;
                    commandParams.add(aValue);
                }
            }

        } catch (IllegalAccessException e) {
            str = null;
        }
        if(str==null){
            throw new FFMpegOperationConvertExcption("FFMpegOperation To String Is Null!");
        }

        stringvalue = str;

        return stringvalue;
    }
}
