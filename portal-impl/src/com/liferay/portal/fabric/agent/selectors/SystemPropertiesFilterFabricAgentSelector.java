/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent.selectors;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.fabric.status.FabricStatus;

import java.lang.management.RuntimeMXBean;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public abstract class SystemPropertiesFilterFabricAgentSelector
	implements FabricAgentSelector {

	@Override
	public Collection<FabricAgent> select(
		Collection<FabricAgent> fabricAgents,
		ProcessCallable<?> processCallable) {

		Iterator<FabricAgent> iterator = fabricAgents.iterator();

		while (iterator.hasNext()) {
			FabricAgent fabricAgent = iterator.next();

			FabricStatus fabricStatus = fabricAgent.getFabricStatus();

			RuntimeMXBean runtimeMXBean = fabricStatus.getRuntimeMXBean();

			if (!accept(runtimeMXBean.getSystemProperties(), processCallable)) {
				iterator.remove();
			}
		}

		return fabricAgents;
	}

	protected abstract boolean accept(
		Map<String, String> systemProperties,
		ProcessCallable<?> processCallable);

}