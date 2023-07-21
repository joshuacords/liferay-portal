/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.test.util.microcontainer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
public class ComponentPropertyMapUtil {

	public static Map<String, String> getComponentPropertyMap(
		ClassNode classNode) {

		AnnotationNode annotationNode = findComponentAnnotationNode(classNode);

		List<String> propertyLines = getPropertyAttributeValues(annotationNode);

		return MapUtil.toLinkedHashMap(
			ArrayUtil.toStringArray(propertyLines), StringPool.EQUAL);
	}

	protected static AnnotationNode findComponentAnnotationNode(
		ClassNode classNode) {

		List<AnnotationNode> annotationNodes = getInvisibleAnnotations(
			classNode);

		Stream<AnnotationNode> stream = annotationNodes.stream();

		return stream.filter(
			annotationNode -> annotationNode.desc.contains(
				Component.class.getSimpleName())
		).findAny(
		).get();
	}

	protected static List<AnnotationNode> getInvisibleAnnotations(
		ClassNode classNode) {

		List<AnnotationNode> annotationNodes = classNode.invisibleAnnotations;

		if (annotationNodes == null) {
			ClassNode superClassNode = ASMUtil.getClassNode(
				ASMUtil.getClassReader(classNode.superName));

			return superClassNode.invisibleAnnotations;
		}

		return annotationNodes;
	}

	protected static List<String> getPropertyAttributeValues(
		AnnotationNode annotationNode) {

		List<?> values = annotationNode.values;

		int i = values.indexOf("property");

		return (List<String>)values.get(i + 1);
	}

}