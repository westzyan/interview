package com.zyan;

public class InnerClassBase {
    class InnerClass {
    }
    static class StaticInnerClass {
        int a = 0;
    }
    public static void main(String[] args) {// InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be
        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot bereferenced from a static context
        InnerClassBase outerClass = new InnerClassBase();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        StaticInnerClass a = new InnerClassBase.StaticInnerClass();
        int b =a.a;
    }
}
