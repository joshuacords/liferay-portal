/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.sort.portlet.shared.search;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.sort.Sort;
import com.liferay.portal.search.sort.SortBuilder;
import com.liferay.portal.search.sort.SortBuilderFactory;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.web.internal.sort.constants.SortPortletKeys;
import com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferences;
import com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + SortPortletKeys.SORT,
	service = PortletSharedSearchContributor.class
)
public class SortPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		SortPortletPreferences sortPortletPreferences =
			new SortPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		Stream<Sort> stream = buildSorts(
			portletSharedSearchSettings, sortPortletPreferences);

		searchRequestBuilder.sorts(stream.toArray(Sort[]::new));
	}

	protected Sort buildSort(String fieldValue, Locale locale) {
		SortOrder sortOrder = SortOrder.ASC;

		if (fieldValue.endsWith("+")) {
			fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
		}
		else if (fieldValue.endsWith("-")) {
			fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
			sortOrder = SortOrder.DESC;
		}

		if (fieldValue.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {
			try {
				return ddmIndexer.createDDMStructureFieldSort(
					fieldValue, locale, sortOrder);
			}
			catch (PortalException portalException) {
				throw new RuntimeException(portalException);
			}
		}

		SortBuilder sortBuilder = _sortBuilderFactory.getSortBuilder();

		return sortBuilder.field(
			fieldValue
		).locale(
			locale
		).sortOrder(
			sortOrder
		).build();
	}

	protected Stream<Sort> buildSorts(
		PortletSharedSearchSettings portletSharedSearchSettings,
		SortPortletPreferences sortPortletPreferences) {

		List<String> fieldValues = getFieldValues(
			sortPortletPreferences.getParameterName(),
			portletSharedSearchSettings);

		ThemeDisplay themeDisplay =
			portletSharedSearchSettings.getThemeDisplay();

		Stream<String> stream = fieldValues.stream();

		return stream.filter(
			fieldValue -> !fieldValue.isEmpty()
		).map(
			fieldValue -> buildSort(fieldValue, themeDisplay.getLocale())
		);
	}

	protected List<String> getFieldValues(
		String parameterName,
		PortletSharedSearchSettings portletSharedSearchSettings) {

		String[] fieldValues = portletSharedSearchSettings.getParameterValues(
			parameterName);

		if (ArrayUtil.isNotEmpty(fieldValues)) {
			return Arrays.asList(fieldValues);
		}

		String portletId = portletSharedSearchSettings.getPortletId();
		ThemeDisplay themeDisplay =
			portletSharedSearchSettings.getThemeDisplay();

		try {
			PortletPreferences portletPreferences =
				PortletPreferencesFactoryUtil.getExistingPortletSetup(
					themeDisplay.getLayout(), portletId);

			SortPortletPreferences sortPortletPreferences =
				new SortPortletPreferencesImpl(Optional.of(portletPreferences));

			JSONArray fieldsJSONArray =
				sortPortletPreferences.getFieldsJSONArray();

			JSONObject jsonObject = fieldsJSONArray.getJSONObject(0);

			String fieldValue = jsonObject.getString("field");

			return ListUtil.fromArray(fieldValue);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	@Reference
	protected DDMIndexer ddmIndexer;

	@Reference
	private SortBuilderFactory _sortBuilderFactory;

}