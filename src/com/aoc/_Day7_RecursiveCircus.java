package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Day7_RecursiveCircus {
	public static void main(String args[]) {
		try {
			List<String> input = Files.readAllLines(new File("Day7.txt").toPath());
			Map<Node, String>  subs = new HashMap<>();
			List<Node> nodes = input.stream().map(pgm -> {
				String[] split = pgm.split("->");
				int indexOf = split[0].indexOf(" (");
				String name = split[0].substring(0, indexOf);
				int weight = Integer.valueOf(split[0].substring(indexOf + 2, split[0].indexOf(")")));
				
				Node node = new Node(name, weight);
				
				if (split.length > 1) {
					subs.put(node, split[1]);
				}
				return node;
			}).collect(Collectors.toList());

			subs.forEach((node, str) -> {
				String[] sub = str.split(",");
				Stream.of(sub).map(subName -> {
					Node subNode = nodes.get(nodes.indexOf(new Node(subName)));
					subNode.parent = node;
					return subNode;
				}).forEach(node::add);
			});
			
			Node rootNode = nodes.stream().filter(node -> !node.subNodes.isEmpty()).findFirst().orElse(null);
			while (rootNode.parent != null) {
				rootNode = rootNode.parent;
			}
			
			System.out.println(rootNode.name);
			recursivelyFindWeights(rootNode);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static int recursivelyFindWeights(Node rootNode) {
		int totalWeight = 0;
		for (Node sub : rootNode.subNodes) {
			sub.subWeights = sub.weight + recursivelyFindWeights(sub);
			if (totalWeight == 0) {
				totalWeight = sub.subWeights;
			} else if (totalWeight < sub.subWeights) {
				System.out.println(sub.weight - (sub.subWeights-totalWeight));
			}
		}
		return rootNode.subNodes.stream().mapToInt(s -> s.subWeights).sum();
	}

	private static class Node {
		String name;
		int weight;
		List<Node> subNodes = new ArrayList<>();
		Node parent;
		int subWeights;
		
		public Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		public Node(String name) {
			this.name = name.trim();
		}
		
		public void add(Node node) {
			subNodes.add(node);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}
}
