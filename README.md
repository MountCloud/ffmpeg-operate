# USE 使用

	<dependency>
		<groupId>org.mountcloud</groupId>
		<artifactId>ffmpeg-operate</artifactId>
		<version>1.0</version>
	</dependency>

# English

## Description
&nbsp;&nbsp;The prerequisite for this project is that FFMPEG is installed in the environment and FFMPEG commands can be used in the console. This project is very convenient for java to use FFMPEG function.

&nbsp;&nbsp;There are 3 built-in video operations in the project, 1: video format is changed to M3U8 (FFMpegVideoFormatM3u8), 2: view video attribute (FFMpegVideoInfo), 3: video screenshot (FFMpegVideoScreenShot). It can also be extended by inheriting FFMpegOperationBase.

## Features
1: You can get the execution percentage progress of the task.

2: Very high scalability.

3: Multi-threaded mode of operation.


# 中文

## 描述

&nbsp;&nbsp;这个项目使用的前提条件是环境中安装了FFMPEG，并且可以在控制台中使用FFMPEG命令。本项目极大方便了java使用FFMPEG功能。

&nbsp;&nbsp;项目中内置了3个对视频的操作，1：视频格式转为M3U8（FFMpegVideoFormatM3u8），2：查看视频属性（FFMpegVideoInfo），3：视频截图（FFMpegVideoScreenShot）。还可以通过继承FFMpegOperationBase来进行扩展。

## 特点

1：可以获取任务的执行百分比进度。

2：非常高的可扩展性。

3：多线程方式进行操作。


# Demo 用例


    @Test
    public void convertM3u8() {
		
		//create result bean
        FFVideoInfoResult result = new FFVideoInfoResult();

		//find video info
        FFMpegVideoInfo ffMpegVideoInfo = new FFMpegVideoInfo();
        ffMpegVideoInfo.setVideoUrl("D:\\cma_15307640036trzll1p.mp4");
        FFMepgVideoInfoTask videoInfoTask = new FFMepgVideoInfoTask(result,ffMpegVideoInfo);

        FFTaskContext.getContext().submit(videoInfoTask,null);

        String bitrate = "5286k";

        //create to m3u8 operation
        FFMpegVideoFormatM3u8 m3u8Operation = new FFMpegVideoFormatM3u8();
        m3u8Operation.setVideoFileName("D:\\cma_15307640036trzll1p.mp4");
        m3u8Operation.setBitrate(bitrate);
        m3u8Operation.setTimes(5);
        m3u8Operation.setM3u8File("D:\\cma_15307640036trzll1p\\cma_15307640036trzll1p.m3u8");
        m3u8Operation.setTsFiles("D:\\cma_15307640036trzll1p\\cma_15307640036trzll1p%5d.ts");

		//to m3u8 task
        FFMepgVideoFormatM3u8Task task = new FFMepgVideoFormatM3u8Task(m3u8Operation);

 		//add task
        FFTaskContext.getContext().addTask(task);

        while(!task.getProgress().getState().equals(FFTaskStateEnum.COMPLETE)){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }

        System.out.println("COMPLETE");

    }

## note 笔记
1: FFTaskContext.getContext().addTask(task) is an asynchronous task, FFTaskContext.getContext().submit(taks) is a synchronous task.

1：FFTaskContext.getContext().addTask(task) 为异步任务，FFTaskContext.getContext().submit(taks)为同步任务