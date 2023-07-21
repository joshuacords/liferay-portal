/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.web.internal.upgrade.v1_0_0;

import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Marcellus Tavares
 */
public class UpgradeDDLFormPortletId extends BaseUpgradePortletId {

	public UpgradeDDLFormPortletId(
		PortletPreferencesLocalService portletPreferencesLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_portletPreferencesLocalService = portletPreferencesLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	protected void deleteResourcePermissions(
			final String oldRootPortletId, final String newRootPortletId)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			_resourcePermissionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property nameProperty = PropertyFactoryUtil.forName("name");

				dynamicQuery.add(nameProperty.eq(new String(oldRootPortletId)));
			});
		actionableDynamicQuery.setPerformActionMethod(
			(ResourcePermission resourcePermission) -> {
				long total = getResourcePermissionsCount(
					resourcePermission.getCompanyId(), newRootPortletId,
					resourcePermission.getScope(),
					resourcePermission.getRoleId());

				if (total > 0) {
					_resourcePermissionLocalService.deleteResourcePermission(
						resourcePermission);
				}
			});

		actionableDynamicQuery.performActions();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"1_WAR_ddlformportlet", DDLPortletKeys.DYNAMIC_DATA_LISTS_DISPLAY}
		};
	}

	protected long getResourcePermissionsCount(
			final long companyId, final String name, final int scope,
			final long roleId)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			_resourcePermissionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property companyIdProperty = PropertyFactoryUtil.forName(
					"companyId");

				dynamicQuery.add(companyIdProperty.eq(companyId));

				Property nameProperty = PropertyFactoryUtil.forName("name");

				dynamicQuery.add(nameProperty.eq(name));

				Property scopeProperty = PropertyFactoryUtil.forName("scope");

				dynamicQuery.add(scopeProperty.eq(scope));

				Property roleIdProperty = PropertyFactoryUtil.forName("roleId");

				dynamicQuery.add(roleIdProperty.eq(roleId));
			});

		return actionableDynamicQuery.performCount();
	}

	@Override
	protected void updateInstanceablePortletPreferences(
			final String oldRootPortletId, final String newRootPortletId)
		throws Exception {

		ActionableDynamicQuery actionableDynamicQuery =
			_portletPreferencesLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Junction junction = RestrictionsFactoryUtil.disjunction();

				Property property = PropertyFactoryUtil.forName("portletId");

				junction.add(property.eq(oldRootPortletId));
				junction.add(property.like(oldRootPortletId + "_INSTANCE_%"));
				junction.add(
					property.like(oldRootPortletId + "_USER_%_INSTANCE_%"));

				dynamicQuery.add(junction);
			});
		actionableDynamicQuery.setPerformActionMethod(
			(PortletPreferences portletPreference) -> updatePortletPreferences(
				portletPreference, oldRootPortletId, newRootPortletId));

		actionableDynamicQuery.performActions();
	}

	@Override
	protected void updatePortlet(
			String oldRootPortletId, String newRootPortletId)
		throws Exception {

		try {
			updateResourcePermission(oldRootPortletId, newRootPortletId, true);

			updateInstanceablePortletPreferences(
				oldRootPortletId, newRootPortletId);

			updateLayouts(oldRootPortletId, newRootPortletId, false);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}
	}

	protected void updatePortletPreferences(
		PortletPreferences portletPreferences, String oldRootPortletId,
		String newRootPortletId) {

		String newPortletId = StringUtil.replace(
			portletPreferences.getPortletId(), oldRootPortletId,
			newRootPortletId);

		portletPreferences.setPortletId(newPortletId);

		String newPreferences = StringUtil.replace(
			portletPreferences.getPreferences(), "</portlet-preferences>",
			"<preference><name>formView</name><value>true</value>" +
				"</preference></portlet-preferences>");

		newPreferences = StringUtil.replace(
			newPreferences,
			new String[] {
				"#p_p_id_" + oldRootPortletId, "#portlet_" + oldRootPortletId
			},
			new String[] {
				"#p_p_id_" + newRootPortletId, "#portlet_" + newRootPortletId
			});

		portletPreferences.setPreferences(newPreferences);

		_portletPreferencesLocalService.updatePortletPreferences(
			portletPreferences);
	}

	@Override
	protected void updateResourcePermission(
			String oldRootPortletId, String newRootPortletId,
			boolean updateName)
		throws Exception {

		deleteResourcePermissions(oldRootPortletId, newRootPortletId);

		super.updateResourcePermission(
			oldRootPortletId, newRootPortletId, updateName);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeDDLFormPortletId.class);

	private final PortletPreferencesLocalService
		_portletPreferencesLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;

}