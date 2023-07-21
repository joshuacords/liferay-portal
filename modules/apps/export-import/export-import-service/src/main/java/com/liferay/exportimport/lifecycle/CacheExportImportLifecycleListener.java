/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.lifecycle;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lifecycle.BaseExportImportLifecycleListener;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.servlet.filters.cache.CacheUtil;

/**
 * @author     Máté Thurzó
 * @deprecated As of Judson (7.1.x)
 */
@Deprecated
public class CacheExportImportLifecycleListener
	extends BaseExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	protected void clearCache() {
		CacheUtil.clearCache();
		PermissionCacheUtil.clearCache();
	}

	@Override
	protected void onLayoutImportProcessFinished(
		PortletDataContext portletDataContext) {

		clearCache();
	}

	@Override
	protected void onPortletImportProcessFinished(
		PortletDataContext portletDataContext) {

		clearCache();
	}

}