# Spring 练习笔记

1. AspectJ表达式过滤的方法貌似只有显式调用才会触发。

eg：

1）MediaPlayer类有这个方法  
private cd;   
public void playTheCd() {  
    this.cd.paly();  
}  
测试类中调用  
public void test() {  
    MediaPlayer player = ac.getBean("player");  
    player.playTheCd();  
}  
此时Aspect中@Pointcut做下面定义没有效果  
@Pointcut("execution(** springtest.CompactDisc.play(..))")  
需要定位到MediaPlayer才有用  
@Pointcut("execution(** springtest.MediaPlayer.playTheCd(..))")  



