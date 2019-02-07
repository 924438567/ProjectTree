package cn.yueshutong.springprojecttree.config.annotation;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
@ComponentScan(basePackages = "cn.yueshutong.springprojecttree")
@EntityScan(basePackages = "cn.yueshutong.springprojecttree.database.entity")
@EnableJpaRepositories(basePackages = "cn.yueshutong.springprojecttree.database.dao")
@ImportResource(value = "classpath:spring/spring-aop-pt.xml")
public @interface EnableProjectTree {
    String value() default "";
}
