package org.mountcloud.ffmepg.util;

import java.io.*;
import java.util.List;

/**
 * 命令行生成器
 * com.ugirls.ffmepg.util.terminal
 * 2018/6/11.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFTerminalCreater {

    private static FFTerminalCreater creater = null;

    public static FFTerminalCreater getCreater(){
        if(creater == null){
            creater = new FFTerminalCreater();
        }
        return creater;
    }

    /**
     * 获取一个命令的命令对象
     * @param cmd 命令
     * @return 命令对象
     * @throws IOException 异常
     */
    public FFTerminal getTerminal(String cmd) throws IOException {

        String[] cmds = new String[3];

        if(OSUtils.WINDOWS){
            cmds[0] = "cmd";
            cmds[1] = "/c";
        }else{
            cmds[0] = "/bin/sh";
            cmds[1] = "-c";
        }

        cmds[2] = cmd;

        FFTerminal ffTerminal = new FFTerminal( Runtime.getRuntime().exec(cmd));
        return ffTerminal;
    }

    /**
     * 获取一个命令的命令对象
     * @param cmd 命令
     * @return 命令工具
     * @throws IOException 异常
     */
    public FFTerminal getTerminal(String[] cmd) throws IOException {
        FFTerminal ffTerminal = new FFTerminal( Runtime.getRuntime().exec(cmd));
        return ffTerminal;
    }

    /**
     * 获取一个命令的命令对象
     * @param cmd 命令
     * @param params 参数
     * @return 命令对象
     * @throws IOException 异常
     */
    public FFTerminal getTerminal(String cmd, List<String> params) throws IOException {
        String[] tempParams = new String[params.size()];
        for(int i=0;i<params.size();i++){
            String tp = params.get(i);
            tempParams[i] = tp;
        }
        FFTerminal ffTerminal = new FFTerminal( Runtime.getRuntime().exec(cmd,tempParams));
        return ffTerminal;
    }


    /**
     * 命令实例
     */
    public static class FFTerminal{

        private Process process;

        private InputStream inputStream;

        private InputStream errorInputStream;

        //private OutputStream outputStream;

        private BufferedReader errorBufferedReader;
        private BufferedReader bufferedReader;

        private OutputStream outputStream;

        public FFTerminal(Process process){
            this.process = process;
            this.inputStream = process.getInputStream();
            this.errorInputStream = process.getErrorStream();
            this.outputStream = process.getOutputStream();
            //this.outputStream = process.getOutputStream();
            errorBufferedReader = new BufferedReader(new InputStreamReader(errorInputStream));
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }

        /**
         * 返回 Process
         * @return Process
         */
        public Process getProcess() {
            return process;
        }

        /**
         * 返回 InputStream
         * @return InputStream
         */
        public InputStream getInputStream() {
            return inputStream;
        }

        /**
         * 返回 ErrorInputStream
         * @return ErrorInputStream
         */
        public InputStream getErrorInputStream() {
            return errorInputStream;
        }

        /**
         * 键入流
         * @return OutputStream
         */
        public OutputStream getOutputStream() {
            return outputStream;
        }

        public BufferedReader getErrorBufferedReader() {
            return errorBufferedReader;
        }

        public BufferedReader getBufferedReader() {
            return bufferedReader;
        }

        /**
         * 读取一行
         * @return 一行的数据
         * @throws IOException 异常
         */
        public String readLine() throws IOException {
            String line = bufferedReader.readLine();
            return line;
        }

        /**
         * 从error中读取一行
         * @return 一行文本
         * @throws IOException 异常
         */
        public String readErrorLine() throws IOException {
            String line = errorBufferedReader.readLine();
            return line;
        }

        /**
         * 是否还存活
         * @return 是否存活
         */
        public boolean isAlive(){
            return process.isAlive();
        }

        /**
         * 退出
         */
        public void exit(){
            process.destroy();
        }

    }
}
