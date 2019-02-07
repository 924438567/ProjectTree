
![](./picture/220619.png)

很多新人进入一家新公司后，最头疼的就是如何快速了解公司的业务和项目架构。

因为文档很少，没有文档，或者是文档严重落伍， 根本没法看；如果你碰到一个特别热心的老员工，事无巨细地给你讲，随时在你身边答疑解惑， 那简直是天大的好运气， 现实是大家都很忙，没人给你讲解。

很快就要深入项目做开发了，怎么办呢？

我在加入新公司后，就遇到了悲催的情况。而且在一个多月时间里，我依旧没有熟悉项目细节。于是，我就诞生了做一款可视化流程分析插件的想法！帮助更多像我一样刚进公司对接手的项目摸不着头脑的新人快速熟悉业务流程！

## 下载使用

项目暂时没有加入Maven中央仓库，所以还需要你clone或download到本地。

在本地计算机进入项目的pom.xml文件所在目录，打开命令行（CMD）,执行install命令安装到本地。

```
mvn install
```

以后使用时，只需要导入依赖

```
<dependency>
    <groupId>cn.yueshutong</groupId>
    <artifactId>spring-project-tree</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Spring Boot安装

如果你的项目是Spring Boot，那么使用会非常简单，只需要两步。

**第一步**：

在SpingBootApplication启动类注解`@EnableProjectTree`开启功能

**第二步**：

在main函数里首先运行`ProjectTree.make()`方法，参数为[pointcut](https://www.baidu.com/baidu?isource=infinity&iname=baidu&itype=web&tn=98012088_9_dg&ch=7&ie=utf-8&wd=%40pointcut%20%E8%A1%A8%E8%BE%BE%E5%BC%8F)表达式，完整代码如下：

```
@SpringBootApplication
@EnableProjectTree //1
public class SpringbootApplication {

    public static void main(String[] args) {
        ProjectTree.make("execution(* cn.yueshutong.springprojecttree..*(..))");//2
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
```

注：重构后的Project Tree不止增加了对分布式接口的监控，还增加了pointcut表示式。

### 访问ProjectTree

启动你的项目，首先访问你项目的某个接口，使其执行被监控的方法。然后访问`localhost:8080/projecttree`查看网页。

#### 接口说明

| 接口                         | 说明                 |
| ---------------------------- | -------------------- |
| /projecttree                 | 返回完整调用链视图     |
| /projecttree/all             | 返回全部方法视图     |
| /json/projecttree            | JSON形式的返回结果   |
| /json/projecttree/{methodId} | 对某一方法的JSON结果 |

## 注意事项

使用Shiro、Spring Securit等安全框架时，需要注意对此URL的权限控制。

若遇到Jpa Dao层接口注入失败，Entity扫描失败这类问题，需要在你的启动类中使用下面两个注解：

```
@EntityScan(basePackages = "cn.yueshutong.springprojecttree.database.entity")
@EnableJpaRepositories(basePackages = "cn.yueshutong.springprojecttree.database.dao")
```

## 源码介绍

如何降低对主流程的性能消耗？

使用单例线程池实现异步非阻塞模型，降低对主流程的性能影响。

为什么使用单例线程池？

首先只有一个线程的线程池实际是队列+单线程，一个一个任务的进行执行，完全满足本插件的使用需求，对性能的影响也降到最低。

基于内存的内嵌数据库实现数据的快速读写，这里不用担心数据过大问题，本插件基于**方法调用链分析**进行选择性保存，理论上说，从接口开始，有多少流程分支，就有多少条数据记录。占用内存极小。

如何实现方法调用分析？

基于栈数据结构设计算法。

## 关于作者

博客：[http://www.yueshutong.cn](http://www.yueshutong.cn)

邮箱：yster@foxmail.com

Github：[https://github.com/yueshutong/Spring-Project-Tree](https://github.com/yueshutong/Spring-Project-Tree)

Gitee：[https://gitee.com/zyzpp/Spring-Project-Tree](https://gitee.com/zyzpp/Spring-Project-Tree)

