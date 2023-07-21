/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.pool.metrics.ConnectionPoolMetrics;
import com.liferay.portal.kernel.test.rule.ClassTestRule;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.runner.Description;

/**
 * @author Tom Wang
 */
public class JDBCConnectionLeakDetectionClassTestRule
	extends ClassTestRule<Collection<ServiceReference<ConnectionPoolMetrics>>> {

	public static final JDBCConnectionLeakDetectionClassTestRule INSTANCE =
		new JDBCConnectionLeakDetectionClassTestRule();

	@Override
	public void afterClass(
		Description description,
		Collection<ServiceReference<ConnectionPoolMetrics>> serviceReferences) {

		for (ServiceReference<ConnectionPoolMetrics> serviceReference :
				serviceReferences) {

			ConnectionPoolMetrics connectionPoolMetrics = _registry.getService(
				serviceReference);

			int previousActiveNumber = _connectionPoolActiveNumbers.remove(
				connectionPoolMetrics.getConnectionPoolName());

			int currentActiveNumber = connectionPoolMetrics.getNumActive();

			Assert.assertTrue(
				StringBundler.concat(
					"Active connection count increased after test for ",
					connectionPoolMetrics.getConnectionPoolName(),
					" previous active number: ", previousActiveNumber,
					", current active number: ", currentActiveNumber),
				previousActiveNumber >= currentActiveNumber);

			_registry.ungetService(serviceReference);
		}
	}

	@Override
	public Collection<ServiceReference<ConnectionPoolMetrics>> beforeClass(
			Description description)
		throws Exception {

		_registry = RegistryUtil.getRegistry();

		Collection<ServiceReference<ConnectionPoolMetrics>> serviceReferences =
			_registry.getServiceReferences(ConnectionPoolMetrics.class, null);

		Assert.assertTrue(
			"Number of connection pool should be 2 or more: " +
				serviceReferences,
			serviceReferences.size() >= 2);

		for (ServiceReference<ConnectionPoolMetrics> serviceReference :
				serviceReferences) {

			ConnectionPoolMetrics connectionPoolMetrics = _registry.getService(
				serviceReference);

			_connectionPoolActiveNumbers.put(
				connectionPoolMetrics.getConnectionPoolName(),
				connectionPoolMetrics.getNumActive());

			_registry.ungetService(serviceReference);
		}

		return serviceReferences;
	}

	private static Registry _registry;

	private final Map<String, Integer> _connectionPoolActiveNumbers =
		new HashMap<>();

}