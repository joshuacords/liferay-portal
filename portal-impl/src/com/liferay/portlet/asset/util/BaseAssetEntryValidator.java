/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.util;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class BaseAssetEntryValidator implements AssetEntryValidator {

	/**
	 * @throws PortalException
	 */
	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] entryNames)
		throws PortalException {
	}

	protected boolean isAssetCategorizable(long classNameId) {
		return true;
	}

	/**
	 * @throws PortalException
	 */
	protected void validate(
			long classNameId, long classTypePK, final long[] categoryIds,
			AssetVocabulary vocabulary)
		throws PortalException {
	}

}