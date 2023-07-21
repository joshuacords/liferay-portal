/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cluster;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tina Tian
 */
public class ClusterInvokeAcceptorUtil {

	public static ClusterInvokeAcceptor getClusterInvokeAcceptor(
		String clusterInvokeAcceptorName) {

		return _clusterInvokeAcceptors.get(clusterInvokeAcceptorName);
	}

	private ClusterInvokeAcceptorUtil() {
	}

	private static final Map<String, ClusterInvokeAcceptor>
		_clusterInvokeAcceptors = new ConcurrentHashMap<>();
	private static final ServiceTracker
		<ClusterInvokeAcceptor, ClusterInvokeAcceptor> _serviceTracker;

	private static class ClusterInvokeAcceptorServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<ClusterInvokeAcceptor, ClusterInvokeAcceptor> {

		@Override
		public ClusterInvokeAcceptor addingService(
			ServiceReference<ClusterInvokeAcceptor> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			ClusterInvokeAcceptor clusterInvokeAcceptor = registry.getService(
				serviceReference);

			Class<?> clazz = clusterInvokeAcceptor.getClass();

			_clusterInvokeAcceptors.put(clazz.getName(), clusterInvokeAcceptor);

			return clusterInvokeAcceptor;
		}

		@Override
		public void modifiedService(
			ServiceReference<ClusterInvokeAcceptor> serviceReference,
			ClusterInvokeAcceptor clusterInvokeAcceptor) {

			Class<?> clazz = clusterInvokeAcceptor.getClass();

			_clusterInvokeAcceptors.put(clazz.getName(), clusterInvokeAcceptor);
		}

		@Override
		public void removedService(
			ServiceReference<ClusterInvokeAcceptor> serviceReference,
			ClusterInvokeAcceptor clusterInvokeAcceptor) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			Class<?> clazz = clusterInvokeAcceptor.getClass();

			_clusterInvokeAcceptors.remove(clazz.getName());
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			ClusterInvokeAcceptor.class,
			new ClusterInvokeAcceptorServiceTrackerCustomizer());

		_serviceTracker.open();
	}

}