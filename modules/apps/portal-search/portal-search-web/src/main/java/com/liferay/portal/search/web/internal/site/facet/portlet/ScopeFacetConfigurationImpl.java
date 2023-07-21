/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.site.facet.portlet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

/**
 * @author André de Oliveira
 */
public class ScopeFacetConfigurationImpl implements ScopeFacetConfiguration {

	public ScopeFacetConfigurationImpl(FacetConfiguration facetConfiguration) {
		_jsonObject = facetConfiguration.getData();
	}

	@Override
	public int getFrequencyThreshold() {
		return _jsonObject.getInt("frequencyThreshold");
	}

	@Override
	public int getMaxTerms() {
		return _jsonObject.getInt("maxTerms");
	}

	@Override
	public void setFrequencyThreshold(int frequencyThreshold) {
		_jsonObject.put("frequencyThreshold", frequencyThreshold);
	}

	@Override
	public void setMaxTerms(int maxTerms) {
		_jsonObject.put("maxTerms", maxTerms);
	}

	private final JSONObject _jsonObject;

}