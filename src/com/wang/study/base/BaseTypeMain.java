package com.wang.study.base;

/**
 * Java基本数据类型的包装类中，使用了缓存技术的有：
 * {@link Integer}
 * {@link Character}
 * {@link Boolean}
 * {@link Byte}
 * {@link Short}
 * {@link Long}
 * 除了Boolean以外，其它的包装类缓存范围为 [-128 , 127]，超出此范围会创建新的对象
 * <p>
 * 未使用缓存技术的有：
 * {@link Float}
 * {@link Double}
 */
class BaseTypeMain {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        /*
         * 在编译的时候 Integer i1 = 40; 这句代码是会直接封装为 Integer i1 = Integer.valueOf(40)；
         * 又因为40在[-128 , 127]这个范围内，所以会直接使用缓存中的对象
         */
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1 = i2       ? " + (i1 == i2));           //true ，都是使用缓存中的对象
        /*
         * 因为 i1、i2、i3都是Integer对象，而对象无法进行 + 操作，所以在执行 i2 + i3 时会对 i2、i3进行自动拆箱操作，所以此时
         * Integer i1 == Integer i2 + Integer i3  等价于  Integer i1 == 40 + 0 (表达式1),
         * 此时表达式1左边是Integer对象，右边是数值40，无法进行 == 操作，所以此时会对表达式1的左边 i1 进行自动拆箱操作，所以此时
         * 表达式1 等价于 40 == 40 + 0
         * 所以最终，就相当于比较值，返回 true
         */
        System.out.println("i1 = i2 + i3  ? " + (i1 == i2 + i3));      //true
        System.out.println("i1 = i4       ? " + (i1 == i4));           //false, 一个是使用缓存中的对象，一个是new的对象，地址不同，所以返回false
        System.out.println("i4 = i5       ? " + (i4 == i5));           //false，两个都是new出来的对象，地址不同，所以返回false
        System.out.println("i4 = i5 + i6  ? " + (i4 == i5 + i6));      //true
        System.out.println("40 = i5 + i6  ? " + (40 == i5 + i6));      //true
    }

}
