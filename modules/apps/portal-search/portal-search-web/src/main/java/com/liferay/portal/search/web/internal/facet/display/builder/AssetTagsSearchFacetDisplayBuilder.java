/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.builder;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Lino Alves
 */
public class AssetTagsSearchFacetDisplayBuilder {

	public AssetTagsSearchFacetDisplayContext build() {
		AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext =
			new AssetTagsSearchFacetDisplayContext();

		assetTagsSearchFacetDisplayContext.setCloudWithCount(
			isCloudWithCount());
		assetTagsSearchFacetDisplayContext.setFacetLabel(getFacetLabel());
		assetTagsSearchFacetDisplayContext.setNothingSelected(
			isNothingSelected());
		assetTagsSearchFacetDisplayContext.setPaginationStartParameterName(
			_paginationStartParameterName);
		assetTagsSearchFacetDisplayContext.setParameterName(_parameterName);
		assetTagsSearchFacetDisplayContext.setParameterValue(
			getFirstParameterValue());
		assetTagsSearchFacetDisplayContext.setParameterValues(_selectedTags);
		assetTagsSearchFacetDisplayContext.setRenderNothing(isRenderNothing());
		assetTagsSearchFacetDisplayContext.setTermDisplayContexts(
			buildTermDisplayContexts());

		return assetTagsSearchFacetDisplayContext;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
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

	public void setParameterName(String parameterName) {
		_parameterName = parameterName;
	}

	public void setParameterValue(String parameterValue) {
		parameterValue = StringUtil.trim(
			Objects.requireNonNull(parameterValue));

		if (parameterValue.isEmpty()) {
			return;
		}

		_selectedTags = Collections.singletonList(parameterValue);
	}

	public void setParameterValues(String... parameterValues) {
		_selectedTags = Arrays.asList(parameterValues);
	}

	protected AssetTagsSearchFacetTermDisplayContext buildTermDisplayContext(
		TermCollector termCollector, int maxCount, int minCount,
		double multiplier) {

		int frequency = termCollector.getFrequency();

		int popularity = (int)getPopularity(
			frequency, minCount, maxCount, multiplier);

		String value = termCollector.getTerm();

		AssetTagsSearchFacetTermDisplayContext
			assetTagsSearchFacetTermDisplayContext =
				new AssetTagsSearchFacetTermDisplayContext();

		assetTagsSearchFacetTermDisplayContext.setFrequency(frequency);
		assetTagsSearchFacetTermDisplayContext.setFrequencyVisible(
			_frequenciesVisible);
		assetTagsSearchFacetTermDisplayContext.setPopularity(popularity);
		assetTagsSearchFacetTermDisplayContext.setSelected(isSelected(value));
		assetTagsSearchFacetTermDisplayContext.setValue(value);

		return assetTagsSearchFacetTermDisplayContext;
	}

	protected List<AssetTagsSearchFacetTermDisplayContext>
		buildTermDisplayContexts() {

		List<TermCollector> termCollectors = getTermsCollectors();

		if (termCollectors.isEmpty()) {
			return getEmptySearchResultTermDisplayContexts();
		}

		List<AssetTagsSearchFacetTermDisplayContext>
			assetTagsSearchFacetTermDisplayContexts = new ArrayList<>(
				termCollectors.size());

		int maxCount = 1;
		int minCount = 1;

		if (isCloudWithCount()) {

			// The cloud style may not list tags in the order of frequency,
			// so keep looking through the results until we reach the maximum
			// number of terms or we run out of terms

			for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
				if ((_maxTerms > 0) && (j >= _maxTerms)) {
					break;
				}

				TermCollector termCollector = termCollectors.get(i);

				int frequency = termCollector.getFrequency();

				if ((_frequencyThreshold > 0) &&
					(_frequencyThreshold > frequency)) {

					j--;

					continue;
				}

				maxCount = Math.max(maxCount, frequency);
				minCount = Math.min(minCount, frequency);
			}
		}

		double multiplier = 1;

		if (maxCount != minCount) {
			multiplier = (double)5 / (maxCount - minCount);
		}

		for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
			if ((_maxTerms > 0) && (j >= _maxTerms)) {
				break;
			}

			TermCollector termCollector = termCollectors.get(i);

			int frequency = termCollector.getFrequency();

			if ((_frequencyThreshold > 0) &&
				(_frequencyThreshold > frequency)) {

				j--;

				continue;
			}

			AssetTagsSearchFacetTermDisplayContext
				assetTagsSearchFacetTermDisplayContext =
					buildTermDisplayContext(
						termCollector, maxCount, minCount, multiplier);

			if (assetTagsSearchFacetTermDisplayContext != null) {
				assetTagsSearchFacetTermDisplayContexts.add(
					assetTagsSearchFacetTermDisplayContext);
			}
		}

		return assetTagsSearchFacetTermDisplayContexts;
	}

	protected List<AssetTagsSearchFacetTermDisplayContext>
		getEmptySearchResultTermDisplayContexts() {

		if (_selectedTags.isEmpty()) {
			return Collections.emptyList();
		}

		AssetTagsSearchFacetTermDisplayContext
			assetTagsSearchFacetTermDisplayContext =
				new AssetTagsSearchFacetTermDisplayContext();

		assetTagsSearchFacetTermDisplayContext.setFrequency(0);
		assetTagsSearchFacetTermDisplayContext.setFrequencyVisible(
			_frequenciesVisible);
		assetTagsSearchFacetTermDisplayContext.setPopularity(0);
		assetTagsSearchFacetTermDisplayContext.setSelected(true);
		assetTagsSearchFacetTermDisplayContext.setValue(_selectedTags.get(0));

		return Collections.singletonList(
			assetTagsSearchFacetTermDisplayContext);
	}

	protected String getFacetLabel() {
		FacetConfiguration facetConfiguration = _facet.getFacetConfiguration();

		if (facetConfiguration != null) {
			return facetConfiguration.getLabel();
		}

		return StringPool.BLANK;
	}

	protected String getFirstParameterValue() {
		if (_selectedTags.isEmpty()) {
			return StringPool.BLANK;
		}

		return _selectedTags.get(0);
	}

	protected double getPopularity(
		int frequency, int minCount, int maxCount, double multiplier) {

		double popularity = maxCount - (maxCount - (frequency - minCount));

		return 1 + (popularity * multiplier);
	}

	protected List<TermCollector> getTermsCollectors() {
		FacetCollector facetCollector = _facet.getFacetCollector();

		return facetCollector.getTermCollectors();
	}

	protected boolean isCloudWithCount() {
		if (_frequenciesVisible && _displayStyle.equals("cloud")) {
			return true;
		}

		return false;
	}

	protected boolean isNothingSelected() {
		if (_selectedTags.isEmpty()) {
			return true;
		}

		return false;
	}

	protected boolean isRenderNothing() {
		List<TermCollector> termCollectors = getTermsCollectors();

		if (isNothingSelected() && termCollectors.isEmpty()) {
			return true;
		}

		return false;
	}

	protected boolean isSelected(String value) {
		if (_selectedTags.contains(value)) {
			return true;
		}

		return false;
	}

	private String _displayStyle;
	private Facet _facet;
	private boolean _frequenciesVisible;
	private int _frequencyThreshold;
	private int _maxTerms;
	private String _paginationStartParameterName;
	private String _parameterName;
	private List<String> _selectedTags = Collections.emptyList();

}