package io.critsu.entities;

import java.sql.Timestamp;

public class Friend {

    private long userId;
    private long friendId;
    private int blackFlg = 0;
    private Timestamp addTime;
    private User info;

    @Override
    public String toString() {
        return "Friend{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", blackFlg=" + blackFlg +
                ", addTime=" + addTime +
                ", info=" + info +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public int getBlackFlg() {
        return blackFlg;
    }

    public void setBlackFlg(int blackFlg) {
        this.blackFlg = blackFlg;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public User getInfo() {
        return info;
    }

    public void setInfo(User info) {
        this.info = info;
    }
}
