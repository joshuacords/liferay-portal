/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.util;

import com.liferay.petra.function.UnsafeFunction;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class TransformUtil {

	public static <T, R> List<R> transform(
		Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		List<R> list = new ArrayList<>(collection.size());

		for (T item : collection) {
			try {
				R newItem = unsafeFunction.apply(item);

				if (newItem != null) {
					list.add(newItem);
				}
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		return list;
	}

	public static <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		List<R> list = transformToList(array, unsafeFunction);

		return list.toArray((R[])Array.newInstance(clazz, 0));
	}

	public static <T, R> R[] transformToArray(
		Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		List<R> list = transform(collection, unsafeFunction);

		return list.toArray((R[])Array.newInstance(clazz, 0));
	}

	public static <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		List<R> list = new ArrayList<>(array.length);

		for (T item : array) {
			try {
				R newItem = unsafeFunction.apply(item);

				if (newItem != null) {
					list.add(newItem);
				}
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		return list;
	}

}