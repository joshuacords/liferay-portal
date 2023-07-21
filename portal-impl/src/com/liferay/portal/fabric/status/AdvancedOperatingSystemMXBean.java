/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.status;

import java.lang.management.OperatingSystemMXBean;

/**
 * @author Shuyang Zhou
 */
public interface AdvancedOperatingSystemMXBean extends OperatingSystemMXBean {

	@JMXProxyUtil.Optional
	public Long getCommittedVirtualMemorySize();

	@JMXProxyUtil.Optional
	public Long getFreePhysicalMemorySize();

	@JMXProxyUtil.Optional
	public Long getFreeSwapSpaceSize();

	@JMXProxyUtil.Optional
	public Long getMaxFileDescriptorCount();

	@JMXProxyUtil.Optional
	public Long getOpenFileDescriptorCount();

	@JMXProxyUtil.Optional
	public Double getProcessCpuLoad();

	@JMXProxyUtil.Optional
	public Long getProcessCpuTime();

	@JMXProxyUtil.Optional
	public Double getSystemCpuLoad();

	@JMXProxyUtil.Optional
	public Long getTotalPhysicalMemorySize();

	@JMXProxyUtil.Optional
	public Long getTotalSwapSpaceSize();

}