package com.wang.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;

/**
 * java 静态代理和动态代理
 * 2019/04/22
 * <p>
 * 需求：在项目所有类的方法前后打印日志(本案例为类{@link CalculatorImpl})
 * <p>
 * 静态代理实现(缺点是对于每一个类都必须编写代理类)：
 * 1、为每一个类编写一个对应的代理类(本案例为{@link CalculatorProxy})，并且让它和目标类实现相同的接口
 * 2、在创建代理对象时，通过构造器塞入一个目标对象，然后在代理对象的方法内部调用目标对象同名方法，并在调用前后打印日志即可
 * <p>
 * 动态代理实现(不写代理类，而直接得到代理类的Class对象，然后通过反射创建代理实例)：
 * 1、{@link Proxy#getProxyClass(ClassLoader, Class[])}此方法只要给它传入类加载器和一组接口，它就能给你返回代理Class对象
 * 2、通过代理Class的构造器创建对象时，需传入{@link InvocationHandler}，每次调用代理对象的方法，最终都会调用{@link InvocationHandler#invoke(Object, Method, Object[])}方法
 * 说明：在实际中，一般使用{@link Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)}来直接返回代理实例
 */

class ProxyMain {

    public static void main(String[] args) throws Exception {
        //静态代理
        Calculator c1 = new CalculatorProxy(new CalculatorImpl());
        c1.add(1, 2);

        System.out.println("============================");

        //动态代理
        Calculator c2 = (Calculator) getProxy(new CalculatorImpl());
        c2.add(1, 2);
    }

    private static Object getProxy(Object target) throws Exception {
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理： " + method.getName() + " 方法开始执行");
                        Object result = method.invoke(target, args);
                        System.out.println("result = " + result);
                        System.out.println("动态代理： " + method.getName() + " 方法执行完毕");
                        return result;
                    }
                });
    }
}
