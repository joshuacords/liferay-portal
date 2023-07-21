/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.builder;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetTermDisplayContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author André de Oliveira
 */
public class UserSearchFacetDisplayBuilder {

	public UserSearchFacetDisplayContext build() {
		boolean nothingSelected = isNothingSelected();

		List<TermCollector> termCollectors = getTermsCollectors();

		boolean renderNothing = false;

		if (nothingSelected && termCollectors.isEmpty()) {
			renderNothing = true;
		}

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			new UserSearchFacetDisplayContext();

		userSearchFacetDisplayContext.setNothingSelected(nothingSelected);
		userSearchFacetDisplayContext.setPaginationStartParameterName(
			_paginationStartParameterName);
		userSearchFacetDisplayContext.setParamName(_paramName);
		userSearchFacetDisplayContext.setParamValue(getFirstParamValue());
		userSearchFacetDisplayContext.setParamValues(_paramValues);
		userSearchFacetDisplayContext.setRenderNothing(renderNothing);
		userSearchFacetDisplayContext.setTermDisplayContexts(
			buildTermDisplayContexts(termCollectors));

		return userSearchFacetDisplayContext;
	}

	public void setFacet(Facet facet) {
		_facet = facet;
	}

	public void setFrequenciesVisible(boolean frequenciesVisible) {
		_frequenciesVisible = frequenciesVisible;
	}

	public void setFrequencyThreshold(int frequencyThreshold) {
		_frequencyThreshold = frequencyThreshold;
	}

	public void setMaxTerms(int maxTerms) {
		_maxTerms = maxTerms;
	}

	public void setPaginationStartParameterName(
		String paginationStartParameterName) {

		_paginationStartParameterName = paginationStartParameterName;
	}

	public void setParamName(String paramName) {
		_paramName = paramName;
	}

	public void setParamValue(String paramValue) {
		paramValue = StringUtil.trim(Objects.requireNonNull(paramValue));

		if (paramValue.isEmpty()) {
			return;
		}

		_paramValues = Collections.singletonList(paramValue);
	}

	public void setParamValues(List<String> paramValues) {
		_paramValues = paramValues;
	}

	protected UserSearchFacetTermDisplayContext buildTermDisplayContext(
		TermCollector termCollector) {

		String term = GetterUtil.getString(termCollector.getTerm());

		UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext =
			new UserSearchFacetTermDisplayContext();

		userSearchFacetTermDisplayContext.setFrequency(
			termCollector.getFrequency());
		userSearchFacetTermDisplayContext.setFrequencyVisible(
			_frequenciesVisible);
		userSearchFacetTermDisplayContext.setSelected(isSelected(term));
		userSearchFacetTermDisplayContext.setUserName(term);

		return userSearchFacetTermDisplayContext;
	}

	protected List<UserSearchFacetTermDisplayContext> buildTermDisplayContexts(
		List<TermCollector> termCollectors) {

		if (termCollectors.isEmpty()) {
			return getEmptyTermDisplayContexts();
		}

		List<UserSearchFacetTermDisplayContext>
			userSearchFacetTermDisplayContexts = new ArrayList<>(
				termCollectors.size());

		for (int i = 0; i < termCollectors.size(); i++) {
			TermCollector termCollector = termCollectors.get(i);

			if (((_maxTerms > 0) && (i >= _maxTerms)) ||
				((_frequencyThreshold > 0) &&
				 (_frequencyThreshold > termCollector.getFrequency()))) {

				break;
			}

			userSearchFacetTermDisplayContexts.add(
				buildTermDisplayContext(termCollector));
		}

		return userSearchFacetTermDisplayContexts;
	}

	protected List<UserSearchFacetTermDisplayContext>
		getEmptyTermDisplayContexts() {

		if (_paramValues.isEmpty()) {
			return Collections.emptyList();
		}

		UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext =
			new UserSearchFacetTermDisplayContext();

		userSearchFacetTermDisplayContext.setFrequency(0);
		userSearchFacetTermDisplayContext.setFrequencyVisible(
			_frequenciesVisible);
		userSearchFacetTermDisplayContext.setSelected(true);
		userSearchFacetTermDisplayContext.setUserName(_paramValues.get(0));

		return Collections.singletonList(userSearchFacetTermDisplayContext);
	}

	protected String getFirstParamValue() {
		if (_paramValues.isEmpty()) {
			return StringPool.BLANK;
		}

		return _paramValues.get(0);
	}

	protected List<TermCollector> getTermsCollectors() {
		FacetCollector facetCollector = _facet.getFacetCollector();

		if (facetCollector != null) {
			return facetCollector.getTermCollectors();
		}

		return Collections.<TermCollector>emptyList();
	}

	protected boolean isNothingSelected() {
		if (_paramValues.isEmpty()) {
			return true;
		}

		return false;
	}

	protected boolean isSelected(String value) {
		if (_paramValues.contains(value)) {
			return true;
		}

		return false;
	}

	private Facet _facet;
	private boolean _frequenciesVisible;
	private int _frequencyThreshold;
	private int _maxTerms;
	private String _paginationStartParameterName;
	private String _paramName;
	private List<String> _paramValues = Collections.emptyList();

}