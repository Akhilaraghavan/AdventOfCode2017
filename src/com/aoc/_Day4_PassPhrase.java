package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Day4_PassPhrase {
	public static void main(String args[]) {
		try {
			List<String> lines = Files.readAllLines(new File("Day4.txt").toPath());
			long part1Count = lines.stream()
					.map(line -> line.split("\\s"))
					.filter(_Day4_PassPhrase::isDupicate)
					.count();
			System.out.println(part1Count);
		
			long part2Count = lines.stream()
					.map(line -> line.split("\\s"))
					.filter(_Day4_PassPhrase::isRotated).count();
			System.out.println(part2Count);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isRotated(String[] split) {
		if (!isDupicate(split)) return false;
		List<String> collect = Stream.of(split)
								.map(str  ->  str.chars().sorted()
									.mapToObj(c -> String.valueOf((char) c))
									.collect(Collectors.joining()))
								.collect(Collectors.toList());
		return collect.size() == collect.stream().distinct().count();
	}

	private static boolean isDupicate(String[] split) {
		return split.length == Stream.of(split).distinct().count(); 
	}
}
