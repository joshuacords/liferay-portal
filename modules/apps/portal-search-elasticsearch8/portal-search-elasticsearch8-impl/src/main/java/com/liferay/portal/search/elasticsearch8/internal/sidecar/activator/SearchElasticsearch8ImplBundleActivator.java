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
import com.liferay.portal.search.elasticsearch8.internal.sidecar.SidecarManagerReady;
import com.liferay.portal.tools.DBUpgrader;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

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
		Bundle bundle = bundleContext.getBundle();

		_log.error(
			StringBundler.concat(
				"[LPD-82794] Activator.start entered. Bundle=",
				bundle.getSymbolicName(), " state=", bundle.getState()));

		File sidecarProcessFile = bundleContext.getDataFile("sidecar.process");

		if (sidecarProcessFile.exists()) {
			_log.error(
				"[LPD-82794] sidecar.process exists; submitting persisted " +
					"process resume task");

			ServiceReference<ProcessExecutor> serviceReference =
				bundleContext.getServiceReference(ProcessExecutor.class);

			ExecutorService executorService =
				SystemExecutorServiceUtil.getExecutorService();

			_future = executorService.submit(
				() -> PersistedProcessUtil.start(
					bundleContext.getService(serviceReference),
					sidecarProcessFile));
		}
		else {
			_log.error("[LPD-82794] sidecar.process does not exist");
		}

		boolean upgradeClient = DBUpgrader.isUpgradeClient();
		boolean upgradeDatabaseAutoRunEnabled =
			DBUpgrader.isUpgradeDatabaseAutoRunEnabled();
		boolean startupHelperUpgrading = StartupHelperUtil.isUpgrading();

		_log.error(
			StringBundler.concat(
				"[LPD-82794] Upgrade signals: DBUpgrader.isUpgradeClient=",
				upgradeClient, ", DBUpgrader.isUpgradeDatabaseAutoRunEnabled=",
				upgradeDatabaseAutoRunEnabled,
				", StartupHelperUtil.isUpgrading=", startupHelperUpgrading));

		if (!upgradeClient && !upgradeDatabaseAutoRunEnabled &&
			!startupHelperUpgrading) {

			_log.error(
				"[LPD-82794] Fast path: not upgrading; publishing " +
					"SidecarManagerReady immediately");

			_publishSidecarManagerReady(bundleContext);

			_log.error("[LPD-82794] Activator.start returning (fast path)");

			return;
		}

		_log.error(
			"[LPD-82794] Upgrade in progress; checking for legacy " +
				"Elasticsearch configurations");

		if (!_hasLegacyElasticsearchConfiguration(bundleContext)) {
			_log.error(
				"[LPD-82794] Fast path: no legacy Elasticsearch configuration; " +
					"publishing SidecarManagerReady immediately");

			_publishSidecarManagerReady(bundleContext);

			_log.error("[LPD-82794] Activator.start returning (fast path)");

			return;
		}

		String filterString = StringBundler.concat(
			"(&(objectClass=", Release.class.getName(),
			")(release.bundle.symbolic.name=", bundle.getSymbolicName(),
			")(release.schema.version>=1.0.0))");

		_log.error(
			"[LPD-82794] Slow path: arming ServiceLatch with filter=" +
				filterString);

		ServiceLatch serviceLatch = new ServiceLatch(bundleContext);

		serviceLatch.waitFor(filterString);

		serviceLatch.openOn(
			() -> {
				_log.error(
					"[LPD-82794] ServiceLatch fired (Release v1_0_0+ " +
						"available); publishing SidecarManagerReady");

				_publishSidecarManagerReady(bundleContext);
			});

		_log.error(
			"[LPD-82794] Activator.start returning (slow path; latch armed)");
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		_log.error("[LPD-82794] Activator.stop entered");

		if (_sidecarManagerReadyServiceRegistration != null) {
			_sidecarManagerReadyServiceRegistration.unregister();

			_sidecarManagerReadyServiceRegistration = null;

			_log.error("[LPD-82794] SidecarManagerReady service unregistered");
		}
	}

	private boolean _hasLegacyElasticsearchConfiguration(
		BundleContext bundleContext) {

		_log.error("[LPD-82794] _hasLegacyElasticsearchConfiguration entered");

		ServiceReference<ConfigurationAdmin> serviceReference =
			bundleContext.getServiceReference(ConfigurationAdmin.class);

		if (serviceReference == null) {
			_log.error(
				"[LPD-82794] ConfigurationAdmin serviceReference is null; " +
					"returning false");

			return false;
		}

		ConfigurationAdmin configurationAdmin = bundleContext.getService(
			serviceReference);

		try {
			Configuration[] configurations =
				configurationAdmin.listConfigurations(
					"(|(service.pid=*Elasticsearch*Configuration)" +
						"(service.factoryPid=*Elasticsearch*Configuration))");

			if (configurations == null) {
				_log.error(
					"[LPD-82794] listConfigurations returned null; returning " +
						"false");

				return false;
			}

			_log.error(
				StringBundler.concat(
					"[LPD-82794] listConfigurations returned ",
					configurations.length, " configuration(s)"));

			for (Configuration configuration : configurations) {
				String className = configuration.getFactoryPid();

				if (className == null) {
					className = configuration.getPid();
				}

				boolean classLoadable = _isClassLoadable(
					bundleContext, className);

				_log.error(
					StringBundler.concat(
						"[LPD-82794] Configuration className=", className,
						" classLoadable=", classLoadable));

				if (!classLoadable) {
					_log.error(
						"[LPD-82794] Found legacy configuration; returning " +
							"true");

					return true;
				}
			}

			_log.error(
				"[LPD-82794] No legacy configuration found; returning false");

			return false;
		}
		catch (InvalidSyntaxException | IOException exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to query ConfigurationAdmin for legacy " +
						"Elasticsearch configurations",
					exception);
			}

			return false;
		}
		finally {
			bundleContext.ungetService(serviceReference);
		}
	}

	private boolean _isClassLoadable(
		BundleContext bundleContext, String className) {

		Bundle bundle = bundleContext.getBundle();

		try {
			bundle.loadClass(className);

			return true;
		}
		catch (ClassNotFoundException classNotFoundException) {
			if (_log.isDebugEnabled()) {
				_log.debug(classNotFoundException);
			}

			return false;
		}
	}

	private void _publishSidecarManagerReady(BundleContext bundleContext) {
		_log.error(
			"[LPD-82794] _publishSidecarManagerReady entered; calling " +
				"registerService");

		_sidecarManagerReadyServiceRegistration = bundleContext.registerService(
			SidecarManagerReady.class,
			new SidecarManagerReady() {
			},
			null);

		_log.error("[LPD-82794] SidecarManagerReady service registered");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchElasticsearch8ImplBundleActivator.class);

	private static volatile Future
		<ObjectValuePair<ProcessChannel<Serializable>, byte[]>> _future;

	private ServiceRegistration<SidecarManagerReady>
		_sidecarManagerReadyServiceRegistration;

}