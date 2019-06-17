package com.shyfay.usual;

/**
 * @author 牟雪
 * @since 2018/11/13
 */
public class TryCatchFinally {
    public static Integer innerFun(){
        Integer x = 0;
        try{//TODO 代码块1
            x = 1;
            return x;
        }catch(Exception e){//TODO 代码块2
            x = 2;
            return x;
        }
//      catch(CustomException e){//TODO 代码块3
//            x = 3;
//            return x;
//        }
        finally{//TODO 代码块4
            x = 4;
            //return x;
        }
    }
    public static void main(String[] args){
        Integer x = innerFun();
        //TODO 情况1：如果在代码块1处没有抛出任何异常且代码块4里面没有写返回语句，那么返回1，如果代码块4里面写了返回语句。那么返回4。
        //TODO 情况2：代码块1出现了Exception或其子类的异常且代码块4里面没有写返回语句，那么返回2，如果代码块4里面写了返回语句。那么返回4。
        //TODO 情况3：代码块1出现了非Exception或其子类的异常且不存在代码块3的时候那么代码块4必须写返回语句且返回的值就是代码块4返回的值。
        //TODO 情况4：代码块1出现了非Exception或其子类的异常且是自定义异常CustomException时，
        //TODO       如果代码块4里面没有写返回语句，那么返回3，如果代码块4里面写了返回语句。那么返回4。
        System.out.println(x);
    }
}
