package com.wang.study.base;

/**
 * 测试{@link String}类的相关功能
 */
class StringMain {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        /*
         * 在堆内存中创建一个String对象，引用s1指向堆内存中的对象，但引用s1本身是在栈内存中创建，类似于c中的指针
         *
         * 此句代码实际创建了两个对象，先将字符串abc放到常量池，然后new了一份新的字符串abc放到堆内存中，
         * （字符串常量abc在编译期就放到了常量池中，而堆上的abc是在运行期初始化阶段才new出来的）
         */
        String s1 = new String("abc");
        //从常量池中获取String对象
        String s2 = "abc";
        System.out.println("s1 == s2 ? " + (s1 == s2));          //false ，因为一个是堆内存中的String对象，一个是常量池中的String对象，所以其地址不同
        System.out.println("s1.equasl(s2) ? " + s1.equals(s2));  //true  , equals比较的是字符串的内容
        /*
         * intern() 方法的作用是：
         *          如果运行时常量池中存在一个和此String对象内容一致的字符串，那么返回常量池中该字符串的引用
         *          如果不存在，那么在常量池中创建一个与此String对象内容一致的字符串，然后返回常量池中创建字符串的引用
         */
        String s3 = s1.intern();
        System.out.println("s2 == s3 ? " + (s2 == s3));          //true  都是从常量池中获取的字符串，只要内容一致，它们都指向相同的字符串，所以地址相同

        System.out.println("=========================");

        String s4 = "ab";       //常量池中的对象
        String s5 = "cd";
        String s6 = "abcd";
        String s7 = "ab" + "cd";//常量池中的对象
        String s8 = s4 + s5;    //在堆上创建的对象
        System.out.println("s6 == s7 ? " + (s6 == s7));          //true
        System.out.println("s6 == s8 ? " + (s6 == s8));          //false

        System.out.println("=========================");

    }

}
