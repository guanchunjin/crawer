package com.chunjin.Jdk8Pattern;

import com.google.common.base.Strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {


  public static void main(String[] args) {


    //对collection 中的 element 去除空
//    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//    List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//    //去除replace b with t
//    List<String> removeb= strings.stream().map(s -> s.replace("b","t")).collect(Collectors.toList());
//    System.out.println(filtered.toString());
//    System.out.println(removeb.toString());
//
//
//    Random random = new Random();
//    IntStream t = random.ints().limit(10);
//    random.ints().limit(4).sorted().forEach(System.out::println);
//
//
//
//    List<String> stringE = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//    int count = (int) stringE.stream().filter(string -> string.isEmpty()).count();
//    System.out.println(count);
//
//
//    List<String> sd = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//// 获取空字符串的数量
//    int cou4nt = (int) sd.parallelStream().filter(string -> string.isEmpty()).count();
//    System.out.println(cou4nt);


    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
    List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());


    System.out.println("筛选列表: " + filtered);
    String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
    System.out.println("合并字符串: " + mergedString);


    List<Integer> numbers = Arrays.asList(6, 6, 6, 6, 6, 6);
    IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

    System.out.println("列表中最大的数 : " + stats.getMax());
    System.out.println("列表中最小的数 : " + stats.getMin());
    System.out.println("所有数之和 : " + stats.getSum());
    System.out.println("平均数 : " + stats.getAverage());

    numbers.stream().mapToInt(x -> (int) (x - stats.getAverage())).map(x -> x * x).reduce(Integer::sum).getAsInt();
    System.out.println("方差 : " +  numbers.stream().mapToInt(x -> (int) (x - stats.getAverage())).map(x -> x * x).reduce(Integer::sum).getAsInt());

    test7();
  }

  public static  void test7() {
    List<String> list = Arrays.asList("a", "MnM");

    List<String> result = list.stream().
            map(String::toUpperCase).
            collect(Collectors.toList());
    System.out.println(list);
    System.out.println(result);
  }
}
