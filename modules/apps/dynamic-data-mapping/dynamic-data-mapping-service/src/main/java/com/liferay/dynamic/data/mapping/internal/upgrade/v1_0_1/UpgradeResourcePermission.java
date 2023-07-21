/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_1;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;

/**
 * @author Rafael Praxedes
 */
public class UpgradeResourcePermission extends UpgradeProcess {

	public UpgradeResourcePermission(ResourceActions resourceActions) {
		_resourceActions = resourceActions;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateResourcePermissions(DDMStructure.class.getName());

		updateResourcePermissions(DDMTemplate.class.getName());
	}

	protected String getNewCompositeModelName(String ddmModelClassName) {
		return _resourceActions.getCompositeModelName(
			ddmModelClassName, _CLASS_NAME);
	}

	protected String getOldCompositeModelName(String ddmModelClassName) {
		return _CLASS_NAME + StringPool.DASH + ddmModelClassName;
	}

	protected void updateResourcePermissions(String ddmModelClassName)
		throws Exception {

		String newCompositeModelName = getNewCompositeModelName(
			ddmModelClassName);
		String oldCompositeModelName = getOldCompositeModelName(
			ddmModelClassName);

		try (PreparedStatement ps = connection.prepareStatement(
				"update ResourcePermission set name = ? where name = ?");
			PreparedStatement ps1 = connection.prepareStatement(
				"update ResourcePermission set primKey = ? where primKey = " +
					"?")) {

			ps.setString(1, newCompositeModelName);
			ps.setString(2, oldCompositeModelName);

			ps.executeUpdate();

			ps1.setString(1, newCompositeModelName);
			ps1.setString(2, oldCompositeModelName);

			ps1.executeUpdate();
		}
	}

	private static final String _CLASS_NAME =
		"com.liferay.journal.model.JournalArticle";

	private final ResourceActions _resourceActions;

}