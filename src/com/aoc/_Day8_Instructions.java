package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class _Day8_Instructions {

	public static void main(String args[]) {
		try {
			List<String> registers = Files.readAllLines(new File("Day8.txt").toPath());
			Map<String, Integer> insMap = new HashMap<>();
			int max = -1;
			for(String reg : registers) {
				String[] split = reg.split("\\s");
				String register = split[0];
				String ins = split[1];
				int value = Integer.valueOf(split[2]);
				insMap.putIfAbsent(register, 0);
				if (parseCondition(insMap, split[4], split[5], Integer.valueOf(split[6]))) {
					if ("inc".equals(ins)) {
						insMap.put(register, insMap.get(register) + value);
					} else if ("dec".equals(ins)) {
						insMap.put(register, insMap.get(register) - value);
					}
				}
				if (max < insMap.get(register)) {
					max = insMap.get(register);
				}
			}

			System.out.println("Part 2: " + max);
			max = -1;
			for (Entry<String, Integer> entry :insMap.entrySet()) {
				if (entry.getValue() > max) {
					max = entry.getValue();
				}
			}
			System.out.println("Part 1:" + max);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean parseCondition(Map<String, Integer> insMap, String reg, String op, int value) {
		insMap.putIfAbsent(reg, 0);
		if ("==".equals(op)) {
			return insMap.get(reg) == value; 
		} else if ("!=".equals(op)) {
			return insMap.get(reg) != value; 
		} else if (">".equals(op)) {
			return insMap.get(reg) > value; 
		} else if ("<".equals(op)) {
			return insMap.get(reg) < value; 
		} else if (">=".equals(op)) {
			return insMap.get(reg) >= value; 
		} else if ("<=".equals(op)) {
			return insMap.get(reg) <= value; 
		}
		return false;
	}
	
}
