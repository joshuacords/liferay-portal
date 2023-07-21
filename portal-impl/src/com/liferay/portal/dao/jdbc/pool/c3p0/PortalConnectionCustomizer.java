/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.pool.c3p0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PropsUtil;

import com.mchange.v2.c3p0.ConnectionCustomizer;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalConnectionCustomizer implements ConnectionCustomizer {

	@Override
	public void onAcquire(
			Connection connection, String parentDataSourceIdentityToken)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("JDBC property prefix " + parentDataSourceIdentityToken);
		}

		String transactionIsolation = PropsUtil.get(
			parentDataSourceIdentityToken + "transactionIsolation");

		if (_log.isDebugEnabled()) {
			_log.debug("Custom transaction isolation " + transactionIsolation);
		}

		if (transactionIsolation != null) {
			connection.setTransactionIsolation(
				GetterUtil.getInteger(transactionIsolation));
		}
	}

	@Override
	public void onCheckIn(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	@Override
	public void onCheckOut(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	@Override
	public void onDestroy(
		Connection connection, String parentDataSourceIdentityToken) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalConnectionCustomizer.class);

}