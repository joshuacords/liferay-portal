/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.upgrade.v1_1_2;

import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alberto Chaparro
 */
public class UpgradeDLFileEntryType extends UpgradeProcess {

	public UpgradeDLFileEntryType(ResourceLocalService resourceLocalService) {
		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select fileEntryTypeId, companyId, userId from " +
					"DLFileEntryType where fileEntryTypeKey in ('IMAGE " +
						"GALLERY IMAGE', 'Image Gallery Image')");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long fileEntryTypeId = rs.getLong("fileEntryTypeId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");

				_resourceLocalService.addResources(
					companyId, 0, userId,
					"com.liferay.document.library.kernel.model.DLFileEntryType",
					String.valueOf(fileEntryTypeId), false, false, true);
			}
		}
	}

	private final ResourceLocalService _resourceLocalService;

}