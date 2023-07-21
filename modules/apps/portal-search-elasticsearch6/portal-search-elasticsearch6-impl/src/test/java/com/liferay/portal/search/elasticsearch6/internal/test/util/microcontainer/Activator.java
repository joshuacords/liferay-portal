/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.test.util.microcontainer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author André de Oliveira
 */
public class Activator {

	public Activator(Object component) {
		_component = component;

		_class = component.getClass();

		_classNode = ASMUtil.getClassNode(component.getClass());
	}

	public void activate() {
		Optional<Method> optional = findMethodActivate();

		optional.ifPresent(this::invokeActivate);
	}

	protected Optional<Method> findMethodActivate() {
		return Stream.of(
			_class.getMethods()
		).filter(
			method -> Objects.equals(method.getName(), "activate")
		).findAny();
	}

	protected void invokeActivate(Method method) {
		try {
			Parameter[] parameters = method.getParameters();

			if (parameters.length == 0) {
				method.invoke(_component);
			}
			else {
				method.invoke(
					_component,
					ComponentPropertyMapUtil.getComponentPropertyMap(
						_classNode));
			}
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private final Class<? extends Object> _class;
	private final ClassNode _classNode;
	private final Object _component;

}