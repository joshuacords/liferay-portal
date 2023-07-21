/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.test.util.microcontainer;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author André de Oliveira
 */
public class ASMUtil {

	public static ClassNode getClassNode(Class<?> clazz) {
		return getClassNode(clazz.getName());
	}

	public static ClassNode getClassNode(ClassReader classReader) {
		ClassNode classNode = new ClassNode();

		classReader.accept(classNode, ClassReader.SKIP_CODE);

		return classNode;
	}

	public static ClassReader getClassReader(String name) {
		try {
			return new ClassReader(name);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	protected static ClassNode getClassNode(String name) {
		return getClassNode(getClassReader(name));
	}

}