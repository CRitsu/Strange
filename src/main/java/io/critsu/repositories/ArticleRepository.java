package io.critsu.repositories;

import io.critsu.entities.Article;

public interface ArticleRepository {

    /**
     * 获得指定文章，拉取一定评论<br>
     * 评论件数又属性值设定
     * @param aid 文章id
     * @return 文章
     */
    Article getArticle(long aid);

}
