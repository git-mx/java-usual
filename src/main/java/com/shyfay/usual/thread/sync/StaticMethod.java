package com.shyfay.usual.thread.sync;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author mx
 * @since 2019/7/6
 */
public class StaticMethod {
    public synchronized static void method1(){
        for(int i=0; i<10; i++){
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("AAAAAAAAAAAAAAAAAA");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized  static void method2(){
        for(int i=0; i<10; i++){
            try{
                TimeUnit.MILLISECONDS.sleep(1100);
                System.out.println("BBBBBBBBBBBBBBBBB");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void method3(){
        for(int i=0; i<10; i++){
            try{
                TimeUnit.MILLISECONDS.sleep(1200);
                System.out.println("CCCCCCCCCCCCCCC");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
