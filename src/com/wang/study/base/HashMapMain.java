package com.wang.study.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 本案例用来说明类{@link Object}中 hashCode()与equals()方法的作用
 * <p>
 * 在使用HashMap时，当我们使用自定义的类作为键，如果不同时重写hashCode()和equals()方法，
 * 那么在读取数据时可能和我们预期的不一致。
 * <p>
 * HashMap底层维护了一个hash表(通过数组+链表实现，jdk1.8以后通过数组+链表+红黑树实现)，
 * <p>
 * 我们最终要实现和 85 ~ 89 行代码(使用String类作为键)所示的效果。
 * <p>
 * 对于 Key1 类 ( 95 ~ 98 行代码所示)
 * 当我们往HashMap里存放key1_1时，首先会调用Key1这个类的hashCode()方法来计算它hash值，随后把
 * key1_1存放到hash值所指向的内存位置，如果自定义的类没有重写hashCode()方法，则会调用Object的
 * hashCode()方法，然而Object的hashCode()方法返回的实际是key1_1的内存地址，然后key1_1与key1_2
 * 的内存地址不同，在hash表中查找不到键key1_2，所以最终输出：null。
 * <p>
 * 对于 Key2 类 ( 102 ~ 105 行代码所示)
 * 同上，因为Key2类重写了hashCode()方法，所以key2_1与key2_2的hash值一致，但调用map2.get(key2_2)
 * 时，虽然它们的hashCode()相同(即在数组中的同一个位置)，但无法确定它们的内容是否相等(hash值相同不代
 * 表内容也相同)，此时会调用Key2类的equals()方法来判断，但Key2类未重写该方法，那么就直接调用Object类
 * 的equals()方法，但是Object中的equals()方法比较的是内存地址，key2_1与key2_2的内存地址肯定不同，
 * 所以最终还是输出：null。
 * <p>
 * 对于 Key3 类 ( 109 ~ 112 行代码所示)
 * 即重写了hashCode()方法，也重写了equals()方法，满足所有条件，所以最终通过map2.get(key3_2)能得到最终
 * 结果，输出： key3_1。
 */
@SuppressWarnings("all")
public class HashMapMain {

    interface IEmpty {
        //do nothing...
    }

    private static class Key1 implements IEmpty {
        Integer id;

        public Key1(int id) {
            this.id = id;
        }
    }

    private static class key2 implements IEmpty {
        Integer id;

        public key2(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    private static class Key3 implements IEmpty {
        Integer id;

        public Key3(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Key3)) {
                return false;
            }

            return id.equals(((Key3) obj).id);
        }

    }

    public static void main(String[] args) {
        HashMap<Integer, String> map1 = new HashMap<>();
        Integer i1 = 1;
        Integer i2 = new Integer(1);
        map1.put(i1, "i1");
        System.out.println(map1.get(i2));                  //输出结果为：i1

        System.out.println("==========================================");

        HashMap<IEmpty, String> map2 = new HashMap<>();

        Key1 key1_1 = new Key1(1);
        Key1 key1_2 = new Key1(1);
        map2.put(key1_1, "key1_1");
        System.out.println(map2.get(key1_2));             //输出结果为：null

        System.out.println("==========================================");

        key2 key2_1 = new key2(1);
        key2 key2_2 = new key2(1);
        map2.put(key2_1, "key2_1");
        System.out.println(map2.get(key2_2));            //输出结果为：null

        System.out.println("==========================================");

        Key3 key3_1 = new Key3(1);
        Key3 key3_2 = new Key3(1);
        map2.put(key3_1, "key3_1");
        System.out.println(map2.get(key3_2));           //输出结果为：key3_1
    }


    /**
     * ----------------------------------------------------------------------------------------------
     * <p>                                    HashMap 源码分析
     * 特点：
     * 1、无序，key、value允许为空，非同步
     * 2、底层由哈希表实现（jdk1.7及其以前使用数组+链表，jdk1.8及其以后使用数组+链表+红黑树）
     * 3、初始容量、加载因子对HashMap的影响很大
     * <p>
     * 一、构造方法
     * {@link HashMap#HashMap()}
     * {@link HashMap#HashMap(int)}
     * {@link HashMap#HashMap(int, float)}
     * {@link HashMap#HashMap(Map)}
     */
    class Empty {

    }
}
