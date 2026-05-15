/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch8.internal.sidecar.activator;

import com.liferay.portal.events.StartupHelperUtil;
import com.liferay.portal.kernel.module.util.ServiceLatch;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.tools.DBUpgrader;

import java.io.File;

import java.util.UUID;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.runtime.ServiceComponentRuntime;

/**
 * @author Joshua Cords
 */
public class SearchElasticsearch8ImplBundleActivatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testStartEnablesSidecarManagerWhenNotUpgrading()
		throws Exception {

		BundleContext bundleContext = _createBundleContext();

		try (MockedStatic<DBUpgrader> dbUpgraderMockedStatic =
				Mockito.mockStatic(DBUpgrader.class);
			MockedStatic<StartupHelperUtil> startupHelperUtilMockedStatic =
				Mockito.mockStatic(StartupHelperUtil.class);
			MockedConstruction<ServiceLatch> serviceLatchMockedConstruction =
				Mockito.mockConstruction(ServiceLatch.class)) {

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeClient
			).thenReturn(
				false
			);

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeDatabaseAutoRunEnabled
			).thenReturn(
				false
			);

			startupHelperUtilMockedStatic.when(
				StartupHelperUtil::isUpgrading
			).thenReturn(
				false
			);

			SearchElasticsearch8ImplBundleActivator
				searchElasticsearch8ImplBundleActivator =
					new SearchElasticsearch8ImplBundleActivator();

			searchElasticsearch8ImplBundleActivator.start(bundleContext);

			Mockito.verify(
				bundleContext, Mockito.never()
			).getServiceReference(
				ConfigurationAdmin.class
			);

			Mockito.verify(
				bundleContext
			).addBundleListener(
				Mockito.any(BundleListener.class)
			);

			Assert.assertTrue(
				serviceLatchMockedConstruction.constructed(
				).isEmpty());
		}
	}

	@Test
	public void testStartEnablesSidecarManagerWhenUpgradingWithoutLegacyConfiguration()
		throws Exception {

		BundleContext bundleContext = _createBundleContext();

		ConfigurationAdmin configurationAdmin = Mockito.mock(
			ConfigurationAdmin.class);

		Mockito.when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[0]
		);

		@SuppressWarnings("unchecked")
		ServiceReference<ConfigurationAdmin>
			configurationAdminServiceReference = Mockito.mock(
				ServiceReference.class);

		Mockito.when(
			bundleContext.getServiceReference(ConfigurationAdmin.class)
		).thenReturn(
			configurationAdminServiceReference
		);

		Mockito.when(
			bundleContext.getService(configurationAdminServiceReference)
		).thenReturn(
			configurationAdmin
		);

		try (MockedStatic<DBUpgrader> dbUpgraderMockedStatic =
				Mockito.mockStatic(DBUpgrader.class);
			MockedStatic<StartupHelperUtil> startupHelperUtilMockedStatic =
				Mockito.mockStatic(StartupHelperUtil.class);
			MockedConstruction<ServiceLatch> serviceLatchMockedConstruction =
				Mockito.mockConstruction(ServiceLatch.class)) {

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeClient
			).thenReturn(
				false
			);

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeDatabaseAutoRunEnabled
			).thenReturn(
				false
			);

			startupHelperUtilMockedStatic.when(
				StartupHelperUtil::isUpgrading
			).thenReturn(
				true
			);

			SearchElasticsearch8ImplBundleActivator
				searchElasticsearch8ImplBundleActivator =
					new SearchElasticsearch8ImplBundleActivator();

			searchElasticsearch8ImplBundleActivator.start(bundleContext);

			Mockito.verify(
				configurationAdmin
			).listConfigurations(
				Mockito.anyString()
			);

			Mockito.verify(
				bundleContext
			).addBundleListener(
				Mockito.any(BundleListener.class)
			);

			Assert.assertTrue(
				serviceLatchMockedConstruction.constructed(
				).isEmpty());
		}
	}

	@Test
	public void testStartWaitsForReleaseWhenUpgradingWithLegacyConfiguration()
		throws Exception {

		BundleContext bundleContext = _createBundleContext();

		ConfigurationAdmin configurationAdmin = Mockito.mock(
			ConfigurationAdmin.class);

		Mockito.when(
			configurationAdmin.listConfigurations(Mockito.anyString())
		).thenReturn(
			new Configuration[] {Mockito.mock(Configuration.class)}
		);

		@SuppressWarnings("unchecked")
		ServiceReference<ConfigurationAdmin>
			configurationAdminServiceReference = Mockito.mock(
				ServiceReference.class);

		Mockito.when(
			bundleContext.getServiceReference(ConfigurationAdmin.class)
		).thenReturn(
			configurationAdminServiceReference
		);

		Mockito.when(
			bundleContext.getService(configurationAdminServiceReference)
		).thenReturn(
			configurationAdmin
		);

		try (MockedStatic<DBUpgrader> dbUpgraderMockedStatic =
				Mockito.mockStatic(DBUpgrader.class);
			MockedStatic<StartupHelperUtil> startupHelperUtilMockedStatic =
				Mockito.mockStatic(StartupHelperUtil.class);
			MockedConstruction<ServiceLatch> serviceLatchMockedConstruction =
				Mockito.mockConstruction(ServiceLatch.class)) {

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeClient
			).thenReturn(
				true
			);

			dbUpgraderMockedStatic.when(
				DBUpgrader::isUpgradeDatabaseAutoRunEnabled
			).thenReturn(
				false
			);

			startupHelperUtilMockedStatic.when(
				StartupHelperUtil::isUpgrading
			).thenReturn(
				true
			);

			SearchElasticsearch8ImplBundleActivator
				searchElasticsearch8ImplBundleActivator =
					new SearchElasticsearch8ImplBundleActivator();

			searchElasticsearch8ImplBundleActivator.start(bundleContext);

			Assert.assertEquals(
				1,
				serviceLatchMockedConstruction.constructed(
				).size());

			ServiceLatch serviceLatch =
				serviceLatchMockedConstruction.constructed(
				).get(
					0
				);

			ArgumentCaptor<String> filterStringArgumentCaptor =
				ArgumentCaptor.forClass(String.class);

			Mockito.verify(
				serviceLatch
			).waitFor(
				filterStringArgumentCaptor.capture()
			);

			String filterString = filterStringArgumentCaptor.getValue();

			Assert.assertTrue(
				filterString,
				filterString.contains(
					"objectClass=com.liferay.portal.kernel.model.Release"));
			Assert.assertTrue(
				filterString,
				filterString.contains(
					"release.bundle.symbolic.name=" +
						"com.liferay.portal.search.elasticsearch8.impl"));
			Assert.assertTrue(
				filterString,
				filterString.contains("release.schema.version>=1.0.0"));

			Mockito.verify(
				serviceLatch
			).openOn(
				Mockito.any(Runnable.class)
			);

			Mockito.verify(
				bundleContext, Mockito.never()
			).addBundleListener(
				Mockito.any(BundleListener.class)
			);
		}
	}

	private BundleContext _createBundleContext() {
		BundleContext bundleContext = Mockito.mock(BundleContext.class);

		Bundle bundle = Mockito.mock(Bundle.class);

		Mockito.when(
			bundle.getSymbolicName()
		).thenReturn(
			"com.liferay.portal.search.elasticsearch8.impl"
		);

		Mockito.when(
			bundle.getState()
		).thenReturn(
			Bundle.STARTING
		);

		Mockito.when(
			bundleContext.getBundle()
		).thenReturn(
			bundle
		);

		File sidecarProcessFile = new File(
			System.getProperty("java.io.tmpdir"),
			"SearchElasticsearch8ImplBundleActivatorTest-" + UUID.randomUUID() +
				"-sidecar.process");

		Mockito.when(
			bundleContext.getDataFile("sidecar.process")
		).thenReturn(
			sidecarProcessFile
		);

		@SuppressWarnings("unchecked")
		ServiceReference<ServiceComponentRuntime>
			serviceComponentRuntimeServiceReference = Mockito.mock(
				ServiceReference.class);

		Mockito.when(
			bundleContext.getServiceReference(ServiceComponentRuntime.class)
		).thenReturn(
			serviceComponentRuntimeServiceReference
		);

		Mockito.when(
			bundleContext.getService(serviceComponentRuntimeServiceReference)
		).thenReturn(
			Mockito.mock(ServiceComponentRuntime.class)
		);

		return bundleContext;
	}

}