package com.wang.study.proxy;

/**
 * 作者：wanglei on 2019/4/22.
 * 邮箱：forwlwork@gmail.com
 */

class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
