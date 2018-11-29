package org.mountcloud.ffmepg.operation.ffmpeg.vidoe;


import org.mountcloud.ffmepg.annotation.FFCmd;
import org.mountcloud.ffmepg.operation.ffmpeg.FFMpegOperationBase;

/**
 * 视频转码操作 com.ugirls.ffmepg.operation.ffmpeg.vidoe 2018/6/6.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class FFMpegVideoFormatM3u8 extends FFMpegOperationBase {

	/**
	 * type
	 */
	@FFCmd(key = "-re")
	private String type = "";

	/**
	 * 视频文件
	 */
	@FFCmd(key = "-i")
	private String videoFileName;

	/**
	 * vcodec
	 */
	@FFCmd(key = "-vcodec")
	private String vcodec = "libx264";

	/**
	 * acodec
	 */
	@FFCmd(key = "-acodec")
	private String acodec = "copy";

	/**
	 * 分辨率大小 1366x768
	 */
	@FFCmd(key = "-s")
	private String size;

	/**
	 * 比特率
	 */
	@FFCmd(key = "-b:v")
	private String bitrate;

	@FFCmd(key = "-force_key_frames")
	private String forceKeyFrames;

	@FFCmd(key = "-f")
	private String formate = "segment";

	@FFCmd(key = "-segment_list_type")
	private String listType = "m3u8";

	@FFCmd(key = "-segment_list_size")
	private String listSize = "0";

	@FFCmd(key = "-segment_time")
	private String time;

	@FFCmd(key = "-segment_time_delta")
	private String timeDelta = "0.1";

	@FFCmd(key = "-segment_list")
	private String m3u8File;

	@FFCmd
	private String tsFiles;

	public void setTimes(Integer time) {
		//this.forceKeyFrames = "\"expr:gte(t,n_forced*" + time + ")\"";
		this.time = String.valueOf(Double.parseDouble(time.toString()));
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public String getVcodec() {
		return vcodec;
	}

	public void setVcodec(String vcodec) {
		this.vcodec = vcodec;
	}

	public String getAcodec() {
		return acodec;
	}

	public void setAcodec(String acodec) {
		this.acodec = acodec;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBitrate() {
		return bitrate;
	}

	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}

	public String getForceKeyFrames() {
		return forceKeyFrames;
	}

	public void setForceKeyFrames(String forceKeyFrames) {
		this.forceKeyFrames = forceKeyFrames;
	}

	public String getFormate() {
		return formate;
	}

	public void setFormate(String formate) {
		this.formate = formate;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getListSize() {
		return listSize;
	}

	public void setListSize(String listSize) {
		this.listSize = listSize;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeDelta() {
		return timeDelta;
	}

	public void setTimeDelta(String timeDelta) {
		this.timeDelta = timeDelta;
	}

	public String getM3u8File() {
		return m3u8File;
	}

	public void setM3u8File(String m3u8File) {
		this.m3u8File = m3u8File;
	}

	public String getTsFiles() {
		return tsFiles;
	}

	public void setTsFiles(String tsFiles) {
		this.tsFiles = tsFiles;
	}
}