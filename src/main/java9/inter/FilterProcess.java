package main.java9.inter;

/**
 * @author bingshan
 * @date 2022/5/6 13:40
 */
public interface FilterProcess<T> {
    //java 7 及以前 特性 全局常量 和抽象方法
    public static final String a ="22";    boolean process(T t);
    //java 8 特性 静态方法和默认方法
    default void love(){
        System.out.println("java8 特性默认方法");
    }    static void haha(){
        System.out.println("java8 特性静态方法");
    }
    //java 9 特性 支持私有方法
    private void java9(){}
}
