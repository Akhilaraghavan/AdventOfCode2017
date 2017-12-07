package com.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _Day6_MemoryReallocation {
	public static void main(String args[]) {
		int[] input = new int[] {4,10,4,1,8,4,9,14,5,1,14,15,0,15,3,5};
		
		List<String> seen = new ArrayList<>();
		String alloc = "";
		int count = 0;

		while(!seen.contains(alloc)) {
			++count;
			String collect = IntStream.of(input).mapToObj(String::valueOf).collect(Collectors.joining("|"));
			seen.add(collect);
			
			int max = IntStream.of(input).max().orElse(0);
			int rotation = max;
			int start = IntStream.of(input).mapToObj(Integer::valueOf).collect(Collectors.toList()).indexOf(max);
			boolean foundMax = false;
			while (rotation >= 0) {
				start = start == input.length ? 0 : start;
				Integer val = input[start];
				if (val == max && !foundMax) {
					input[start] = 0;
					foundMax = true;
				} else {
					++input[start]; 
				}
				++start;
				--rotation;
			}
			alloc =  IntStream.of(input).mapToObj(String::valueOf).collect(Collectors.joining("|"));
		}
		int indexOf = seen.indexOf(alloc);
		System.out.println(seen.size() - indexOf);
		System.out.println(count);
	}
}
