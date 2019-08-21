package com.shyfay.usual.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 本例用于计算自定义计算公式的值
 * @author mx
 * @since 2019/8/15
 */
public class FourOperations {
    //运算符优先级映射表
    private static Map<String, Map<String, String>> priorityMap = new HashMap<>();
    //操作符栈
    private Stack<String> optStack = new Stack<>();
    //操作数栈
    private Stack<Double> numStack = new Stack<>();
    static {
        //+操作符
        Map<String, String> subMap = new HashMap<String, String>();
        subMap.put("+", ">");
        subMap.put("-", ">");
        subMap.put("*", "<");
        subMap.put("/", "<");
        subMap.put("(", "<");
        subMap.put(")", ">");
        subMap.put("#", ">");
        priorityMap.put("+", subMap);

        //-操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", ">");
        subMap.put("-", ">");
        subMap.put("*", "<");
        subMap.put("/", "<");
        subMap.put("(", "<");
        subMap.put(")", ">");
        subMap.put("#", ">");
        priorityMap.put("-", subMap);

        //*操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", ">");
        subMap.put("-", ">");
        subMap.put("*", ">");
        subMap.put("/", ">");
        subMap.put("(", "<");
        subMap.put(")", ">");
        subMap.put("#", ">");
        priorityMap.put("*", subMap);

        // /操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", ">");
        subMap.put("-", ">");
        subMap.put("*", ">");
        subMap.put("/", ">");
        subMap.put("(", "<");
        subMap.put(")", ">");
        subMap.put("#", ">");
        priorityMap.put("/", subMap);


        // (操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", "<");
        subMap.put("-", "<");
        subMap.put("*", "<");
        subMap.put("/", "<");
        subMap.put("(", "<");
        subMap.put(")", "=");
        priorityMap.put("(", subMap);

        // )操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", ">");
        subMap.put("-", ">");
        subMap.put("*", ">");
        subMap.put("/", ">");
        //subMap.put("(", "<");
        subMap.put(")", ">");
        subMap.put("#", ">");
        priorityMap.put(")", subMap);

        //#操作符
        subMap = new HashMap<String, String>();
        subMap.put("+", "<");
        subMap.put("-", "<");
        subMap.put("*", "<");
        subMap.put("/", "<");
        subMap.put("(", "<");
        //subMap.put(")", ">");
        subMap.put("#", "=");
        priorityMap.put("#", subMap);
    }

    /**
     * 计算表达式
     * @param exp 表达式字符串，表达式以#开始并以#结束，所有的运算符和操作数之间以空格分隔，(和)也是运算符
     * @return
     */
    public double executeExpression(String exp){
        StringTokenizer st = new StringTokenizer(exp);
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            process(token);
        }
        return numStack.pop();
    }


    /**
     * 读入一个操作数或者运算符
     * 如果是操作数（数字），则压入numStack栈顶
     * 如果是运算符，则将optStack栈顶的元素与该运算符进行优先级比较
     * 如果栈顶操作符优先级较低，则将该运算符压如optStack栈顶，如果相等则表示是)运算符，那么弹出optStack栈顶的(运算符。如果高，则取出
     * 取出numStack栈顶的两个操作数，然后取出optStack栈顶的一个运算符，进行运算，并将结果压入numStack栈顶
     * @param token
     */
    private void process(String token){
        while(optStack.size() == 0 || !"#".equals(optStack.peek()) || !"#".equals(token)){
            if(isNumber(token)){
                numStack.push(Double.parseDouble(token));
                break;
            }else{
                if(optStack.size() == 0){
                    optStack.push(token);
                }else{
                    String priority = getPriority(optStack.peek(), token);
                    if("<".equals(priority)){
                        optStack.push(token);
                        break;
                    }else if("=".equals(priority)){
                        optStack.pop();
                        break;
                    }else{
                        double res = calculate(optStack.pop(), numStack.pop(), numStack.pop());
                        numStack.push(res);
                    }
                }
            }
        }
    }

    private boolean isNumber(String source){
        for(int i=0; i<source.length(); i++){
            if(!Character.isDigit(source.charAt(i)) && source.charAt(i) != '.'){
                return false;
            }
        }
        return true;
    }


    private String getPriority(String opt1, String opt2){
        return priorityMap.get(opt1).get(opt2);
    }

    //注意次序， 一定是n2在前，n1在后
    private double calculate(String opt, Double n1, Double n2){
        if("+".equals(opt)){
            return n2 + n1;
        }else if("-".equals(opt)){
            return n2 - n1;
        }else if("*".equals(opt)){
            return n1 * n2;
        }else if("/".equals(opt)){
            return n2 / n1;
        }else{
            throw new RuntimeException("not supported operator:" + opt);
        }
    }

    public static void main(String[] args) {
        String expression = "# 5 * ( 40 - 35 * 0 ) + 5 #";
        FourOperations fo = new FourOperations();
        System.out.println(fo.executeExpression(expression));
    }
}
