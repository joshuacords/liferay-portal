/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instances.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PortalInstancesLocalService}.
 *
 * @author Michael C. Han
 * @see PortalInstancesLocalService
 * @generated
 */
public class PortalInstancesLocalServiceWrapper
	implements PortalInstancesLocalService,
			   ServiceWrapper<PortalInstancesLocalService> {

	public PortalInstancesLocalServiceWrapper(
		PortalInstancesLocalService portalInstancesLocalService) {

		_portalInstancesLocalService = portalInstancesLocalService;
	}

	@Override
	public void addCompanyId(long companyId) {
		_portalInstancesLocalService.addCompanyId(companyId);
	}

	@Override
	public long getCompanyId(
		javax.servlet.http.HttpServletRequest httpServletRequest) {

		return _portalInstancesLocalService.getCompanyId(httpServletRequest);
	}

	@Override
	public long[] getCompanyIds() {
		return _portalInstancesLocalService.getCompanyIds();
	}

	@Override
	public long[] getCompanyIdsBySQL() throws java.sql.SQLException {
		return _portalInstancesLocalService.getCompanyIdsBySQL();
	}

	@Override
	public long getDefaultCompanyId() {
		return _portalInstancesLocalService.getDefaultCompanyId();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _portalInstancesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String[] getWebIds() {
		return _portalInstancesLocalService.getWebIds();
	}

	@Override
	public void initializePortalInstance(
		javax.servlet.ServletContext servletContext, String webId) {

		_portalInstancesLocalService.initializePortalInstance(
			servletContext, webId);
	}

	@Override
	public boolean isAutoLoginIgnoreHost(String host) {
		return _portalInstancesLocalService.isAutoLoginIgnoreHost(host);
	}

	@Override
	public boolean isAutoLoginIgnorePath(String path) {
		return _portalInstancesLocalService.isAutoLoginIgnorePath(path);
	}

	@Override
	public boolean isCompanyActive(long companyId) {
		return _portalInstancesLocalService.isCompanyActive(companyId);
	}

	@Override
	public boolean isVirtualHostsIgnoreHost(String host) {
		return _portalInstancesLocalService.isVirtualHostsIgnoreHost(host);
	}

	@Override
	public boolean isVirtualHostsIgnorePath(String path) {
		return _portalInstancesLocalService.isVirtualHostsIgnorePath(path);
	}

	@Override
	public void reload(javax.servlet.ServletContext servletContext) {
		_portalInstancesLocalService.reload(servletContext);
	}

	@Override
	public void removeCompany(long companyId) {
		_portalInstancesLocalService.removeCompany(companyId);
	}

	@Override
	public void synchronizePortalInstances() {
		_portalInstancesLocalService.synchronizePortalInstances();
	}

	@Override
	public PortalInstancesLocalService getWrappedService() {
		return _portalInstancesLocalService;
	}

	@Override
	public void setWrappedService(
		PortalInstancesLocalService portalInstancesLocalService) {

		_portalInstancesLocalService = portalInstancesLocalService;
	}

	private PortalInstancesLocalService _portalInstancesLocalService;

}