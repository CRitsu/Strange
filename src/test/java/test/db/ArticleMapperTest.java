package test.db;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.Article;
import io.critsu.entities.Comment;
import io.critsu.mapper.ArticleMapper;
import io.critsu.repositories.TransactionalTools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.critsu.constant.StrangeDefinition.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TransactionalTools tools;

    private long aid = 1000000L;
    private long cid = 10000L;
    private int forCreateData = 0;

    @Before
    public void createDate() {
        Integer count = articleMapper.getCommentsCount(aid + forCreateData);
        assertNotNull(count);
        if (count > 20) return;
        Comment comment = new Comment();
        comment.setUserId(10000L);
        comment.setCommentBody("COMMENT_TEST_BODY_ONE");
        comment.setLikeCount(0);
        comment.setArticleId(aid + forCreateData);
        count = articleMapper.addComment(comment);
        assertEquals(1, count.intValue());
        List<Comment> comments = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN);

        for (int i = 0; i < 100; i++) {
            Comment tem = new Comment();
            tem.setUserId(10000L + i);
            tem.setCommentBody("COMMENT_TEST_BODY_" + i);
            tem.setLikeCount(i);
            tem.setArticleId(aid + forCreateData);
            tem.setPostTime(Timestamp.valueOf(simpleDateFormat.format(new Date().getTime())));
            comments.add(tem);
        }
        count = articleMapper.addComments(comments);
        assertEquals(100, count.intValue());

        if (++forCreateData < 3) {
            createDate();
        }

    }

    @Test
    public void testGetArticle() {
        Article article = articleMapper.getArticle(0L, null);
        assertNull(article);

        article = articleMapper.getArticle(aid, 10);
        assertNotNull(article);
        assertEquals(10, article.getComments().size());

        article = articleMapper.getArticle(aid, 20);
        assertNotNull(article);
        assertEquals(20, article.getComments().size());

        System.out.println(article);
        System.out.println(article.getComments());

    }

    @Test
    public void testGetComments() {
        List<Comment> comments = articleMapper.getComments(aid, null, null);
        assertEquals(20, comments.size());

        comments = articleMapper.getComments(aid, 10097L, 10);
        assertEquals(10096L, comments.get(0).getCommentId());

        System.out.println(comments);
    }

    @Test
    public void testGetArticles() {
        List<Article> articles = articleMapper.getArticles(null, null, null);
        assertEquals(3, articles.size());
        for (Article a :
                articles) {
            assertEquals(20, a.getComments().size());
        }
        System.out.println(articles);

        articles = articleMapper.getArticles(1000002L, 1, 10);
        assertEquals(1, articles.size());
        for (Article a :
                articles) {
            assertEquals(10, a.getComments().size());
        }
        System.out.println(articles);
    }

    private int flg = 0;
    private int times = 1000;
    private long waits = 100L;
    @Test
    public void testSetAndUpdateArticleLikeCount() {
        flg = 0;


        Integer num = articleMapper.setArticleLikeCount(0, aid);
        assertEquals(1, num.intValue());
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(0, num.intValue());

        // first try
        num = articleMapper.setArticleLikeCountWithOptimisticLock(num + 1, aid, num);
        assertEquals(1, num.intValue());

        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(1, num.intValue());


        for (int i = 0; i < times; i++) {
            Runnable runnable = () -> {
                    Integer tem = articleMapper.getArticleLikeCount(aid);
                    while (0 == articleMapper.setArticleLikeCountWithOptimisticLock(tem + 1, aid, tem)) {
                        try {
                            Thread.sleep(waits);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tem = articleMapper.getArticleLikeCount(aid);
                    }
                    synchronized (this) {
                        flg++;
                    }
            };
            new Thread(runnable).start();
        }
        while (flg < times) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(flg);
        }
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(times + 1, num.intValue());
    }

    @Test
    public void testUpdateArticleLikeCountWithPessimisticLock() {
        flg = 0;
        Integer num = articleMapper.setArticleLikeCount(0, aid);
        assertEquals(1, num.intValue());
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(0, num.intValue());

        // first try
        num = articleMapper.setArticleLikeCountWithOptimisticLock(num + 1, aid, num);
        assertEquals(1, num.intValue());

        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(1, num.intValue());

        for (int i = 0; i < times; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep(waits);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int tem = tools.addLikeCountWithPessimisticLockForArticle(articleMapper, aid, 1);
                assertEquals(1, tem);
                synchronized (this) {
                    flg++;
                }
            };
            new Thread(runnable).start();
        }
        while (flg < times) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(flg);
        }
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(times + 1, num.intValue());
    }

    @Test
    public void testRemoveArticleLikeCount() {
        flg = 0;

        Integer num = articleMapper.setArticleLikeCount(times, aid);
        assertEquals(1, num.intValue());
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(times, num.intValue());


        for (int i = 0; i < times; i++) {
            Runnable runnable = () -> {
                Integer tem = articleMapper.getArticleLikeCount(aid);
                while (0 == articleMapper.setArticleLikeCountWithOptimisticLock(tem - 1, aid, tem)) {
                    try {
                        Thread.sleep(waits);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tem = articleMapper.getArticleLikeCount(aid);
                }
                synchronized (this) {
                    flg++;
                }
            };
            new Thread(runnable).start();
        }
        while (flg < times) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(flg);
        }
        num = articleMapper.getArticleLikeCount(aid);
        assertEquals(0, num.intValue());
    }

    @Test
    public void testAddAndRemoveCommentLikeCount() {
        flg = 0;

        Integer num = articleMapper.setCommentLikeCount(0, cid);
        assertEquals(1, num.intValue());
        num = articleMapper.getCommentLikeCount(cid);
        assertEquals(0, num.intValue());


        for (int i = 0; i < times; i++) {
            Runnable runnable = () -> {
                Integer tem = articleMapper.getCommentLikeCount(cid);
                while (0 == articleMapper.setCommentLikeCountWithOptimisticLock(tem + 1, cid, tem)) {
                    try {
                        Thread.sleep(waits);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tem = articleMapper.getCommentLikeCount(cid);
                }
                synchronized (this) {
                    flg++;
                }
            };
            new Thread(runnable).start();
        }
        while (flg < times) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(flg);
        }
        num = articleMapper.getCommentLikeCount(cid);
        assertEquals(times, num.intValue());

        flg = 0;

        for (int i = 0; i < times; i++) {
            Runnable runnable = () -> {
                Integer tem = articleMapper.getCommentLikeCount(cid);
                while (0 == articleMapper.setCommentLikeCountWithOptimisticLock(tem - 1, cid, tem)) {
                    try {
                        Thread.sleep(waits);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tem = articleMapper.getCommentLikeCount(cid);
                }
                synchronized (this) {
                    flg++;
                }
            };
            new Thread(runnable).start();
        }

        while (flg < times) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num = articleMapper.getCommentLikeCount(cid);
        assertEquals(0, num.intValue());

    }


    @Test
    public void simpleTest() {
        try {
            tools.testTransactional(articleMapper, aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddAndRemoveArticle() {
        Article article = new Article();
        article.setUserId(10000L);
        article.setArticleBody("TEST_ADD_ARTICLE");
        article.setImage("TEST_IMAGE_PATH");
        article.setCreatedTime(new Timestamp(new Date().getTime()));

        Integer num = articleMapper.addArticle(article);
        assertEquals(1, num.intValue());
        assertNotEquals(0, article.getArticleId());
        assertNotNull(articleMapper.getArticle(article.getArticleId(), 0));

        num = articleMapper.removeArticle(article.getArticleId());
        assertEquals(1, num.intValue());
        assertNull(articleMapper.getArticle(article.getArticleId(), 0));
    }


    @Test
    public void testMarkArticle() {
        long temp = 1000002L;
        Integer num = articleMapper.markArticle(temp, DELETE_FLAG_TRUE);
        assertEquals(1, num.intValue());
        assertNull(articleMapper.getArticle(temp, 1));
        num = articleMapper.markArticle(temp, DELETE_FLAG_FALSE);
        assertEquals(1, num.intValue());
        assertNotNull(articleMapper.getArticle(temp, 1));

    }

    @Test
    public void testMarkComment() {
        long temp = 10000L;
        Integer num = articleMapper.markComment(temp, DELETE_FLAG_FALSE);
        assertEquals(1, num.intValue());

        Comment comment = articleMapper.getComment(temp);
        assertNotNull(comment);
        System.out.println(comment.toString());

        num = articleMapper.markComment(temp, DELETE_FLAG_TRUE);
        assertEquals(1, num.intValue());

        comment = articleMapper.getComment(temp);
        assertNull(comment);

        num = articleMapper.markComment(temp, DELETE_FLAG_FALSE);
        assertEquals(1, num.intValue());


    }


}
