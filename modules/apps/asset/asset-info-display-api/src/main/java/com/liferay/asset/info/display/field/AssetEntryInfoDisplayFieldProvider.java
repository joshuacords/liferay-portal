/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.field;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jürgen Kappler
 */
@ProviderType
public interface AssetEntryInfoDisplayFieldProvider {

	public Map<String, Object> getAssetEntryInfoDisplayFieldsValues(
			String className, long classPK, Locale locale)
		throws PortalException;

}