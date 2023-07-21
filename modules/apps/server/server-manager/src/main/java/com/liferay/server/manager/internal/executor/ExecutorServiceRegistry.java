/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = ExecutorServiceRegistry.class)
public class ExecutorServiceRegistry {

	public Set<String> getAvailableExecutorPaths() {
		return Collections.unmodifiableSet(_executors.keySet());
	}

	public Executor getExecutor(String executorPath) {
		return _executors.get(executorPath);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void registerExecutor(
		Executor executor, Map<String, Object> properties) {

		String executorPath = MapUtil.getString(
			properties, "server.manager.executor.path");

		if (Validator.isNull(executorPath)) {
			throw new IllegalArgumentException(
				"The property \"server.manager.executor.path\" is null");
		}

		_executors.put(executorPath, executor);
	}

	protected synchronized void unregisterExecutor(
		Executor executor, Map<String, Object> properties) {

		String executorPath = MapUtil.getString(
			properties, "server.manager.executor.path");

		_executors.remove(executorPath);
	}

	private final Map<String, Executor> _executors = new HashMap<>();

}