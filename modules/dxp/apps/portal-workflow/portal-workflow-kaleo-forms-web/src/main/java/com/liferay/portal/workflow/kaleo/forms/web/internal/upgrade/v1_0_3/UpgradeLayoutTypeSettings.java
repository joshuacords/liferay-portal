/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.web.internal.upgrade.v1_0_3;

import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortletConstants;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Inácio Nery
 */
public class UpgradeLayoutTypeSettings extends BaseUpgradePortletId {

	protected void deleteLayoutTypeSettingsColumnKeyWithoutValue()
		throws Exception {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			LayoutLocalServiceUtil.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

				Property typeSettingsProperty = PropertyFactoryUtil.forName(
					"typeSettings");

				disjunction.add(
					typeSettingsProperty.like(
						"%" + LayoutTypePortletConstants.COLUMN_PREFIX +
							"%=,%"));
				disjunction.add(
					typeSettingsProperty.like(
						"%" + LayoutTypePortletConstants.NESTED_COLUMN_IDS +
							"%=,%"));

				dynamicQuery.add(disjunction);
			});
		indexableActionableDynamicQuery.setParallel(true);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(Layout layout) -> {
				try {
					UnicodeProperties oldtypeSettings =
						layout.getTypeSettingsProperties();
					UnicodeProperties newTypeSettings = getNewTypeSettings(
						layout.getTypeSettingsProperties());

					if (!oldtypeSettings.equals(newTypeSettings)) {
						updateLayout(
							layout.getPlid(), newTypeSettings.toString());
					}
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to update layout " + layout.getPlid(),
							exception);
					}
				}
			});

		indexableActionableDynamicQuery.performActions();
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteLayoutTypeSettingsColumnKeyWithoutValue();
	}

	protected UnicodeProperties getNewTypeSettings(
		UnicodeProperties oldtypeSettingsProperties) {

		UnicodeProperties newtypeSettingsProperties =
			(UnicodeProperties)oldtypeSettingsProperties.clone();

		for (String key : oldtypeSettingsProperties.keySet()) {
			if (StringUtil.startsWith(
					key, LayoutTypePortletConstants.COLUMN_PREFIX) ||
				StringUtil.startsWith(
					key, LayoutTypePortletConstants.NESTED_COLUMN_IDS)) {

				String[] portletIds = StringUtil.split(
					oldtypeSettingsProperties.getProperty(key));

				if (ArrayUtil.isEmpty(portletIds) ||
					Validator.isNull(portletIds[0])) {

					newtypeSettingsProperties.remove(key);
				}
			}
		}

		return newtypeSettingsProperties;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeLayoutTypeSettings.class);

}