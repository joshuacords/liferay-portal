/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent.selectors;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.fabric.status.AdvancedOperatingSystemMXBean;
import com.liferay.portal.fabric.status.FabricStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Shuyang Zhou
 */
public class MinSystemCPULoadFabricAgentSelector
	implements FabricAgentSelector {

	@Override
	public Collection<FabricAgent> select(
		Collection<FabricAgent> fabricAgents,
		ProcessCallable<?> processCallable) {

		if (fabricAgents.isEmpty()) {
			return fabricAgents;
		}

		return Collections.singleton(
			Collections.min(fabricAgents, _COMPARATOR));
	}

	private static final Comparator<FabricAgent> _COMPARATOR =
		new SystemCPULoadComparator();

	private static class SystemCPULoadComparator
		implements Comparator<FabricAgent> {

		@Override
		public int compare(FabricAgent fabricAgent1, FabricAgent fabricAgent2) {
			FabricStatus fabricStatus1 = fabricAgent1.getFabricStatus();

			AdvancedOperatingSystemMXBean advancedOperatingSystemMXBean1 =
				fabricStatus1.getAdvancedOperatingSystemMXBean();

			Double systemCpuLoad1 =
				advancedOperatingSystemMXBean1.getSystemCpuLoad();

			FabricStatus fabricStatus2 = fabricAgent2.getFabricStatus();

			AdvancedOperatingSystemMXBean advancedOperatingSystemMXBean2 =
				fabricStatus2.getAdvancedOperatingSystemMXBean();

			Double systemCpuLoad2 =
				advancedOperatingSystemMXBean2.getSystemCpuLoad();

			if ((systemCpuLoad1 == null) && (systemCpuLoad2 == null)) {
				return 0;
			}

			if (systemCpuLoad1 == null) {
				return 1;
			}

			if (systemCpuLoad2 == null) {
				return -1;
			}

			return systemCpuLoad1.compareTo(systemCpuLoad2);
		}

	}

}