/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.internal.upgrade.v1_0_1;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = UpgradeLicenseKeys.class)
public class UpgradeLicenseKeys extends UpgradeProcess {

	public void upgradeDetachPermanentLicenseKeys(Connection connection)
		throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(
				"select licenseKeyId from Provisioning_LicenseKey where " +
					"expirationDate > '2100-01-01' and productPurchaseKey is " +
						"not null");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long licenseKeyId = resultSet.getLong("licenseKeyId");

				LicenseKey licenseKey = _licenseKeyLocalService.fetchLicenseKey(
					licenseKeyId);

				if (licenseKey != null) {
					licenseKey.setProductPurchaseKey(null);

					try {
						_licenseKeyLocalService.updateLicenseKey(licenseKey);
					}
					catch (Exception exception) {
						_log.error(exception);
					}
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}
		finally {
			DataAccess.cleanUp(connection, preparedStatement, resultSet);
		}
	}

	@Override
	protected void doUpgrade() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeLicenseKeys.class);

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}