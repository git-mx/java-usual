package com.shyfay.usual.reflex;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Notes MethodHandle（方法句柄）是JDK7引进的新组件，相当对JAVA反射功能的补充
 * 所谓方法句柄就是方法的句柄，我们可以使用方法句柄来调用任何可被发现的（本项目内的）类的任何方法
 * 它必须结合MethodType来使用，每一个MethodHandle都有一个MethodType，用来指明方法的返回值类型和参数类型。
 * 它的类型完全由参数类型和方法的返回值类型来确定，而与它所引用的底层方法的名称和所在的类没有任何关系，例如：
 * String类的length方法和Integer类的intValue方法的方法句柄的MethodType就是一样的，因为这两个方法都没有参数，
 * 而且返回值类型都是int。MethodType的对象实例只能通过MethodType类中的静态工厂方法来创建，而且创建出来的
 * 对象实例也是不可变的。invoke和invokeExact方法的第一个参数，就是这个方法的接受对象，也就是说是哪个对象来调用这个方法
 * 我们可以通过bindTo来动态的指定invoke和invokeExact方法的调用者。
 * invoke和invokeExact方法的区别在于，从名字上看，明显是后者准确性更高，或者说要求更严格。
 * invokeExact方法在调用时要求严格的类型匹配，方法的返回值也在考虑范围之内，像下面的例子：
 * 如果把 Object result2 = (String) mh.invokeExact(str, 1, 3); 这一句中的(String)去掉，那么则会报错。
 * 因为在调用的时候该方法会认为返回值是Object而不是String。
 * 与invokeExact方法不同，invoke允许更加松散的调用方式。它会尝试在调用的时候进行返回值和参数类型的转换工作。
 * 这是通过MethodHandle类的asType方法来完成的,asType方法的作用是把当前方法适配到新的MethodType上面，
 * 并产生一个新的方法句柄。当方法句柄在调用时的参数与返回值类型完全一直时，调用invoke方法等同于调用invokeExact方法。
 * 否则，invoke方法会先调用asType方法来尝试适配到调用时的类型。如果适配成功，那么继续调用，否则就会抛出异常。
 * 这种灵活的适配机制，使得invoke方法称为在绝大多数情况下都应该使用的方法句柄调用方式。
 * 在SpringCloud-FeignClient中就是利用方法句柄来动态调用执行FeignClient里的每一个方法的：SynchronousMethodHandler
 * @Author muxue
 * @Since 7/12/2020
 */
public class MethodHandleTest {
    public MethodHandle getHandler() {
        MethodHandle mh = null;
        MethodType mt = MethodType.methodType(String.class, int.class, int.class);
        MethodHandles.Lookup lk = MethodHandles.lookup();

        try {
            mh = lk.findVirtual(String.class, "substring", mt);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return mh;
    }

    public static void main(String[] args) throws Throwable {
        MethodHandle mh = new MethodHandleTest().getHandler();
        String str = "hello world";

        Object result1 = mh.invoke(str, 1, 3);
        Object result2 = (String) mh.invokeExact(str, 1, 3);
//      Object result2 = mh.invokeExact(str, new Integer(1), 3);
        /**
         * 上面这句方法执行时报错,因为方法类型为String.class, int.class, int.class
         * 而返回的类型为Object，与声明中为String不符合
         * 其中第二个参数类型为Integer，与声明中为int不符合，则类型适配不符合，系统报错。
         */

        System.out.println("result 1:" + result1);
        System.out.println("result 1:" + result2);
    }
}
