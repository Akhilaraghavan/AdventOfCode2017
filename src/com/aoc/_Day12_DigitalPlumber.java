package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Day12_DigitalPlumber {

	
	public static void main(String args[]) throws IOException {
		List<String> input = Files.readAllLines(new File("Day12.txt").toPath());
		Map<String, List<String>> nodes = new LinkedHashMap<>();
		
		input.stream().forEach(s -> {
			String key = s.substring(0, s.indexOf(" <-> "));
			List<String> children = Stream
					.of(s.substring(s.lastIndexOf(" <-> ") + " <-> ".length(), s.length())
					.split(","))
					.map(s1 -> s1.trim()).collect(Collectors.toList());
			nodes.put(key,children);
		});
		
		List<String> collect = nodes.keySet().stream().collect(Collectors.toList());
		int count = 0;
		while(!collect.isEmpty()) {
			List<String> visited = new ArrayList<>();
			String start = collect.get(0);
			recursivelyFindNodes(Arrays.asList(start), visited, nodes);
			if ("0".equals(start)) {
				System.out.println("Visited : " + visited.size());
			}
			collect.removeAll(visited);
			++count;
		}
		
		System.out.println(count);
		
	}

	private static void recursivelyFindNodes(List<String> list, List<String> visited, Map<String, List<String>> nodes) {
		list.stream()
		.filter(s -> !visited.contains(s))
		.forEach(s -> {
			visited.add(s);
			recursivelyFindNodes(nodes.get(s), visited, nodes);
		});
		
	}
	
}
