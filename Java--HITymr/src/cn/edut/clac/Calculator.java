
package cn.edut.clac;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    /**
     * 给外部的唯一方法
     * 分析表达式，返回计算值
     *
     * @param s
     * @return
     */
    public static int calcExpression(String s) {
        return doCalc(parseSufixExpression(parseExpression(s)));
    }

    /**
     * 测试1：
     * 1字符串录入，
     * 2后缀求值
     */
    @Test
    public void Test01() {
//        String suffix_expression = "1 2 3      * + 4 5 + 6 * + 7 - 2 /"; //27
        String suffix_expression = "A+(B-C/D)*E#"; //27
        //测试：字符串 =》 后缀List
        System.out.println("后缀表达式：" + parseExpression(suffix_expression).toString());
        //测试：后缀List =》求值
        System.out.println("运算结果：" + doCalc(parseExpression(suffix_expression)));
        ;
    }

    /**
     * 测试2：
     * 1 中缀 转后缀
     * 2 中缀 转后缀 取值
     */
    @Test
    public void Test02() {
//        String infix_expression = "1 + 2 * 3 + ( 4 + 5   ) * 6 - 7";
        String infix_expression = "A+(B-C/D)*E#"; //27
        System.out.println("中缀表达式：" + infix_expression);
        List<String> suffix_strTemp = parseSufixExpression(
                parseExpression(infix_expression)
        );
        System.out.println("转成后缀表达式：" + suffix_strTemp.toString());
        System.out.println("计算结果：" + doCalc(suffix_strTemp));
        //78
        String infix_expression2 = "1 + 2 * 3 + ( 8 / 2 * 1 + 25 / 5 + ( 7 + ( 1 + 1 ) / 2 ) / 2  ) * 6 - 7";
        System.out.println("中缀表达式：" + infix_expression2);
        List<String> suffix_strTemp2 = parseSufixExpression(
                parseExpression(infix_expression2)
        );
        System.out.println("转成后缀表达式：" + suffix_strTemp2.toString());
        System.out.println("计算结果：" + doCalc(suffix_strTemp2));
    }


    /**
     * 中缀转后缀 时，迭代所需要的当前位置
     */
    private static int currentIndex = 0;

    /**
     * {中缀转后缀}
     * 使用迭代，实现（带括号的）中缀转后缀
     * 1.遍历中缀List
     * 2.判断是否为“(”
     * 开始迭代
     * 3.判断是否为")"
     * 结束迭代
     *
     * @param infixExpression
     * @return
     */
    private static List<String> parseSufixExpression(List<String> infixExpression) {
        List<String> resultList = new ArrayList<String>();
        //存放操作的栈
        Stack<String> stackOperation = new Stack<>();
        //获取到当前遍历到的指针
        //遍历中缀，把当前操作位置记为index
        //index 为后缀的位置
        for (; // 不需要
             currentIndex < infixExpression.size();
             currentIndex++) {
            //遍历中缀当前字符
            String s = infixExpression.get(currentIndex);
            //不是不是数字
            if (!s.matches("\\D")) {
                //如果是数字，就加入输出结果
                resultList.add(s);
            } else if (s.equals("(")) {
                //如果是左括号
                //开始迭代
                //把迭代的结果
                //操作下一个字符
                currentIndex++;
                resultList.addAll(parseSufixExpression(infixExpression));
            } else if (s.equals(")")) {
                //如果是右括号
                //跳出循环结束迭代
                break;
            } else {
                //如果不是数字，不是()
                //进行判断，加减乘除
                switch (s) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        //栈有东西的话
                        if (stackOperation.size() > 0) {
                            while (stackOperation.size() > 0) {
                                //栈顶取元素
                                String strTemp = stackOperation.pop();
                                if (isHeightLevel(s, strTemp)) {
                                    //如果现有的s和栈顶比，优先级高，就存入
                                    stackOperation.push(strTemp);
                                    break;
                                } else {
                                    //否则就把栈顶元素输出到list
                                    resultList.add(strTemp);
                                }
                            }
                            //把栈需要的输出后，，把s放入栈
                            stackOperation.push(s);
                        } else {
                            //如果栈空,直接进入栈
                            stackOperation.push(s);
                        }
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        }
        while (stackOperation.size() > 0) {
            resultList.add(stackOperation.pop());
        }
        //遍历到最后，初始化index
        initialIndex(infixExpression);
        return resultList;
    }

    private static void initialIndex(List<String> Expression) {
        if (!(currentIndex < Expression.size())) {
            currentIndex = 0;
        }
    }

    /**
     * 判断优先级
     *
     * @param s
     * @param sTemp
     * @return
     */
    private static boolean isHeightLevel(String s, String sTemp) {
        return getLevel(s) - getLevel(sTemp) > 0 ? true : false;
    }

    /**
     * 获取优先级
     *
     * @param s
     * @return
     */
    private static int getLevel(String s) {
        switch (s) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
        }
        throw new RuntimeException();
    }


    /**
     * 分割字符
     * 1 接收一个：由多个空格隔开的字符
     * 2 分割这些字符
     * 3 返回字符ArrayList
     *
     * @param str
     * @return
     */
    private static List<String> parseExpression(String str) {
        //空格分开字符
        String[] strTemp = str.split("\\s+");
        //字符存入List
        List<String> outList = new ArrayList<>();
        for (String s : strTemp) {
            outList.add(s);
        }
        return outList;
    }

    /**
     * 后缀求值：
     * 1  接收一个List<String>
     * 2 遍历、分析是否为数字
     * 2.1 数字 - 存入栈
     * 2.2 不是数字 - 判断符号，栈中取顶、进行运算
     * 3 返回值
     */
    private static int doCalc(List<String> str) {
        //操作的栈
        Stack<Integer> stackInteger = new Stack<>();
        for (String s : str) {
            //不是不是数字
            if (!s.matches("\\D")) {
                //数字，字符串转Integer
                stackInteger.push(Integer.parseInt(s));
            }//不是数字
            else {
                //栈中取值
                int num2 = stackInteger.pop();
                int num1 = stackInteger.pop();
                //运算的结果
                int result = 0;
                //判断符号，四则运算
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("四则运算符号对不上");
                }
                //运算的结果存入stack
                stackInteger.push(result);
            }
        }
        //返回stack的值
        return stackInteger.pop();
    }
}


