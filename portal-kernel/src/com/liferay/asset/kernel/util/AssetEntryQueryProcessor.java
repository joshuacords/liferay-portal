/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.util;

import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.model.User;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Jorge Ferrer
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.asset.util.AssetEntryQueryProcessor}
 */
@Deprecated
public interface AssetEntryQueryProcessor {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	public String getKey();

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	public String getTitle(Locale locale);

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException;

	public void processAssetEntryQuery(
			User user, PortletPreferences portletPreferences,
			AssetEntryQuery assetEntryQuery)
		throws Exception;

}