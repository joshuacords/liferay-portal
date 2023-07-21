/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.results.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.search.web.internal.util.PortletPreferencesHelper;
import com.liferay.portal.util.PropsUtil;

import java.util.Optional;

import javax.portlet.PortletPreferences;

/**
 * @author Lino Alves
 */
public class SearchResultsPortletPreferencesImpl
	implements SearchResultsPortletPreferences {

	public SearchResultsPortletPreferencesImpl(
		Optional<PortletPreferences> portletPreferencesOptional) {

		_portletPreferencesHelper = new PortletPreferencesHelper(
			portletPreferencesOptional);
	}

	@Override
	public Optional<String> getFederatedSearchKeyOptional() {
		return _portletPreferencesHelper.getString(
			SearchResultsPortletPreferences.
				PREFERENCE_KEY_FEDERATED_SEARCH_KEY);
	}

	@Override
	public String getFederatedSearchKeyString() {
		return getFederatedSearchKeyOptional().orElse(StringPool.BLANK);
	}

	@Override
	public Optional<String> getFieldsToDisplayOptional() {
		return _portletPreferencesHelper.getString(
			SearchResultsPortletPreferences.PREFERENCE_KEY_FIELDS_TO_DISPLAY);
	}

	@Override
	public String getFieldsToDisplayString() {
		return getFieldsToDisplayOptional().orElse(StringPool.BLANK);
	}

	@Override
	public int getPaginationDelta() {
		return _portletPreferencesHelper.getInteger(
			SearchResultsPortletPreferences.PREFERENCE_KEY_PAGINATION_DELTA,
			GetterUtil.getInteger(
				PropsUtil.get(PropsKeys.SEARCH_CONTAINER_PAGE_DEFAULT_DELTA),
				20));
	}

	@Override
	public String getPaginationDeltaParameterName() {
		return _portletPreferencesHelper.getString(
			SearchResultsPortletPreferences.
				PREFERENCE_KEY_PAGINATION_DELTA_PARAMETER_NAME,
			"delta");
	}

	@Override
	public String getPaginationStartParameterName() {
		return _portletPreferencesHelper.getString(
			SearchResultsPortletPreferences.
				PREFERENCE_KEY_PAGINATION_START_PARAMETER_NAME,
			"start");
	}

	@Override
	public boolean isDisplayInDocumentForm() {
		return _portletPreferencesHelper.getBoolean(
			SearchResultsPortletPreferences.
				PREFERENCE_KEY_DISPLAY_IN_DOCUMENT_FORM,
			false);
	}

	@Override
	public boolean isHighlightEnabled() {
		return _portletPreferencesHelper.getBoolean(
			SearchResultsPortletPreferences.PREFERENCE_KEY_HIGHLIGHT_ENABLED,
			true);
	}

	@Override
	public boolean isViewInContext() {
		return _portletPreferencesHelper.getBoolean(
			SearchResultsPortletPreferences.PREFERENCE_KEY_VIEW_IN_CONTEXT,
			true);
	}

	private final PortletPreferencesHelper _portletPreferencesHelper;

}