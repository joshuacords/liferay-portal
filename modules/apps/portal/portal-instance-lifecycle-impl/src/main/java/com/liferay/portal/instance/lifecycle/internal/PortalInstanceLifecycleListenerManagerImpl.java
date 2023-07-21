/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instance.lifecycle.internal;

import com.liferay.portal.instance.lifecycle.Clusterable;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.instance.lifecycle.PortalInstanceLifecycleManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.LocaleThreadLocal;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = PortalInstanceLifecycleManager.class)
public class PortalInstanceLifecycleListenerManagerImpl
	implements PortalInstanceLifecycleManager {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void preregisterCompany(long companyId) {
		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			preregisterCompany(portalInstanceLifecycleListener, companyId);
		}
	}

	@Override
	public void preunregisterCompany(Company company) {
		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			preunregisterCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Override
	public void registerCompany(Company company) {
		_companies.add(company);

		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			registerCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Override
	public void unregisterCompany(Company company) {
		_companies.remove(company);

		for (PortalInstanceLifecycleListener portalInstanceLifecycleListener :
				_portalInstanceLifecycleListeners) {

			unregisterCompany(portalInstanceLifecycleListener, company);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addPortalInstanceLifecycleListener(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener) {

		_portalInstanceLifecycleListeners.add(portalInstanceLifecycleListener);

		if (_companies.isEmpty()) {
			return;
		}

		for (Company company : _companies) {
			registerCompany(portalInstanceLifecycleListener, company);
		}
	}

	protected void preregisterCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		long companyId) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		portalInstanceLifecycleListener.portalInstancePreregistered(companyId);
	}

	protected void preunregisterCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		try {
			portalInstanceLifecycleListener.portalInstancePreunregistered(
				company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to preunregister portal instance " + company,
					exception);
			}
		}
	}

	protected void registerCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		Long companyId = CompanyThreadLocal.getCompanyId();
		Locale siteDefaultLocale = LocaleThreadLocal.getSiteDefaultLocale();

		try {
			CompanyThreadLocal.setCompanyId(company.getCompanyId());
			LocaleThreadLocal.setSiteDefaultLocale(null);

			portalInstanceLifecycleListener.portalInstanceRegistered(company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to register portal instance " + company, exception);
			}
		}
		finally {
			CompanyThreadLocal.setCompanyId(companyId);
			LocaleThreadLocal.setSiteDefaultLocale(siteDefaultLocale);
		}
	}

	protected void removePortalInstanceLifecycleListener(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		_portalInstanceLifecycleListeners.remove(
			portalInstanceLifecycleListener);
	}

	protected void unregisterCompany(
		PortalInstanceLifecycleListener portalInstanceLifecycleListener,
		Company company) {

		if (!(portalInstanceLifecycleListener instanceof Clusterable) &&
			!clusterMasterExecutor.isMaster()) {

			if (_log.isDebugEnabled()) {
				_log.debug("Skipping " + portalInstanceLifecycleListener);
			}

			return;
		}

		try {
			portalInstanceLifecycleListener.portalInstanceUnregistered(company);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to unregister portal instance " + company,
					exception);
			}
		}
	}

	@Reference
	protected ClusterMasterExecutor clusterMasterExecutor;

	private static final Log _log = LogFactoryUtil.getLog(
		PortalInstanceLifecycleListenerManagerImpl.class);

	private final Set<Company> _companies = new CopyOnWriteArraySet<>();
	private final Set<PortalInstanceLifecycleListener>
		_portalInstanceLifecycleListeners = new CopyOnWriteArraySet<>();

}