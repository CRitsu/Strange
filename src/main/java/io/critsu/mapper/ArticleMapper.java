package io.critsu.mapper;

import io.critsu.entities.Article;
import io.critsu.entities.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    /**
     * 通过文章id获得指定的文章<br>
     * 将附带指定长度的最新评论<br>
     * 在size为null时默认为20
     * @param aid 文章id
     * @param size 评论拉取长度
     * @return 查询结果；查无此文为null
     */
    Article getArticle(@Param("articleId") long aid, @Param("size") Integer size);

    /**
     * 通过文章id获得评论数
     * @param aid 文章id
     * @return 评论数（无评论为0）
     */
    Integer getCommentsCount(long aid);

    /**
     * 添加一条评论
     * @param comment 添加的评论
     * @return 影响行数
     */
    int addComment(Comment comment);

    /**
     * 添加多条评论（复数）
     * @param comments 评论list
     * @return 影响行数
     */
    int addComments(List<Comment> comments);

    /**
     * 获取指定文章的评论<br>
     * 获得最新评论时cid设置为null<br>
     * 获取旧评论时需要设置搜索起点评论id（cid）<br>
     * 可以指定拉取的最大长度（size）<br>
     * 在size为null时默认为20
     * @param aid 指定文章id
     * @param cid 检查起始点的评论id
     * @param size 拉取长度
     * @return 评论list；注意0件时返回空list（不存在返回null的情况）
     */
    List<Comment> getComments(@Param("articleId") long aid, @Param("startId") Long cid, @Param("size") Integer size);

    /**
     * 获得最新的文章<br>
     * 可指定起始文章id，若指定将拉取指定id以前的文章（不包括指定id）<br>
     * 默认拉取最新文章（指定null时）<br>
     * 可指定拉取文章长度，若指定长度则最多拉取指定长度的文章<br>
     * 默认为20（指定null时）<br>
     * 可指定拉取文章附带评论的长度<br>
     * 默认也为20（指定null时）
     * @param aid 检查起始点的文章id
     * @param aSize 拉取文章长度
     * @param cSize 拉取评论长度
     * @return 文章list；无可拉取文章时
     */
    List<Article> getArticles(@Param("startId") Long aid,@Param("articleSize") Integer aSize, @Param("commentSize") Integer cSize);

    /**
     * 强制更改指定文章的赞数
     * @param likeCount 修改后的赞数
     * @param aid 指定文章id
     * @return 影响行数
     */
    int setArticleLikeCount(@Param("likeCount") int likeCount, @Param("articleId") long aid);

    /**
     * 获得指定文章的赞数
     * @param aid 指定文章id
     * @return 赞数或无此文章时为null
     */
    Integer getArticleLikeCount(long aid);

    /**
     * 获得指定文章的赞数，悲观锁
     * @param aid 指定文章id
     * @return 赞数或无此文章时为null
     */
    Integer getArticleLikeCount4Update(long aid);

    /**
     * 更新指定文章的赞数<br>
     * 可以添加赞数，可以减少赞数<br>
     * 参数不能为null
     * @param likeCount 待更新赞数
     * @param aid 指定文章id
     * @param expectedCount 预期数据库中存在的赞数
     * @return 影响行数
     */
    int setArticleLikeCountWithOptimisticLock(@Param("likeCount") int likeCount, @Param("articleId") long aid, @Param("expectedCount") int expectedCount);

    /**
     * 获得指定评论的赞数
     * @param cid 指定评论
     * @return 影响行数
     */
    Integer getCommentLikeCount(long cid);

    /**
     * 强制更改指定评论的赞数
     * @param likeCount 修改后的赞数
     * @param cid 指定评论id
     * @return 影响行数
     */
    int setCommentLikeCount(@Param("likeCount") int likeCount, @Param("commentId") long cid);

    /**
     * 更新指定评论的赞数<br>
     * 可以添加赞数，可以减少赞数<br>
     * 参数不能为null
     * @param likeCount 待更新赞数
     * @param cid 指定评论id
     * @param expectedCount 预期数据库中存在的赞数
     * @return 影响行数
     */
    int setCommentLikeCountWithOptimisticLock(@Param("likeCount") int likeCount, @Param("commentId") long cid, @Param("expectedCount") int expectedCount);


    /**
     * 添加文章
     * @param article 文章
     * @return 影响行数
     */
    int addArticle(Article article);

    /**
     * 删除指定的文章
     * @param aid 文章id
     * @return 影响行数
     */
    int removeArticle(long aid);

    /**
     * 标记文章<br>
     * 标记为0则正常；标记为1则待删除
     * @param aid 文章id
     * @param flg 标记flg
     * @return 影响行数
     */
    int markArticle(@Param("articleId") long aid, @Param("deleteFlg") int flg);

    /**
     * 标记评论<br>
     * 标记为0则正常；标记为1则待删除
     * @param cid 评论id
     * @param flg 标记flg
     * @return 影响行数
     */
    int markComment(@Param("commentId") long cid, @Param("deleteFlg") int flg);

    /**
     * 删除指定评论
     * @param cid 评论id
     * @return 影响行数
     */
    int removeComment(long cid);

    /**
     * 获得指定评论
     * @param cid 指定评论id
     * @return 指定评论；null无此评论
     */
    Comment getComment(long cid);
}
