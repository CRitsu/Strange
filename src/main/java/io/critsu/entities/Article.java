package io.critsu.entities;

import java.sql.Timestamp;
import java.util.List;

public class Article {

    private long userId;
    private long articleId;
    private Timestamp createdTime;
    private String articleBody;
    private String image;
    /**
     * like数 默认位0
     */
    private int likeCount = 0;
    /**
     * 删除flag默认为0；不从DB取值；删除时设置为1
     */
    private int deleteFlg = 0;
    private List<Comment> comments;

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
                ", comments=" + comments +
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
