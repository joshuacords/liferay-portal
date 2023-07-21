/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessConfig;
import com.liferay.petra.process.ProcessException;
import com.liferay.petra.process.ProcessExecutor;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.fabric.agent.FabricAgentRegistry;
import com.liferay.portal.fabric.agent.selectors.FabricAgentSelector;
import com.liferay.portal.fabric.worker.FabricWorker;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Shuyang Zhou
 */
public class FabricProcessExecutor implements ProcessExecutor {

	public FabricProcessExecutor(
		FabricAgentRegistry fabricAgentRegistry,
		FabricAgentSelector fabricAgentSelector) {

		if (fabricAgentRegistry == null) {
			throw new NullPointerException("Fabric agent registry is null");
		}

		if (fabricAgentSelector == null) {
			throw new NullPointerException("Fabric agent selector is null");
		}

		_fabricAgentRegistry = fabricAgentRegistry;
		_fabricAgentSelector = fabricAgentSelector;
	}

	@Override
	public <T extends Serializable> FabricWorker<T> execute(
			ProcessConfig processConfig, ProcessCallable<T> processCallable)
		throws ProcessException {

		FabricAgent fabricAgent = getFabricAgent(processCallable);

		return fabricAgent.execute(processConfig, processCallable);
	}

	protected FabricAgent getFabricAgent(ProcessCallable<?> processCallable) {
		Collection<FabricAgent> fabricAgents = _fabricAgentSelector.select(
			_fabricAgentRegistry.getFabricAgents(), processCallable);

		if (fabricAgents.isEmpty()) {
			return _fabricAgentRegistry.getDefaultFabricAgent();
		}

		Iterator<FabricAgent> iterator = fabricAgents.iterator();

		return iterator.next();
	}

	private final FabricAgentRegistry _fabricAgentRegistry;
	private final FabricAgentSelector _fabricAgentSelector;

}