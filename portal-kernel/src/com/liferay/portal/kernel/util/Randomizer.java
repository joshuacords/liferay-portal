/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Newton (6.2.x)
 */
@Deprecated
public class Randomizer extends Random {

	public static Randomizer getInstance() {
		return _randomizer;
	}

	public Randomizer() {
	}

	public Randomizer(long seed) {
		super(seed);
	}

	public int[] nextInt(int n, int size) {
		if (size > n) {
			size = n;
		}

		Set<Integer> set = new LinkedHashSet<>();

		for (int i = 0; i < size; i++) {
			while (true) {
				Integer value = Integer.valueOf(nextInt(n));

				if (!set.contains(value)) {
					set.add(value);

					break;
				}
			}
		}

		int[] array = new int[set.size()];

		Iterator<Integer> itr = set.iterator();

		for (int i = 0; i < array.length; i++) {
			Integer integer = itr.next();

			array[i] = integer.intValue();
		}

		return array;
	}

	public void randomize(char[] array) {
		int length = array.length;

		for (int i = 0; i < (length - 1); i++) {
			int x = nextInt(length);
			char y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public void randomize(int[] array) {
		int length = array.length;

		for (int i = 0; i < (length - 1); i++) {
			int x = nextInt(length);
			int y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public void randomize(List<Object> list) {
		int size = list.size();

		for (int i = 0; i <= size; i++) {
			Object obj = list.get(i);

			int j = nextInt(size);

			list.set(i, list.get(i + j));
			list.set(i + j, obj);

			size--;
		}
	}

	public void randomize(Object[] array) {
		int length = array.length;

		for (int i = 0; i < (length - 1); i++) {
			int x = nextInt(length);
			Object y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public String randomize(String s) {
		if (s == null) {
			return null;
		}

		char[] array = s.toCharArray();

		randomize(array);

		return new String(array);
	}

	private static final Randomizer _randomizer = new Randomizer();

}