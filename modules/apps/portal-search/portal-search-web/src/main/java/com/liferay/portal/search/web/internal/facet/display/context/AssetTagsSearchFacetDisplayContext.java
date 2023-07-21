/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import java.util.List;

/**
 * @author André de Oliveira
 */
public class AssetTagsSearchFacetDisplayContext {

	public String getFacetLabel() {
		return _facetLabel;
	}

	public String getPaginationStartParameterName() {
		return _paginationStartParameterName;
	}

	public String getParameterName() {
		return _parameterName;
	}

	public String getParameterValue() {
		return _parameterValue;
	}

	public List<String> getParameterValues() {
		return _parameterValues;
	}

	public List<AssetTagsSearchFacetTermDisplayContext>
		getTermDisplayContexts() {

		return _assetTagsSearchFacetTermDisplayContexts;
	}

	public boolean isCloudWithCount() {
		return _cloudWithCount;
	}

	public boolean isNothingSelected() {
		return _nothingSelected;
	}

	public boolean isRenderNothing() {
		return _renderNothing;
	}

	public void setCloudWithCount(boolean cloudWithCount) {
		_cloudWithCount = cloudWithCount;
	}

	public void setFacetLabel(String facetLabel) {
		_facetLabel = facetLabel;
	}

	public void setNothingSelected(boolean nothingSelected) {
		_nothingSelected = nothingSelected;
	}

	public void setPaginationStartParameterName(
		String paginationStartParameterName) {

		_paginationStartParameterName = paginationStartParameterName;
	}

	public void setParameterName(String parameterName) {
		_parameterName = parameterName;
	}

	public void setParameterValue(String parameterValue) {
		_parameterValue = parameterValue;
	}

	public void setParameterValues(List<String> parameterValues) {
		_parameterValues = parameterValues;
	}

	public void setRenderNothing(boolean renderNothing) {
		_renderNothing = renderNothing;
	}

	public void setTermDisplayContexts(
		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts) {

		_assetTagsSearchFacetTermDisplayContexts =
			assetTagsSearchFacetTermDisplayContexts;
	}

	private List<AssetTagsSearchFacetTermDisplayContext>
		_assetTagsSearchFacetTermDisplayContexts;
	private boolean _cloudWithCount;
	private String _facetLabel;
	private boolean _nothingSelected;
	private String _paginationStartParameterName;
	private String _parameterName;
	private String _parameterValue;
	private List<String> _parameterValues;
	private boolean _renderNothing;

}