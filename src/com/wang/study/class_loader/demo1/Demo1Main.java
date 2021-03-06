package com.wang.study.class_loader.demo1;

/**
 * 测试Java中的类加载机制，本案例中使用的类分别为：
 * {@link SSClass}
 * {@link SuperClass}
 * {@link SubClass}
 *
 * ----------------------------------------------------------------
 *
 * 程序输出结果为：
 *
 * SSClass 静态代码块
 * SuperClass 静态代码块
 * 123
 *
 * ----------------------------------------------------------------
 *
 *    类从被加载到虚拟机内存中开始，指卸载出内存为止，它的整个生命周期包括：加载、
 * 验证、准备、解析、初始化、使用、卸载这7个阶段。
 *    其中，准备、验证、解析3个部分统称为连接，加载、验证、准备、初始化、卸载这5
 * 个阶段的顺序是确定的，类的加载过程必须按照这种顺序按部就班地开始。而解析阶段则
 * 不一定，它在某些情况下可以在初始化阶段之后再开始。
 *
 * 1、加载，虚拟机需要完成以下3件事情：
 *    a.通过一个类的全限定名来获取定义此类的二进制字节流;
 *    b.将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构;
 *    c.在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各
 *      种数据的访问入口。
 *
 * 2、验证，确保Class文件的字节流中包含的信息符合当前虚拟机的要求：
 *    a.文件格式验证：验证字节流是否符合Class文件格式的规范；例如：是否以魔术
 *      0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常
 *      量是否有不被支持的类型;
 *    b.元数据验证：对字节码描述的信息进行语义分析，以保证其描述的信息符合Java
 *      语言规范的要求；例如：这个类是否有父类，除了java.lang.Object之外;
 *    c.字节码验证：通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的;
 *    d.符号引用验证：确保解析动作能正确执行。
 *
 * 3、准备
 *    准备阶段是正式为类变量(被static修饰的变量)分配内存(在方法区中分配)并设置
 *    其初始值的阶段，例如以下代码：
 *    <code>public static int value = 123;</code>
 *    value 在准备阶段过后的初始值为0，因为这时候尚未开始执行任何java方法，而把
 *    value 赋值为123的putstatic指令是程序被编译后，存放于类构造器方法之中，所
 *    以把value赋值为123的动作将在初始化阶段才会执行。
 *    但是也有特殊情况，例如以下代码：
 *    <code>public static final int value = 123;</code>
 *    当 value 标注为 final 之后，value 的值在准备阶段初始化后为123而非0。
 *
 *  4、解析
 *     解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。解析动作主要针对
 *     类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符7类符号引
 *     用进行。
 *
 *  5、初始化
 *     类初始化阶段是类加载过程的最后一步，到了初始化阶段，才真正开始执行类中定义的
 *     java程序代码。在准备极端，变量已经付过一次系统要求的初始值，而在初始化阶段，
 *     则根据程序猿通过程序制定的主管计划去初始化类变量和其他资源，或者说：初始化阶段
 *     是执行类构造器<clinit>方法的过程。
 *     <clinit>方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块static{}
 *     中的语句合并产生的，编译器收集的顺序是由语句在源文件中出现的顺序所决定的，静态代码
 *     块只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以
 *     赋值，但是不能访问。如下：
 *     <code>
 *         public class Test{
 *             static{
 *                 i = 1;
 *                 System.out.println(i); //这句编译器会报错：Cannot reference a field before it is defined（非法向前应用）
 *             }
 *
 *             static int i = 0;
 *         }
 *     </code>
 *     <cinit>方法和实例构造器<init>方法不同，它不需要显式调用父类的构造器，虚拟机会保证
 *     在子类的<init>方法之前执行。
 *     <clinit>方法对于类或者接口来说并不是必需的，如果一个类中没有静态语句块，也没有对变量
 *     的赋值操作，那么编译器可以不为这个类生产<clinit>方法。
 *     虚拟机会保证一个类的<clinit>方法在多线程环境中被正确的加锁、同步，如果多个线程同时去
 *     初始化一个类，那么只会有一个线程去执行这个类的<clinit>方法，其他线程都需要阻塞等待，
 *     直到活动线程执行<clinit>方法完毕。如果在一个类的<clinit>方法中有耗时很长的操作，
 *     就可能造成多个线程阻塞，在实际应用中这种阻塞往往是隐藏的，具体可参考以下示例：
 *     @see com.wang.study.class_loader.demo2.Demo2Main
 */
public class Demo1Main {

    public static void main(String [] args){
        System.out.println(SubClass.value);
    }
}
