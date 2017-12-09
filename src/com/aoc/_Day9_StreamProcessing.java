package com.aoc;

import java.util.Stack;

public class _Day9_StreamProcessing {
	//instead could use FSM enum with states??
	public static void main(String args[]) {
		String input = "{{<a!>},{<a!>},{<a!>},{<ab>}}";
		int index = 0;
		Stack<String> stack = new Stack<>();
		int count = 0;
		int currentLevel = 0;
		boolean foundGarbage = false;
		boolean foundIgnore = false;
		int part2Count = 0;
		while (index < input.length()) {
			String val = String.valueOf(input.charAt(index));	
			if (foundIgnore) {
				foundIgnore = false;
			} else if ("!".equals(val)) {
				foundIgnore = true;
			} else if ("<".equals(val) && !foundGarbage) {
				foundGarbage = true;
			} else if (">".equals(val)) {
				foundGarbage = false;
			} else if (!foundGarbage) {
				if ("{".equals(val)) {
					stack.push("{");
					++currentLevel;
				} else if ("}".equals(val) && !stack.isEmpty() && "{".equals(stack.peek())) {
					count += currentLevel;
					stack.pop();
					--currentLevel;
				}
			} else if (foundGarbage) {
				++part2Count;
			}
			++index;
		}
		System.out.println(count);
		System.out.println(part2Count);
	}
}