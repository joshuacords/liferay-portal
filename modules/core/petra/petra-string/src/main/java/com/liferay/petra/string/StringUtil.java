/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Function;

/**
 * The String utility class.
 *
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 * @author Ganesh Ram
 * @author Shuyang Zhou
 * @author Hugo Huijser
 */
public class StringUtil {

	public static boolean equalsIgnoreCase(char c1, char c2) {
		if (c1 == c2) {
			return true;
		}

		// Fast fallback for non-acsii code.

		if ((c1 > 127) || (c2 > 127)) {

			// Georgian alphabet needs to check both upper and lower case

			if ((Character.toLowerCase(c1) == Character.toLowerCase(c2)) ||
				(Character.toUpperCase(c1) == Character.toUpperCase(c2))) {

				return true;
			}

			return false;
		}

		// Fast fallback for non-letter ascii code

		if ((c1 < CharPool.UPPER_CASE_A) || (c1 > CharPool.LOWER_CASE_Z) ||
			(c2 < CharPool.UPPER_CASE_A) || (c2 > CharPool.LOWER_CASE_Z)) {

			return false;
		}

		int delta = c1 - c2;

		if ((delta != 32) && (delta != -32)) {
			return false;
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if the strings are equal, ignoring case.
	 *
	 * @param  s1 the first string to compare
	 * @param  s2 the second string to compare
	 * @return <code>true</code> if the strings are equal, ignoring case;
	 *         <code>false</code> otherwise
	 */
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if ((s1 == null) || (s2 == null) || (s1.length() != s2.length())) {
			return false;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (!equalsIgnoreCase(s1.charAt(i), s2.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static String merge(boolean[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(byte[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(char[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T> String merge(
		Collection<T> col, Function<T, String> toStringFunction,
		String delimiter) {

		if (col instanceof List && col instanceof RandomAccess) {
			return merge(
				(List<T> & RandomAccess)col, toStringFunction, delimiter);
		}

		if (col == null) {
			return null;
		}

		if (col.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * col.size());

		for (T t : col) {
			sb.append(toStringFunction.apply(t));
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T> String merge(Collection<T> col, String delimiter) {
		return merge(col, String::valueOf, delimiter);
	}

	public static String merge(double[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(float[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(int[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T, L extends List<T> & RandomAccess> String merge(
		L list, Function<T, String> toStringFunction, String delimiter) {

		if (list == null) {
			return null;
		}

		if (list.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2 * list.size());

		for (int i = 0; i < list.size(); i++) {
			sb.append(toStringFunction.apply(list.get(i)));
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T, L extends List<T> & RandomAccess> String merge(
		L list, String delimiter) {

		return merge(list, String::valueOf, delimiter);
	}

	public static String merge(long[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(short[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return String.valueOf(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static String merge(String[] array, String delimiter) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return array[0];
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T> String merge(
		T[] array, Function<T, String> toStringFunction, String delimiter) {

		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return StringPool.BLANK;
		}

		if (array.length == 1) {
			return toStringFunction.apply(array[0]);
		}

		StringBundler sb = new StringBundler(2 * array.length);

		for (int i = 0; i < array.length; i++) {
			sb.append(toStringFunction.apply(array[i]));
			sb.append(delimiter);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static <T> String merge(T[] array, String delimiter) {
		return merge(array, String::valueOf, delimiter);
	}

	public static List<String> split(String s) {
		return split(s, CharPool.COMMA);
	}

	public static List<String> split(String s, char delimiter) {
		if ((s == null) || s.isEmpty()) {
			return Collections.emptyList();
		}

		s = s.trim();

		if (s.isEmpty()) {
			return Collections.emptyList();
		}

		List<String> elements = new ArrayList<>();

		_split(elements, s, delimiter);

		return elements;
	}

	private static void _split(List<String> values, String s, char delimiter) {
		int offset = 0;
		int pos;

		while ((pos = s.indexOf(delimiter, offset)) != -1) {
			if (offset < pos) {
				values.add(s.substring(offset, pos));
			}

			offset = pos + 1;
		}

		if (offset < s.length()) {
			values.add(s.substring(offset));
		}
	}

}