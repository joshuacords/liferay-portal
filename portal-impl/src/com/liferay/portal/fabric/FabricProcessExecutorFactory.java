/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric;

import com.liferay.petra.process.ProcessExecutor;
import com.liferay.portal.fabric.agent.FabricAgentRegistry;
import com.liferay.portal.fabric.agent.PortalClassPathWarmupFabricAgentListener;
import com.liferay.portal.fabric.agent.selectors.FabricAgentSelector;
import com.liferay.portal.util.PropsValues;

/**
 * @author Shuyang Zhou
 */
public class FabricProcessExecutorFactory {

	public static ProcessExecutor createFabricProcessExecutor(
			FabricAgentRegistry fabricAgentRegistry,
			ProcessExecutor processExecutor)
		throws Exception {

		if (!PropsValues.PORTAL_FABRIC_ENABLED) {
			return processExecutor;
		}

		if (PropsValues.PORTAL_FABRIC_SERVER_WARMUP_AGENT_ON_REGISTER) {
			fabricAgentRegistry.registerFabricAgentListener(
				new PortalClassPathWarmupFabricAgentListener());
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		Class<? extends FabricAgentSelector> fabricAgentSelectorClass =
			(Class<? extends FabricAgentSelector>)classLoader.loadClass(
				PropsValues.PORTAL_FABRIC_AGENT_SELECTOR_CLASS);

		return new FabricProcessExecutor(
			fabricAgentRegistry, fabricAgentSelectorClass.newInstance());
	}

}