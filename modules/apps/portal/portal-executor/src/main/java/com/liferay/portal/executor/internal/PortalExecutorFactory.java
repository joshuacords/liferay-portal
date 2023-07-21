/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.executor.internal;

import com.liferay.portal.kernel.concurrent.AbortPolicy;
import com.liferay.portal.kernel.concurrent.ClearThreadLocalThreadPoolHandler;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.executor.PortalExecutorConfig;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Shuyang Zhou
 */
@Component(service = PortalExecutorFactory.class)
public class PortalExecutorFactory {

	public static final String DEFAULT_CONFIG_NAME = "default";

	public ThreadPoolExecutor createPortalExecutor(String executorName) {
		PortalExecutorConfig portalExecutorConfig = getPortalExecutorConfig(
			executorName);

		return new ThreadPoolExecutor(
			portalExecutorConfig.getCorePoolSize(),
			portalExecutorConfig.getMaxPoolSize(),
			portalExecutorConfig.getKeepAliveTime(),
			portalExecutorConfig.getTimeUnit(),
			portalExecutorConfig.isAllowCoreThreadTimeout(),
			portalExecutorConfig.getMaxQueueSize(),
			portalExecutorConfig.getRejectedExecutionHandler(),
			portalExecutorConfig.getThreadFactory(),
			portalExecutorConfig.getThreadPoolHandler());
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addPortalExecutorConfig(
		PortalExecutorConfig portalExecutorConfig) {

		_portalExecutorConfigs.putIfAbsent(
			portalExecutorConfig.getName(), portalExecutorConfig);
	}

	protected PortalExecutorConfig getPortalExecutorConfig(String name) {
		PortalExecutorConfig portalExecutorConfig = _portalExecutorConfigs.get(
			name);

		if (portalExecutorConfig != null) {
			return portalExecutorConfig;
		}

		portalExecutorConfig = _portalExecutorConfigs.get(DEFAULT_CONFIG_NAME);

		if (portalExecutorConfig != null) {
			return portalExecutorConfig;
		}

		return _defaultPortalExecutorConfig;
	}

	protected void removePortalExecutorConfig(
		PortalExecutorConfig portalExecutorConfig) {

		_portalExecutorConfigs.remove(
			portalExecutorConfig.getName(), portalExecutorConfig);
	}

	private final PortalExecutorConfig _defaultPortalExecutorConfig =
		new PortalExecutorConfig(
			DEFAULT_CONFIG_NAME, 0, 10, 60, TimeUnit.SECONDS, true,
			Integer.MAX_VALUE, new AbortPolicy(),
			new ClearThreadLocalThreadPoolHandler(), Thread.NORM_PRIORITY,
			PortalClassLoaderUtil.getClassLoader());
	private final ConcurrentMap<String, PortalExecutorConfig>
		_portalExecutorConfigs = new ConcurrentHashMap<>();

}