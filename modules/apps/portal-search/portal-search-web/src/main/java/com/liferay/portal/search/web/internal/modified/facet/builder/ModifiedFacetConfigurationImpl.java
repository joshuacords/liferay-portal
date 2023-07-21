/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.modified.facet.builder;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

/**
 * @author Lino Alves
 */
public class ModifiedFacetConfigurationImpl
	implements ModifiedFacetConfiguration {

	public ModifiedFacetConfigurationImpl(
		FacetConfiguration facetConfiguration) {

		_jsonObject = facetConfiguration.getData();
	}

	@Override
	public JSONArray getRangesJSONArray() {
		return _jsonObject.getJSONArray("ranges");
	}

	@Override
	public void setRangesJSONArray(JSONArray jsonArray) {
		_jsonObject.put("ranges", jsonArray);
	}

	private final JSONObject _jsonObject;

}