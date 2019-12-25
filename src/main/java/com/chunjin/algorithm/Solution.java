package com.chunjin.algorithm;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    public boolean isValid(String s) {


        Map<String, String> map = new HashMap<>();
        map.put("]", "[");
        map.put(")", "(");
        map.put("}", "{");

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            String strch = String.valueOf(ch);
            if (strch.equals("[") || strch.equals("{") || strch.equals("(")) {
                stack.push(ch);
            }
            if (map.containsKey(strch)) {
                String pair = map.get(strch);
                if (stack.size() > 0) {
                    Character character = stack.pop();
                    if (!pair.equals(character.toString())) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (stack.size() > 0) {
            return false;
        }
        return true;


    }


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int lengthOfLongestSubstring(String s) {


        char[] chars = s.toCharArray();
        Queue<Character> list = new ArrayDeque<>();

        int length = 1;
        for (char c : chars) {
            if (list.contains(c)) {
                if (length <= list.size()) {
                    length = list.size();
                }

            } else {

                list.add(c);
            }
        }
        return 0;
    }
}