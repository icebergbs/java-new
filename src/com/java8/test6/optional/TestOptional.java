package com.java8.test6.optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class TestOptional {
    private static final Logger logger = LoggerFactory.getLogger(TestOptional.class);

    public static void main(String[] args) {
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        //创建Optional实例
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        User user = null;
        //null 抛异常
        //Optional<User> opt = Optional.of(user);

        //可以是null  也可以是非null
        Optional<User> opt1 = Optional.ofNullable(user);
    }

    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        //访问对象值   空时报异常
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);
        Assert.assertEquals("John", opt.get());
    }

    @Test
    public void whenCheckIfPresent_thenOk() {
        //先验证是否有值
        User user = new User();
        user.setEmail("john@gmail.com");
        user.setName("1234");
        Optional<User> opt = Optional.ofNullable(user);

        //是否有值 is
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(user.getEmail(), opt.get().getEmail());

        //if 检验是否有值的另一个选择，ifPresent()接受一个Consumer,对象不为空执行Lambda表达式
        opt.ifPresent(u -> Assert.assertEquals(user.getEmail(), u.getEmail()));

    }

    @Test
    public void whenEmptyValue_thenReturnDefault() {
        //返回默认值 第一个方法是 orElse()
        User user = null;
        User user2 = new User();
        user2.setEmail("1213@163.com");
        User res = Optional.ofNullable(user).orElse(user2);
        Assert.assertEquals(user2.getEmail(), res.getEmail());

        user = new User();
        user.setEmail("111@163.com");
        User res2 = Optional.ofNullable(user).orElse(user2);
        Assert.assertEquals(user.getEmail(), res2.getEmail());

        //第二个同类型的 API 是 orElseGet() —— 其行为略有不同
        user = null;
        User res3 = Optional.ofNullable(user).orElseGet(() -> user2);
        Assert.assertEquals(res3.getEmail(), user2.getEmail());


        //orElse() 和 orElseGet() 的不同之处
        //User user4 = null;
        User user4 = new User("1233@163.com");
        logger.info("Using orElse");
        User res4 = Optional.ofNullable(user4).orElse(createUser());
        logger.info("Using orElseGet");
        User res5 = Optional.ofNullable(user4).orElseGet(() -> createUser());


    }

    private User createUser() {
        logger.info("create User");
        return new User();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {

        //返回异常
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    /**
     * 转换值
     */
    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail())
                .orElse("defalut@163.com");
        Assert.assertEquals(email, user.getEmail());

        user.setPosition("Developer");
//        String position = Optional.ofNullable(user)
//                .flatMap(u -> u.getEmail()).orElse("default");
//        Assert.assertEquals(position, user.getPosition().get());
    }


}
