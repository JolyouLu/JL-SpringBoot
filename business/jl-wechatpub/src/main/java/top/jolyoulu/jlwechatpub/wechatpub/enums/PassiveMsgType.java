package top.jolyoulu.jlwechatpub.wechatpub.enums;

import java.util.StringJoiner;

/**
 * @Author: LZJ
 * @Date: 2020/4/13 16:01
 * @Version 1.0
 * 公众号消息类型
 */
public enum PassiveMsgType {

    /**文本消息*/
    TEXT_TYPE("text"),
    /**图片消息*/
    IMAGE_TYPE("image"),
    /**语音消息*/
    VOICE_TYPE("voice"),
    /**视频消息*/
    VIDEO_TYPE("video"),
    /**音乐消息*/
    MUSIC_TYPE("music"),
    /**图文消息*/
    NEWS_TYPE("news");

    private String type;

    private PassiveMsgType(String type) {
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
        return new StringJoiner(", ", PassiveMsgType.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .toString();
    }
}
