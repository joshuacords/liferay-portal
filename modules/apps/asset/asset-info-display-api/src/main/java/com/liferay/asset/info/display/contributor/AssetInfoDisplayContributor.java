/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor;

import com.liferay.asset.info.display.field.util.ClassTypesInfoDisplayFieldProviderUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.info.display.contributor.InfoDisplayContributor;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;

/**
 * @author     Jürgen Kappler
 * @deprecated As of Mueller (7.2.x), in favour of {@link
 *             InfoDisplayContributor}
 */
@Deprecated
public interface AssetInfoDisplayContributor
	extends InfoDisplayContributor<AssetEntry> {

	@Override
	public default List<InfoDisplayField> getClassTypeInfoDisplayFields(
			long classTypeId, Locale locale)
		throws PortalException {

		return ClassTypesInfoDisplayFieldProviderUtil.
			getClassTypeInfoDisplayFields(getClassName(), classTypeId, locale);
	}

	@Override
	public default List<ClassType> getClassTypes(long groupId, Locale locale)
		throws PortalException {

		return ClassTypesInfoDisplayFieldProviderUtil.getClassTypes(
			groupId, getClassName(), locale);
	}

}