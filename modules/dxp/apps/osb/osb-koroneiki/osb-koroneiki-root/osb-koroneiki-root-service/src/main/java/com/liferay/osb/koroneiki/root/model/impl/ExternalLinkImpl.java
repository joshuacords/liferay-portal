/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model.impl;

import com.liferay.osb.koroneiki.root.constants.RootPortletKeys;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Kyle Bischof
 */
public class ExternalLinkImpl extends ExternalLinkBaseImpl {

	public ExternalLinkImpl() {
	}

	public String getUrl() {
		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				getCompanyId(), getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY,
				PortletKeys.PREFS_PLID_SHARED,
				RootPortletKeys.EXTERNAL_LINKS_ADMIN);

		String url = portletPreferences.getValue(
			getDomain() + StringPool.UNDERLINE + getEntityName(),
			StringPool.BLANK);

		return StringUtil.replace(url, "[$ENTITY_ID$]", getEntityId());
	}

}