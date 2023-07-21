/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.reflect.ObjectGraphUtil.AnnotatedFieldMappingVisitor;
import com.liferay.portal.fabric.repository.RepositoryHelperUtil;

import java.io.File;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.nio.file.Path;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class FabricPathMappingVisitor extends AnnotatedFieldMappingVisitor {

	public FabricPathMappingVisitor(
		Class<? extends Annotation> annotationClass,
		Path remoteRepositoryPath) {

		this(annotationClass, remoteRepositoryPath, false);
	}

	public FabricPathMappingVisitor(
		Class<? extends Annotation> annotationClass, Path remoteRepositoryPath,
		boolean reverseMapping) {

		super(
			Collections.<Class<?>>singleton(ProcessCallable.class),
			Collections.<Class<? extends Annotation>>singleton(annotationClass),
			Collections.<Class<?>>singleton(File.class));

		_remoteRepositoryPath = remoteRepositoryPath;
		_reverseMapping = reverseMapping;
	}

	public Map<Path, Path> getPathMap() {
		return _pathMap;
	}

	@Override
	protected Object doMap(Field field, Object value) {
		File file = (File)value;

		Path path = file.toPath();

		Path mappedPath = RepositoryHelperUtil.getRepositoryFilePath(
			_remoteRepositoryPath, path);

		if (_reverseMapping) {
			_pathMap.put(mappedPath, path);
		}
		else {
			_pathMap.put(path, mappedPath);
		}

		return mappedPath.toFile();
	}

	private final Map<Path, Path> _pathMap = new HashMap<>();
	private final Path _remoteRepositoryPath;
	private final boolean _reverseMapping;

}