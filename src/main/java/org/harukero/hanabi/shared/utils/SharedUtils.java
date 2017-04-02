package org.harukero.hanabi.shared.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SharedUtils {
	public static void shuffle(List<?> list) {
		int size = list.size();
		Random random = new Random();

		for (int index = 0; index < size; index += 1) {
			Collections.swap(list, index, index + random.nextInt(size - index));
		}
	}
}
