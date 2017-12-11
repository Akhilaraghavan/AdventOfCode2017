package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class _Day11_Hex {
	
	public static void main(String args[]) throws IOException {
	List<String> readAllLines = Files.readAllLines(new File("Day11.txt").toPath());
		int x = 0;
		int y = 0;
		int z = 0;
		int max = 0;
		
		for (String s1: readAllLines) {
			for (String s : s1.split(",")) {
				if ("n".equals(s)) {
					++x;
					--y;
				} else if ("s".equals(s)) {
					--x;
					++y;
				} else if ("ne".equals(s)) {
					++z;
					--y;
				} else if("nw".equals(s)) {
					++x;
					--z;
				} else if("se".equals(s)) {
					 --x;
					 ++z;
				} else if("sw".equals(s)) {
					--z;
					++y;
				}
				int val = (Math.abs(x)+Math.abs(y)+ Math.abs(z))/2;
				if (max <val) {
					max = val;
				}
			}
		}
		System.out.println((Math.abs(x)+Math.abs(y)+ Math.abs(z))/2);
		System.out.println(max);
		
	}
}
