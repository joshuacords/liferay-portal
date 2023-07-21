/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.javadoc;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;

/**
 * @author Igor Spasic
 */
public class JavadocUtil {

	public static Class<?> loadClass(ClassLoader classLoader, String className)
		throws ClassNotFoundException {

		className = _getLoadableClassName(className);

		if ((className.indexOf('.') == -1) || (className.indexOf('[') == -1)) {
			int primitiveIndex = _getPrimitiveIndex(className);

			if (primitiveIndex >= 0) {
				return _PRIMITIVE_TYPES[primitiveIndex];
			}
		}

		if (classLoader != null) {
			try {
				return classLoader.loadClass(className);
			}
			catch (ClassNotFoundException classNotFoundException) {
			}
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (classLoader != contextClassLoader) {
			try {
				return contextClassLoader.loadClass(className);
			}
			catch (ClassNotFoundException classNotFoundException) {
			}
		}

		return Class.forName(className);
	}

	private static String _getLoadableClassName(String className) {
		int bracketCount = StringUtil.count(className, CharPool.OPEN_BRACKET);

		if (bracketCount == 0) {
			return className;
		}

		StringBuilder sb = new StringBuilder(bracketCount);

		for (int i = 0; i < bracketCount; i++) {
			sb.append('[');
		}

		int bracketIndex = className.indexOf('[');

		className = className.substring(0, bracketIndex);

		int primitiveIndex = _getPrimitiveIndex(className);

		if (primitiveIndex >= 0) {
			className = String.valueOf(
				_PRIMITIVE_BYTECODE_NAME[primitiveIndex]);

			return sb.toString() + className;
		}

		return StringBundler.concat(sb, "L", className, ";");
	}

	private static int _getPrimitiveIndex(String className) {
		if (className.indexOf('.') != -1) {
			return -1;
		}

		return Arrays.binarySearch(_PRIMITIVE_TYPE_NAMES, className);
	}

	private static final char[] _PRIMITIVE_BYTECODE_NAME = {
		'Z', 'B', 'C', 'D', 'F', 'I', 'J', 'S'
	};

	private static final String[] _PRIMITIVE_TYPE_NAMES = {
		"boolean", "byte", "char", "double", "float", "int", "long", "short"
	};

	private static final Class<?>[] _PRIMITIVE_TYPES = new Class<?>[] {
		boolean.class, byte.class, char.class, double.class, float.class,
		int.class, long.class, short.class
	};

}