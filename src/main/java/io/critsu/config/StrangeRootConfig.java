package io.critsu.config;

import io.critsu.mapper.ManagementMapper;
import io.critsu.mapper.ArticleMapper;
import io.critsu.mapper.AuthorityMapper;
import io.critsu.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({"io.critsu.repositories","io.critsu.services"})
@ImportResource("classpath:configuration/mybatis-spring.xml")
public class StrangeRootConfig {

    @Bean("UserMapper")
    public UserMapper getUserMapper(SqlSessionTemplate session) {
        return session.getMapper(UserMapper.class);
    }

    @Bean("ArticleMapper")
    public ArticleMapper getArticleMapper(SqlSessionTemplate session) {
        return session.getMapper(ArticleMapper.class);
    }

    @Bean("ManagementMapper")
    public ManagementMapper getAdminToolMapper(SqlSessionTemplate session) {
        return session.getMapper(ManagementMapper.class);
    }

    @Bean("AuthorityMapper")
    public AuthorityMapper getAuthorityMapper(SqlSessionTemplate session) {
        return session.getMapper(AuthorityMapper.class);
    }

}
