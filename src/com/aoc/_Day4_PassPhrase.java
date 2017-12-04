package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _Day4_PassPhrase {
	public static void main(String args[]) {
		try {
			List<String> lines = Files.readAllLines(new File("Day4.txt").toPath());
			long count = lines.stream().filter(line -> {
				String[] split = line.split("\\s");
				Set<String> words = new HashSet<>();
				for (String str : split) {
					if (words.contains(str)) {
						return false;
					}
					words.add(str);
				}
				return true;
			}).count();
			System.out.println(count);
		
			long part2Count = lines.stream().filter(line -> {
				String[] split = line.split("\\s");
				Set<String> words = new HashSet<>();
				int index = 0;
				for (String str : split) {
					if (words.contains(str)) {
						return false;
					} else if (!words.isEmpty() && isRotated(Arrays.asList(split), str, index)) {
						return false;
					}
					words.add(str);
					++index;
				}
				return true;
			}).count();
			System.out.println(part2Count);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean isRotated(List<String> words, String str, int index) {
		int i = 0;
		for (String word : words) {
			if (i++ != index) {
				if (str.length() == word.length()) {
					String replaced = str;
					for (int j = 0; j<word.length(); j++) {
						String value = String.valueOf(word.charAt(j));
						if (!replaced.contains(value)) {
							break;
						} 
						replaced = replaced.replaceFirst(value, "");
					}
					if (replaced.isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
