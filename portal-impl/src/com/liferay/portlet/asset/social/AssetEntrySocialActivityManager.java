/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.social;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.social.BaseSocialActivityManager;
import com.liferay.social.kernel.service.SocialActivityLocalService;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.asset.web.internal.social.AssetEntrySocialActivityManager}
 */
@Deprecated
public class AssetEntrySocialActivityManager
	extends BaseSocialActivityManager<AssetEntry> {

	@Override
	protected String getClassName(AssetEntry assetEntry) {
		return assetEntry.getClassName();
	}

	@Override
	protected long getPrimaryKey(AssetEntry assetEntry) {
		return assetEntry.getClassPK();
	}

	@Override
	protected SocialActivityLocalService getSocialActivityLocalService() {
		return socialActivityLocalService;
	}

	@BeanReference(type = SocialActivityLocalService.class)
	protected SocialActivityLocalService socialActivityLocalService;

}