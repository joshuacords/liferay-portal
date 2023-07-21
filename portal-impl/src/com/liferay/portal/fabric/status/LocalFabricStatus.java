/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.status;

import com.liferay.petra.concurrent.DefaultNoticeableFuture;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;

import java.io.Serializable;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class LocalFabricStatus implements FabricStatus {

	public static final FabricStatus INSTANCE = new LocalFabricStatus();

	@Override
	public AdvancedOperatingSystemMXBean getAdvancedOperatingSystemMXBean() {
		OperatingSystemMXBean operatingSystemMXBean =
			ManagementFactory.getOperatingSystemMXBean();

		return JMXProxyUtil.newProxy(
			operatingSystemMXBean.getObjectName(),
			AdvancedOperatingSystemMXBean.class, processCallableExecutor);
	}

	@Override
	public List<BufferPoolMXBean> getBufferPoolMXBeans() {
		return ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
	}

	@Override
	public ClassLoadingMXBean getClassLoadingMXBean() {
		return ManagementFactory.getClassLoadingMXBean();
	}

	@Override
	public CompilationMXBean getCompilationMXBean() {
		return ManagementFactory.getCompilationMXBean();
	}

	@Override
	public List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
		return ManagementFactory.getPlatformMXBeans(
			GarbageCollectorMXBean.class);
	}

	@Override
	public List<MemoryManagerMXBean> getMemoryManagerMXBeans() {
		return ManagementFactory.getPlatformMXBeans(MemoryManagerMXBean.class);
	}

	@Override
	public MemoryMXBean getMemoryMXBean() {
		return ManagementFactory.getMemoryMXBean();
	}

	@Override
	public List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
		return ManagementFactory.getPlatformMXBeans(MemoryPoolMXBean.class);
	}

	@Override
	public PlatformLoggingMXBean getPlatformLoggingMXBean() {
		return ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);
	}

	@Override
	public RuntimeMXBean getRuntimeMXBean() {
		return ManagementFactory.getRuntimeMXBean();
	}

	@Override
	public ThreadMXBean getThreadMXBean() {
		return ManagementFactory.getThreadMXBean();
	}

	protected static final JMXProxyUtil.ProcessCallableExecutor
		processCallableExecutor = new JMXProxyUtil.ProcessCallableExecutor() {

			@Override
			public <V extends Serializable> NoticeableFuture<V> execute(
				ProcessCallable<V> processCallable) {

				DefaultNoticeableFuture<V> defaultNoticeableFuture =
					new DefaultNoticeableFuture<>();

				try {
					defaultNoticeableFuture.set(processCallable.call());
				}
				catch (ProcessException processException) {
					defaultNoticeableFuture.setException(processException);
				}

				return defaultNoticeableFuture;
			}

		};

	private LocalFabricStatus() {
	}

}