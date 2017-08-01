package com.lecloud.springbootlearn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Predicate;

public class MultiThread {
    /*
    Lambda表达式的语法 (注意：表达式中的异常需要在表达式内捕获)
    基本语法:  输入 -> 输出
    (parameters) -> expression
    或
    (parameters) ->{ statements; }

    Callable 有返回值
    Runnable 无返回值

    CTRL+Q看文档注释
    Java通过Executors提供四种线程池，分别为：
    newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲(60秒)线程，若无可回收，则新建线程。
    newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
    newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

    功能接口：
    http://blog.csdn.net/u011659172/article/details/52584196
    */

    public void fun() throws Exception {
        int taskNum = 10;
        List<Callable<Integer>> taskList = new ArrayList<>(taskNum);
        for (int i = 0; i < taskNum; i++) {
            //将一个Lambda功能函数赋值给一个接口变量
            Callable<Integer> task = () -> {
                //执行任务
                return 5;
            };
            //使用匿名内部类,与上面效果相同
            /*Callable<Integer> task2 = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return null;
                }
            };*/
            taskList.add(task);
        }

        //功能接口，可自定义
        Function<Future, Integer> futureIntegerFunction = (future -> {
            try {
                return (Integer) (future.get());
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return 0;
            }
        });

        //断言功能接口
        Predicate<Integer> predicate = (i) -> i > 0;

        Executors.newCachedThreadPool()
                .invokeAll(taskList)   //返回List<Future>, .submit是执行一个task,返回一个Future
                .stream()   //转换成具有顺序特性的stream,其元素类型为Future
                .map(futureIntegerFunction)  //将stream中的每个Future元素通过功能接口映射成Int
                .filter(predicate) //只要 >0 的元素
                .mapToInt(Integer::intValue)
                .sum();
    }
}
