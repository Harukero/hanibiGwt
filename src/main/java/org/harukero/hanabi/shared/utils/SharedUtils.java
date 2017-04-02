package org.harukero.hanabi.shared.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SharedUtils {

	public static final Map<Integer, Integer> nbOfCardByRank = new HashMap<>();

	static {
		nbOfCardByRank.put(1, 3);
		nbOfCardByRank.put(2, 2);
		nbOfCardByRank.put(3, 2);
		nbOfCardByRank.put(4, 2);
		nbOfCardByRank.put(5, 1);
	}

	public static void shuffle(List<?> list) {
		int size = list.size();
		Random random = new Random();

		for (int index = 0; index < size; index += 1) {
			Collections.swap(list, index, index + random.nextInt(size - index));
		}
	}
}
