/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.internal.release;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Version;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miguel Pastor
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true, service = {ModelListener.class, ReleasePublisher.class}
)
public final class ReleasePublisher extends BaseModelListener<Release> {

	@Override
	public void onAfterRemove(Release release) throws ModelListenerException {
		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				ServiceRegistration<Release> serviceRegistration =
					_serviceConfiguratorRegistrations.remove(
						release.getServletContextName());

				if (serviceRegistration != null) {
					serviceRegistration.unregister();
				}

				return null;
			});
	}

	public ServiceRegistration<Release> publish(Release release) {
		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			"release.bundle.symbolic.name", release.getBundleSymbolicName());
		properties.put("release.state", release.getState());

		try {
			if (Validator.isNotNull(release.getSchemaVersion())) {
				Version version = new Version(release.getSchemaVersion());

				properties.put("release.schema.version", version);
			}
		}
		catch (IllegalArgumentException illegalArgumentException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Invalid schema version for release: " + release,
					illegalArgumentException);
			}
		}

		ServiceRegistration<Release> newServiceRegistration =
			_bundleContext.registerService(Release.class, release, properties);

		return _serviceConfiguratorRegistrations.put(
			release.getServletContextName(), newServiceRegistration);
	}

	public ServiceRegistration<Release> publishInProgress(Release release) {
		release.setState(_STATE_IN_PROGRESS);

		return publish(release);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		List<Release> releases = _releaseLocalService.getReleases(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Release release : releases) {
			publish(release);
		}
	}

	@Deactivate
	protected void deactivate() {
		for (ServiceRegistration<Release> serviceRegistration :
				_serviceConfiguratorRegistrations.values()) {

			serviceRegistration.unregister();
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setReleaseLocalService(
		ReleaseLocalService releaseLocalService) {

		_releaseLocalService = releaseLocalService;
	}

	private static final int _STATE_IN_PROGRESS = -1;

	private static final Log _log = LogFactoryUtil.getLog(
		ReleasePublisher.class);

	private BundleContext _bundleContext;
	private ReleaseLocalService _releaseLocalService;
	private final Map<String, ServiceRegistration<Release>>
		_serviceConfiguratorRegistrations = new HashMap<>();

}