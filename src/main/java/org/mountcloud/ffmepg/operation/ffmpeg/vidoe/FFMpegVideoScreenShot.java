package org.mountcloud.ffmepg.operation.ffmpeg.vidoe;


import org.mountcloud.ffmepg.annotation.FFCmd;
import org.mountcloud.ffmepg.operation.ffmpeg.FFMpegOperationBase;

/**
 * 视频截图操作
 * com.ugirls.ffmepg.operation.ffmpeg.vidoe
 * 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMpegVideoScreenShot extends FFMpegOperationBase {

    /**
     * 视频截图操作
     * @param video 视频地址
     * @param startTime 截图开始时间
     * @param vframes 帧数
     * @param image 输出文件路径
     */
    public FFMpegVideoScreenShot(String video,String startTime, String vframes, String image) {
        this.video = video;
        this.startTime = startTime;
        this.vframes = vframes;
        this.image = image;
    }

    /**
     * 输入的视频文件
     */
    @FFCmd(key = "-i")
    private String video;

    /**
     * 输出的图片是否覆盖形式生成，如果为空则不添加该key
     */
    @FFCmd(key = "-y")
    private String isOverWrite = "";

    /**
     * 图片输出的格式 默认 image2
     */
    @FFCmd(key = "-f")
    private String imageFormat = "image2";

    /**
     * 开始时间秒
     */
    @FFCmd(key = "-ss")
    private String startTime;

    /**
     * 时间
     */
    @FFCmd(key = "-t")
    private String time;

    /**
     * 帧数
     */
    @FFCmd(key = "-vframes")
    private String vframes;

    /**
     * 截图的分辨率大小  例如：1020x960
     */
    @FFCmd(key = "-s")
    private String size;

    /**
     * 输出文件
     */
    @FFCmd
    private String image;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getIsOverWrite() {
        return isOverWrite;
    }

    public void setIsOverWrite(String isOverWrite) {
        this.isOverWrite = isOverWrite;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVframes() {
        return vframes;
    }

    public void setVframes(String vframes) {
        this.vframes = vframes;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
