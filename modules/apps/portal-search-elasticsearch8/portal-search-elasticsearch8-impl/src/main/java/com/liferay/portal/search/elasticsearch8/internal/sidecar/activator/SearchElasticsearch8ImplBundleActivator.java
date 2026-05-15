/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch8.internal.sidecar.activator;

import com.liferay.petra.process.ProcessChannel;
import com.liferay.petra.process.ProcessExecutor;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.events.StartupHelperUtil;
import com.liferay.portal.kernel.concurrent.SystemExecutorServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.util.ServiceLatch;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.search.elasticsearch8.internal.sidecar.PersistedProcessUtil;
import com.liferay.portal.search.elasticsearch8.internal.sidecar.SidecarManager;
import com.liferay.portal.tools.DBUpgrader;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

/**
 * @author Tina Tian
 */
public class SearchElasticsearch8ImplBundleActivator
	implements BundleActivator {

	public static Future<ObjectValuePair<ProcessChannel<Serializable>, byte[]>>
		getFuture() {

		return _future;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		File sidecarProcessFile = bundleContext.getDataFile("sidecar.process");

		if (sidecarProcessFile.exists()) {
			ServiceReference<ProcessExecutor> serviceReference =
				bundleContext.getServiceReference(ProcessExecutor.class);

			ExecutorService executorService =
				SystemExecutorServiceUtil.getExecutorService();

			_future = executorService.submit(
				() -> PersistedProcessUtil.start(
					bundleContext.getService(serviceReference),
					sidecarProcessFile));
		}

		if (!DBUpgrader.isUpgradeClient() &&
			!DBUpgrader.isUpgradeDatabaseAutoRunEnabled() &&
			!StartupHelperUtil.isUpgrading()) {

			_enableSidecarManager(bundleContext);

			return;
		}

		if (!_hasLegacyElasticsearch7Configuration(bundleContext)) {
			_enableSidecarManager(bundleContext);

			return;
		}

		Bundle bundle = bundleContext.getBundle();

		ServiceLatch serviceLatch = new ServiceLatch(bundleContext);

		serviceLatch.waitFor(
			StringBundler.concat(
				"(&(objectClass=", Release.class.getName(),
				")(release.bundle.symbolic.name=", bundle.getSymbolicName(),
				")(release.schema.version>=1.0.0))"));

		serviceLatch.openOn(() -> _enableSidecarManager(bundleContext));
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		if (_bundleListener != null) {
			bundleContext.removeBundleListener(_bundleListener);

			_bundleListener = null;
		}
	}

	private void _doEnableSidecarManager(BundleContext bundleContext) {
		ServiceReference<ServiceComponentRuntime> serviceReference =
			bundleContext.getServiceReference(ServiceComponentRuntime.class);

		ServiceComponentRuntime serviceComponentRuntime =
			bundleContext.getService(serviceReference);

		try {
			ComponentDescriptionDTO componentDescriptionDTO =
				serviceComponentRuntime.getComponentDescriptionDTO(
					bundleContext.getBundle(), SidecarManager.class.getName());

			serviceComponentRuntime.enableComponent(componentDescriptionDTO);
		}
		finally {
			bundleContext.ungetService(serviceReference);
		}
	}

	private void _enableSidecarManager(BundleContext bundleContext) {
		Bundle bundle = bundleContext.getBundle();

		if (bundle.getState() == Bundle.ACTIVE) {
			_doEnableSidecarManager(bundleContext);

			return;
		}

		_bundleListener = new BundleListener() {

			@Override
			public void bundleChanged(BundleEvent bundleEvent) {
				if ((bundleEvent.getType() != BundleEvent.STARTED) ||
					!bundle.equals(bundleEvent.getBundle())) {

					return;
				}

				bundleContext.removeBundleListener(this);

				_doEnableSidecarManager(bundleContext);

				_bundleListener = null;
			}

		};

		bundleContext.addBundleListener(_bundleListener);
	}

	private boolean _hasLegacyElasticsearch7Configuration(
		BundleContext bundleContext) {

		ServiceReference<ConfigurationAdmin> serviceReference =
			bundleContext.getServiceReference(ConfigurationAdmin.class);

		if (serviceReference == null) {
			return false;
		}

		ConfigurationAdmin configurationAdmin = bundleContext.getService(
			serviceReference);

		try {
			Configuration[] configurations =
				configurationAdmin.listConfigurations(
					"(|(service.pid=com.liferay.portal.search.elasticsearch7." +
						"configuration.*)(service.factoryPid=com.liferay." +
							"portal.search.elasticsearch7.configuration.*))");

			if ((configurations != null) && (configurations.length > 0)) {
				return true;
			}

			return false;
		}
		catch (InvalidSyntaxException | IOException exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to query ConfigurationAdmin for legacy " +
						"Elasticsearch 7 configurations",
					exception);
			}

			return false;
		}
		finally {
			bundleContext.ungetService(serviceReference);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchElasticsearch8ImplBundleActivator.class);

	private static volatile Future
		<ObjectValuePair<ProcessChannel<Serializable>, byte[]>> _future;

	private BundleListener _bundleListener;

}