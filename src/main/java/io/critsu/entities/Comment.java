package io.critsu.entities;

import java.sql.Timestamp;

public class Comment {

    private long articleId;
    private long userId;
    private long commentId;
    private Timestamp postTime;
    private String commentBody;
    /**
     * like数 默认位0
     */
    private int likeCount = 0;
    /**
     * 删除flag默认为0；不从DB取值；删除时设置为1
     */
    private int deleteFlg = 0;

    @Override
    public String toString() {
        return "Comment{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", postTime=" + postTime +
                ", likeCount=" + likeCount +
                ", commentBody='" + commentBody + '\'' +
                ", deleteFlg=" + deleteFlg +
                '}';
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }
}
