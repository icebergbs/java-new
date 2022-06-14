package com.java8.jvm.c2_classloader;

public class T008_LazyLoading {

    //�ϸ�Ӧ�ý�laz initialzing  ��Ϊjava������淶��û���ϸ�涨ʲôʱ��
    //����loading  ���ϸ�涨��ʲôʱ��initialzing
    public static void main(String[] args) throws ClassNotFoundException {
        //û��new û�з��ʲ��ᱻ����
        //P p;

        //new �˻ᱻ����
        //X x = new  X();

        //��ӡfinal ֵ����Ҫ����������
        //System.out.println(P.i);

        //��final�������
        //System.out.println(P.j);

        //�ᱻ����
        Class.forName("com.java8.jvm.c2_classloader.T008_LazyLoading$P");
    }

    public static class P {
        final static int i = 8;
        static int j = 9;

        static {
            //ֻҪ�����ع����Pһ���Ǳ���ӡ�����ģ���Ϊһ��classload �ڴ�֮�������⼸������
            //1 Loading
            //2 Linking
            //3 Initlalizing ������̻�ִ�о�̬���飬 ���б�load�����������һ����ִ�й���
            //pһ����ӡ��֤�����class��load����
            System.out.println("P");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X");
        }
    }
}
