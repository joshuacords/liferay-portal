/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.service.impl;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.service.base.CommerceDataIntegrationProcessLogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceDataIntegrationProcessLogLocalServiceImpl
	extends CommerceDataIntegrationProcessLogLocalServiceBaseImpl {

	@Override
	public CommerceDataIntegrationProcessLog
			addCommerceDataIntegrationProcessLog(
				long userId, long commerceDataIntegrationProcessId,
				String error, String output, int status, Date startDate,
				Date endDate)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			commerceDataIntegrationProcessLogPersistence.create(
				counterLocalService.increment());

		commerceDataIntegrationProcessLog.setCompanyId(user.getCompanyId());
		commerceDataIntegrationProcessLog.setUserId(user.getUserId());
		commerceDataIntegrationProcessLog.setUserName(user.getFullName());
		commerceDataIntegrationProcessLog.setCDataIntegrationProcessId(
			commerceDataIntegrationProcessId);
		commerceDataIntegrationProcessLog.setError(error);
		commerceDataIntegrationProcessLog.setOutput(output);
		commerceDataIntegrationProcessLog.setStartDate(startDate);
		commerceDataIntegrationProcessLog.setEndDate(endDate);
		commerceDataIntegrationProcessLog.setStatus(status);

		return commerceDataIntegrationProcessLogPersistence.update(
			commerceDataIntegrationProcessLog);
	}

	@Override
	public void deleteCommerceDataIntegrationProcessLogs(
		long commerceDataIntegrationProcessId) {

		commerceDataIntegrationProcessLogPersistence.
			removeByCDataIntegrationProcessId(commerceDataIntegrationProcessId);
	}

	@Override
	public List<CommerceDataIntegrationProcessLog>
		getCommerceDataIntegrationProcessLogs(
			long commerceDataIntegrationProcessId, int start, int end) {

		return commerceDataIntegrationProcessLogPersistence.
			findByCDataIntegrationProcessId(
				commerceDataIntegrationProcessId, start, end);
	}

	@Override
	public int getCommerceDataIntegrationProcessLogsCount(
		long commerceDataIntegrationProcessId) {

		return commerceDataIntegrationProcessLogPersistence.
			countByCDataIntegrationProcessId(commerceDataIntegrationProcessId);
	}

	@Override
	public CommerceDataIntegrationProcessLog
			updateCommerceDataIntegrationProcessLog(
				long cDataIntegrationProcessLogId, String error, String output,
				int status, Date endDate)
		throws PortalException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			commerceDataIntegrationProcessLogPersistence.findByPrimaryKey(
				cDataIntegrationProcessLogId);

		commerceDataIntegrationProcessLog.setError(error);
		commerceDataIntegrationProcessLog.setOutput(output);
		commerceDataIntegrationProcessLog.setEndDate(endDate);
		commerceDataIntegrationProcessLog.setStatus(status);

		return commerceDataIntegrationProcessLogPersistence.update(
			commerceDataIntegrationProcessLog);
	}

}