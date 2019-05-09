package com.wang.study.proxy;

/**
 * 作者：wanglei on 2019/4/22.
 * 邮箱：forwlwork@gmail.com
 */

class CalculatorProxy implements Calculator {
    private CalculatorImpl calculator;

    CalculatorProxy(CalculatorImpl calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("静态代理： add 方法开始执行");
        int result = calculator.add(a, b);
        System.out.println("result = " + result);
        System.out.println("静态代理： add 方法执行完毕");
        return result;
    }
}
