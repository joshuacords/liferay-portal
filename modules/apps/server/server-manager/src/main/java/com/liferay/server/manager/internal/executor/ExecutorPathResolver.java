/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class ExecutorPathResolver {

	public static final String ROOT_EXECUTOR_PATH = StringPool.SLASH;

	public ExecutorPathResolver(Set<String> availableExecutorPaths) {
		_availableExecutorPaths = availableExecutorPaths;
	}

	public String getExecutorPath(String path) {
		String executorPath = path;

		if (Validator.isNull(executorPath)) {
			executorPath = StringPool.SLASH;
		}

		while (!executorPath.isEmpty() &&
			   !_availableExecutorPaths.contains(executorPath)) {

			int index = executorPath.lastIndexOf(StringPool.SLASH);

			executorPath = executorPath.substring(0, index);
		}

		if (!executorPath.isEmpty()) {
			return executorPath;
		}

		return ROOT_EXECUTOR_PATH;
	}

	public List<String> getNextExecutorsPaths(String path) {
		List<String> nextExecutorsPaths = new ArrayList<>();

		String executorPath = getExecutorPath(path);

		for (String availablePath : _availableExecutorPaths) {
			if (isNextExecutorPath(availablePath, executorPath)) {
				nextExecutorsPaths.add(availablePath);
			}
		}

		Collections.sort(nextExecutorsPaths);

		return nextExecutorsPaths;
	}

	protected boolean isNextExecutorPath(
		String path, String matchingExecutorPath) {

		String currentExecutorPath = matchingExecutorPath;

		if (!currentExecutorPath.equals("/")) {
			currentExecutorPath = currentExecutorPath + "/";
		}

		String nextExecutorPath = path.replaceFirst(
			currentExecutorPath, StringPool.BLANK);

		if (!nextExecutorPath.isEmpty() &&
			!nextExecutorPath.contains(StringPool.SLASH)) {

			return true;
		}

		return false;
	}

	private final Set<String> _availableExecutorPaths;

}