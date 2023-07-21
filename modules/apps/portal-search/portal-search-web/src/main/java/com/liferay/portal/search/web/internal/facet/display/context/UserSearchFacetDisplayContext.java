/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import java.io.Serializable;

import java.util.List;

/**
 * @author Lino Alves
 */
public class UserSearchFacetDisplayContext implements Serializable {

	public String getPaginationStartParameterName() {
		return _paginationStartParameterName;
	}

	public String getParamName() {
		return _paramName;
	}

	public String getParamValue() {
		return _paramValue;
	}

	public List<String> getParamValues() {
		return _paramValues;
	}

	public List<UserSearchFacetTermDisplayContext> getTermDisplayContexts() {
		return _userSearchFacetTermDisplayContexts;
	}

	public boolean isNothingSelected() {
		return _nothingSelected;
	}

	public boolean isRenderNothing() {
		return _renderNothing;
	}

	public void setNothingSelected(boolean nothingSelected) {
		_nothingSelected = nothingSelected;
	}

	public void setPaginationStartParameterName(
		String paginationStartParameterName) {

		_paginationStartParameterName = paginationStartParameterName;
	}

	public void setParamName(String paramName) {
		_paramName = paramName;
	}

	public void setParamValue(String paramValue) {
		_paramValue = paramValue;
	}

	public void setParamValues(List<String> paramValues) {
		_paramValues = paramValues;
	}

	public void setRenderNothing(boolean renderNothing) {
		_renderNothing = renderNothing;
	}

	public void setTermDisplayContexts(
		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts) {

		_userSearchFacetTermDisplayContexts =
			userSearchFacetTermDisplayContexts;
	}

	private boolean _nothingSelected;
	private String _paginationStartParameterName;
	private String _paramName;
	private String _paramValue;
	private List<String> _paramValues;
	private boolean _renderNothing;
	private List<UserSearchFacetTermDisplayContext>
		_userSearchFacetTermDisplayContexts;

}