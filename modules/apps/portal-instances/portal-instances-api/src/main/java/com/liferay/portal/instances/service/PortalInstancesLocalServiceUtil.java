/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instances.service;

/**
 * Provides the local service utility for PortalInstances. This utility wraps
 * <code>com.liferay.portal.instances.service.impl.PortalInstancesLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see PortalInstancesLocalService
 * @generated
 */
public class PortalInstancesLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.instances.service.impl.PortalInstancesLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addCompanyId(long companyId) {
		getService().addCompanyId(companyId);
	}

	public static long getCompanyId(
		javax.servlet.http.HttpServletRequest httpServletRequest) {

		return getService().getCompanyId(httpServletRequest);
	}

	public static long[] getCompanyIds() {
		return getService().getCompanyIds();
	}

	public static long[] getCompanyIdsBySQL() throws java.sql.SQLException {
		return getService().getCompanyIdsBySQL();
	}

	public static long getDefaultCompanyId() {
		return getService().getDefaultCompanyId();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.lang.String[] getWebIds() {
		return getService().getWebIds();
	}

	public static void initializePortalInstance(
		javax.servlet.ServletContext servletContext, java.lang.String webId) {

		getService().initializePortalInstance(servletContext, webId);
	}

	public static boolean isAutoLoginIgnoreHost(java.lang.String host) {
		return getService().isAutoLoginIgnoreHost(host);
	}

	public static boolean isAutoLoginIgnorePath(java.lang.String path) {
		return getService().isAutoLoginIgnorePath(path);
	}

	public static boolean isCompanyActive(long companyId) {
		return getService().isCompanyActive(companyId);
	}

	public static boolean isVirtualHostsIgnoreHost(java.lang.String host) {
		return getService().isVirtualHostsIgnoreHost(host);
	}

	public static boolean isVirtualHostsIgnorePath(java.lang.String path) {
		return getService().isVirtualHostsIgnorePath(path);
	}

	public static void reload(javax.servlet.ServletContext servletContext) {
		getService().reload(servletContext);
	}

	public static void removeCompany(long companyId) {
		getService().removeCompany(companyId);
	}

	public static void synchronizePortalInstances() {
		getService().synchronizePortalInstances();
	}

	public static PortalInstancesLocalService getService() {
		return _service;
	}

	public static void setService(PortalInstancesLocalService service) {
		_service = service;
	}

	private static volatile PortalInstancesLocalService _service;

}