/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.service;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDataIntegrationProcessLog. This utility wraps
 * <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogService
 * @generated
 */
public class CommerceDataIntegrationProcessLogServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.data.integration.service.impl.CommerceDataIntegrationProcessLogServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDataIntegrationProcessLog
			addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status,
				java.util.Date startDate, java.util.Date endDate)
		throws PortalException {

		return getService().addCommerceDataIntegrationProcessLog(
			userId, commerceDataIntegrationProcessId, error, output, status,
			startDate, endDate);
	}

	public static void deleteCommerceDataIntegrationProcessLog(
			long cDataIntegrationProcessLogId)
		throws PortalException {

		getService().deleteCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId);
	}

	public static CommerceDataIntegrationProcessLog
			getCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId)
		throws PortalException {

		return getService().getCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId);
	}

	public static List<CommerceDataIntegrationProcessLog>
			getCommerceDataIntegrationProcessLogs(
				long commerceDataIntegrationProcessId, int start, int end)
		throws PortalException {

		return getService().getCommerceDataIntegrationProcessLogs(
			commerceDataIntegrationProcessId, start, end);
	}

	public static int getCommerceDataIntegrationProcessLogsCount(
			long commerceDataIntegrationProcessId)
		throws PortalException {

		return getService().getCommerceDataIntegrationProcessLogsCount(
			commerceDataIntegrationProcessId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceDataIntegrationProcessLog
			updateCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId, String error, String output,
				int status, java.util.Date endDate)
		throws PortalException {

		return getService().updateCommerceDataIntegrationProcessLog(
			cDataIntegrationProcessLogId, error, output, status, endDate);
	}

	public static CommerceDataIntegrationProcessLogService getService() {
		return _service;
	}

	public static void setService(
		CommerceDataIntegrationProcessLogService service) {

		_service = service;
	}

	private static volatile CommerceDataIntegrationProcessLogService _service;

}