package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class _Day5_MazeExit {
	public static void main(String args[]) {
		try {
			List<String> instructions = Files.readAllLines(new File("Day5.txt").toPath());
			int currentIndex  = 0;
			int steps = 0;

			while (currentIndex<instructions.size() && currentIndex >= 0) {
				int instr = Integer.valueOf(instructions.get(currentIndex));
				instructions.set(currentIndex, (instr >= 3 ? (instr - 1) : (instr + 1)) + "");
				steps++;
				currentIndex += instr;
			}
			System.out.println(steps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
