package com.shyfay.usual.datatype;
public class AutoBoxing {
    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer d1 = new Integer(3);
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        //TODO 这里不能直接进行比较
        //System.out.println(g==c);
        System.out.println(d==d1);//false
        System.out.println(c==d);//true
        System.out.println(e==f);//false
        System.out.println(e.equals(f));//true
        System.out.println(c==(a+b));//true
        System.out.println(c.equals(a+b));//true
        System.out.println(g==(a+b));//true
        System.out.println(g.equals(a+b));//false
        System.out.println(g.equals(Long.valueOf(a+b)));//true
        //TODO 在比较Integer和Long类型的值是不要使用==应该使用equals
        //TODO 对于封装类型Integer来说当对比的两个值在-128到127（包含）时是正确的，但是当对比的两个值
        //TODO 超出这个返回之后对比就不正确的，因为对于封装类型Integer当值在那个范围之内时是JVM是直接在
        //TODO 栈内存上分配一块内存区域用于存放这个值，就跟int一样，当程序中再定义一个值跟这个一样的Intger
        //TODO 时，JVM并不会新开辟一块内存空间，而是直接使用已经存在的这个，所以上面的代码中c==d是成立的
        //TODO 但是当Integer的值超过那个范围了，JVM就
        //TODO 是以Integer XXX = new Integer(200)的形式在堆内存分配一块内存区域用于存放XXX,当两个对象
        //TODO 进行==比较时，无论它们的值是否相等都是不成立的。==本质上比较的是比较参与比较的两个变量的内存起始地址是否一样。
        //TODO Long类型也和Integer一样，在范围之类是在栈区分配内存，再次定义值相同的变量时也不会新分配内存
        //TODO 所以g==(a+b)是成立的。但是g.equals(a+b)是false因为equals用于两个不同类型的值进行比较时是恒不成立的，
        //TODO 之所以成立是因为 a和b都是基本数据类型（String不是），对于基本数据类型而言，JVM在编译期会将用到基本数据类型的地方
        //TODO 替换成变量本身的值，然后这里还发生了一次自动类型转换及将(a+b)自动转换成Long类型的变量，这样就相当于两个基本数据类型
        //TODO Long类型的数据进行比较了，只要值是一样的==判断肯定为真，自动类型转换也是发生在程序编译期。String类型则不行比如下面的代码段
        String stra = "HelloWorld";
        String strb = "Hello";
        String strc = "World";
        String strd = strb + strc;
        System.out.println(stra == strd);
        System.exit(0);
        //TODO 对于两个类型相同的封装类型的值比较千万不要用==，一定要用equals，对于不同两个类型不同的值新型比较时千万不要
        //TODO 直接使用equals进行比较，一定要把其中一个强制转换后再使用equals进行比较。
    }
}
