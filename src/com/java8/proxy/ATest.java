package com.java8.proxy;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author bingshan
 * @date 2021/12/20 20:14
 */
public class ATest {
    public static void main(String[] args) throws NoSuchMethodException, CannotCompileException, NotFoundException {

        createProxy();
    }

    /*
     * 手动创建字节码
     */
    private static void createProxy() throws NotFoundException, CannotCompileException, NoSuchMethodException {

        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.makeClass("com.java8.proxy.StationProxy");

        //设置接口
        CtClass interface1 = pool.get("com.java8.proxy.ATicketService");
        cc.setInterfaces(new CtClass[]{interface1});

        //设置Field
        CtField field = CtField.make("private com.java8.proxy.AStation station;", cc);
        cc.addField(field);

        CtClass stationClass = pool.get("com.java8.proxy.AStation");
        CtClass[] arrays = new CtClass[]{stationClass};
        CtConstructor ctc = CtNewConstructor.make(arrays, null, CtNewConstructor.PASS_NONE, null, null, cc);
        //设置构造函数内部信息
        ctc.setBody("this.station=$1;");
        cc.addConstructor(ctc);

        //创建收取手续 takeHandlingFee（）
        CtMethod takeHandlingFee = CtMethod.make("private void takeHandlingFee() {}", cc);
        takeHandlingFee.setBody("System.out.println(\"收取手续费，打印发票。。。。。\");");
        cc.addMethod(takeHandlingFee);

        //创建showAlertInfo 方法
        CtMethod showInfo = CtMethod.make("private void showAlertInfo(String info) {}", cc);
        showInfo.setBody("System.out.println($1);");
        cc.addMethod(showInfo);

        //sellTicket
        CtMethod sellTicket = CtMethod.make("public void sellTicket(){}", cc);
        sellTicket.setBody("{this.showAlertInfo(\"××××您正在使用车票代售点进行购票，每张票将会收取5元手续费！××××\");"
                + "station.sellTicket();"
                + "this.takeHandlingFee();"
                + "this.showAlertInfo(\"××××欢迎您的光临，再见！××××\");}");
        cc.addMethod(sellTicket);


        //添加inquire方法
        CtMethod inquire = CtMethod.make("public void inquire() {}", cc);
        inquire.setBody("{this.showAlertInfo(\"××××欢迎光临本代售点，问询服务不会收取任何费用，本问询信息仅供参考，具体信息以车站真实数据为准！××××\");"
                + "station.inquire();"
                + "this.showAlertInfo(\"××××欢迎您的光临，再见！××××\");}"
        );
        cc.addMethod(inquire);

        //添加widthraw方法
        CtMethod withdraw = CtMethod.make("public void withdraw() {}", cc);
        withdraw.setBody("{this.showAlertInfo(\"××××欢迎光临本代售点，退票除了扣除票额的20%外，本代理处额外加收2元手续费！××××\");"
                + "station.withdraw();"
                + "this.takeHandlingFee();}"
        );
        cc.addMethod(withdraw);

        //获取动态生成的class
        Class c = cc.toClass();
        //获取构造器
        Constructor constructor = c.getConstructor(AStation.class);
        //通过构造器实例化
        try {
            ATicketService o = (ATicketService) constructor.newInstance(new AStation());
            o.inquire();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            cc.writeFile("./src");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
