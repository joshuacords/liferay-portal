/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.data.set;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.json.JSONArray;

import java.util.Locale;

/**
 * @author Marco Leo
 */
@ProviderType
public interface ClayDataSetFilterSerializer {

	public JSONArray serialize(String dataSetFilterKey, Locale locale);

}