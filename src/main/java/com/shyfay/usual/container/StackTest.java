package com.shyfay.usual.container;

import org.apache.axis.utils.StringUtils;

import java.util.Stack;

/**
 * 该类用于测试字符串内的括号是否匹配
 * @author mx
 * @since 2019/6/20
 */
public class StackTest {
    public static void main(String[] args) {
        String str = new String("aaaaa{}(bbbbb)[cccccc]sssss<ssss>");
        System.out.println(checkBracket(str));
    }

    private static boolean checkBracket(String str){
        if(StringUtils.isEmpty(str)){
            return true;
        }
        Stack<Character> bracketStack = new Stack<>();
        for(int i=0; i<str.length(); i++){
            Character character = str.charAt(i);
            if(character.equals('{') || character.equals('(') || character.equals('[') || character.equals('<')){
                bracketStack.push(character);
            }
            if(character.equals('}') || character.equals(')') || character.equals(']') || character.equals('>')){
                Character beginChar = bracketStack.pop();
                if(character.equals('}') && !beginChar.equals('{')){
                    return false;
                }
                if(character.equals(')') && !beginChar.equals('(')){
                    return false;
                }
                if(character.equals(']') && !beginChar.equals('[')){
                    return false;
                }
                if(character.equals('>') && !beginChar.equals('<')){
                    return false;
                }
            }
        }
        if(bracketStack.empty()){
            return true;
        }
        return false;
    }
}
