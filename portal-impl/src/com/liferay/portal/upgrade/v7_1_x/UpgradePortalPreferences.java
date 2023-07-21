/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Iterator;

/**
 * @author Christopher Kian
 */
public class UpgradePortalPreferences extends UpgradeProcess {

	protected String convertDefaultReminderQueries(
			String localizedPreference, String preferences)
		throws Exception {

		Document document = SAXReaderUtil.read(preferences);

		Element rootElement = document.getRootElement();

		Iterator<Element> iterator = rootElement.elementIterator();

		while (iterator.hasNext()) {
			Element preferenceElement = iterator.next();

			String preferenceName = preferenceElement.elementText("name");

			if (preferenceName.equals("reminderQueries")) {
				Element defaultReminderQueryElement =
					preferenceElement.createCopy();

				Element nameElement = defaultReminderQueryElement.element(
					"name");

				defaultReminderQueryElement.remove(nameElement);

				nameElement.setText(localizedPreference);

				defaultReminderQueryElement.add(nameElement);

				rootElement.add(defaultReminderQueryElement);

				break;
			}
		}

		return XMLUtil.formatXML(document);
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeOrganizationReminderQueries();
	}

	protected void upgradeOrganizationReminderQueries() throws Exception {
		StringBundler sb1 = new StringBundler(7);

		sb1.append("select PortalPreferences.portalPreferencesId, ");
		sb1.append("PortalPreferences.preferences, Organization_.companyId ");
		sb1.append("from PortalPreferences inner join Organization_ on ");
		sb1.append("PortalPreferences.ownerId = Organization_.organizationId ");
		sb1.append("where PortalPreferences.ownerType = ");
		sb1.append(PortletKeys.PREFS_OWNER_TYPE_ORGANIZATION);
		sb1.append(" and preferences like '%reminderQueries%'");

		try (PreparedStatement ps1 = connection.prepareStatement(
				sb1.toString());
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update PortalPreferences set preferences = ? where " +
						"portalPreferencesId = ?")) {

			while (rs.next()) {
				long companyId = rs.getLong("companyId");

				String preferences = rs.getString("preferences");

				String defaultLanguageId =
					UpgradeProcessUtil.getDefaultLanguageId(companyId);

				String localizedPreference =
					"reminderQueries_" + defaultLanguageId;

				if (preferences.contains(localizedPreference)) {
					continue;
				}

				ps2.setString(
					1,
					convertDefaultReminderQueries(
						localizedPreference, preferences));

				long portalPreferencesId = rs.getLong("portalPreferencesId");

				ps2.setLong(2, portalPreferencesId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

}