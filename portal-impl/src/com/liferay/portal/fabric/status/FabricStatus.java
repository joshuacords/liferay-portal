/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.status;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public interface FabricStatus {

	public AdvancedOperatingSystemMXBean getAdvancedOperatingSystemMXBean();

	public List<BufferPoolMXBean> getBufferPoolMXBeans();

	public ClassLoadingMXBean getClassLoadingMXBean();

	public CompilationMXBean getCompilationMXBean();

	public List<GarbageCollectorMXBean> getGarbageCollectorMXBeans();

	public List<MemoryManagerMXBean> getMemoryManagerMXBeans();

	public MemoryMXBean getMemoryMXBean();

	public List<MemoryPoolMXBean> getMemoryPoolMXBeans();

	public PlatformLoggingMXBean getPlatformLoggingMXBean();

	public RuntimeMXBean getRuntimeMXBean();

	public ThreadMXBean getThreadMXBean();

}