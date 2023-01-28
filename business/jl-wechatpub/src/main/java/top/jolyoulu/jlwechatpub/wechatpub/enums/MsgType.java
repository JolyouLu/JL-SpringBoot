package top.jolyoulu.jlwechatpub.wechatpub.enums;

import java.util.StringJoiner;

/**
 * @Author: LZJ
 * @Date: 2020/4/13 16:01
 * @Version 1.0
 * 公众号消息类型
 */
public enum MsgType {

    /**文本消息*/
    TEXT_TYPE("text"),
    /**图片消息*/
    IMAGE_TYPE("image"),
    /**语音消息*/
    VOICE_TYPE("voice"),
    /**视频消息*/
    VIDEO_TYPE("video"),
    /**小视频消息*/
    SHORT_VIDEO_TYPE("shortvideo"),
    /**地理位置消息*/
    LOCATION_TYPE("location"),
    /**链接消息*/
    LINK_TYPE("link"),
    /**事件消息*/
    EVENT_TYPE("event");

    private String type;

    private MsgType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MsgType.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .toString();
    }
}
