package io.critsu.repositories;

import io.critsu.mapper.ArticleMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalTools {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addLikeCountWithPessimisticLockForArticle(ArticleMapper articleMapper, long aid, int likeCount) {
        Integer num = articleMapper.getArticleLikeCount4Update(aid);
        if (num == null) return -1;
        num = articleMapper.setArticleLikeCount(num + likeCount, aid);
        return num;
    }

    @Transactional
    public void testTransactional(ArticleMapper articleMapper, long aid) {
        articleMapper.setArticleLikeCount(100, aid);
        throw new RuntimeException();
    }


}
