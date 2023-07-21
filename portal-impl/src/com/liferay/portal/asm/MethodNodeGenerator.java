/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.asm;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author Shuyang Zhou
 */
public class MethodNodeGenerator extends GeneratorAdapter {

	public MethodNodeGenerator(Method method) {
		this(_createMethodNode(method));
	}

	public MethodNodeGenerator(MethodNode methodNode) {
		super(
			Opcodes.ASM5, methodNode, methodNode.access, methodNode.name,
			methodNode.desc);

		_methodNode = methodNode;
	}

	public MethodNode getMethodNode() {
		return _methodNode;
	}

	public void invokeInterface(String owner, Method method) {
		super.visitMethodInsn(
			Opcodes.INVOKEINTERFACE, owner, method.getName(),
			Type.getMethodDescriptor(method), true);
	}

	public void invokeInterface(
		String owner, String name, Type returnType, Type... argumentTypes) {

		super.visitMethodInsn(
			Opcodes.INVOKEINTERFACE, owner, name,
			Type.getMethodDescriptor(returnType, argumentTypes), true);
	}

	public void invokeSpecial(String owner, Method method) {
		super.visitMethodInsn(
			Opcodes.INVOKESPECIAL, owner, method.getName(),
			Type.getMethodDescriptor(method), false);
	}

	public void invokeSpecial(
		String owner, String name, Type returnType, Type... argumentTypes) {

		super.visitMethodInsn(
			Opcodes.INVOKESPECIAL, owner, name,
			Type.getMethodDescriptor(returnType, argumentTypes), false);
	}

	public void invokeStatic(String owner, Method method) {
		super.visitMethodInsn(
			Opcodes.INVOKESTATIC, owner, method.getName(),
			Type.getMethodDescriptor(method), false);
	}

	public void invokeStatic(
		String owner, String name, Type returnType, Type... argumentTypes) {

		super.visitMethodInsn(
			Opcodes.INVOKESTATIC, owner, name,
			Type.getMethodDescriptor(returnType, argumentTypes), false);
	}

	public void invokeVirtual(String owner, Method method) {
		super.visitMethodInsn(
			Opcodes.INVOKEVIRTUAL, owner, method.getName(),
			Type.getMethodDescriptor(method), false);
	}

	public void invokeVirtual(
		String owner, String name, Type returnType, Type... argumentTypes) {

		super.visitMethodInsn(
			Opcodes.INVOKEVIRTUAL, owner, name,
			Type.getMethodDescriptor(returnType, argumentTypes), false);
	}

	public void visitMethodInsn(
		int opcode, String owner, String name, Type returnType,
		Type... argumentTypes) {

		super.visitMethodInsn(
			opcode, owner, name,
			Type.getMethodDescriptor(returnType, argumentTypes), false);
	}

	private static MethodNode _createMethodNode(Method method) {
		MethodNode methodNode = new MethodNode();

		methodNode.access = method.getModifiers() & ~Modifier.ABSTRACT;
		methodNode.name = method.getName();
		methodNode.desc = Type.getMethodDescriptor(method);

		List<String> exceptions = new ArrayList<>();

		for (Class<?> exceptionClass : method.getExceptionTypes()) {
			exceptions.add(Type.getInternalName(exceptionClass));
		}

		methodNode.exceptions = exceptions;

		return methodNode;
	}

	private final MethodNode _methodNode;

}