

>工程介绍:    
　　java8 是Java 8 学习。   
　　JDK: 1.8  
　　数据库：   
　　技术框架：  
    

>分包说明：  
　　algorithm  算法  
　　　　codinginterviewguide  算法面试指南
　　book  书籍学习  
　　　　artofconcurrency  并发编程的艺术  
　　　　easycoding  Ali Java开发手册  
　　　　jcip   xx手册  
　　design  设计模式  
　　java8   Java 8   
　　utils   工具             


>开发规范：  
　　接口规范    
　　　　1. 参照Restful API 接口设计规范  
　　　　2.
　　命名规范    
　　　　1. 类名、参数名驼峰命名  
　　　　2. 包名小写、常量大写  
　　　　3. 不允许使用任何魔法值  
　　　　4. 请求参数接收；查询接口统一使用 Query对象，新增、修改接口统一使用 DTO对象  
　　　　5. 响应体统一以 BO对象  
　　　　6. 数据库对象、表相关统一以 Entity对象  
　　方法名称  
　　　　| 方法   | HTTP 方法映射      |  
　　　　| :---- | :----------------:|  
　　　　|list   | GET <集合URL>    | ------    
　　　　|get    | GET <资源URL>    | ------   
　　　　|create | POST <集合URL>   | ------   
　　　　|update | PUT  <资源URL>   | ------   
　　　　|delete | DELETE <资源URL> | ------   