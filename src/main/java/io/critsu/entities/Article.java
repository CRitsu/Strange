package io.critsu.entities;

import java.sql.Timestamp;

public class Article {

    private long userId;
    private long articleId;
    private Timestamp createdTime;
    private int likeCount;
    private String articleBody;
    private String image;
    private int deleteFlg;

    @Override
    public String toString() {
        return "Article{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                ", createdTime=" + createdTime +
                ", likeCount=" + likeCount +
                ", articleBody='" + articleBody + '\'' +
                ", image='" + image + '\'' +
                ", deleteFlg=" + deleteFlg +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(int deleteFlg) {
        this.deleteFlg = deleteFlg;
    }
}
