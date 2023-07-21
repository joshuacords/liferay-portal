/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.web.internal.display.context;

import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.exportimport.util.comparator.ExportImportConfigurationNameComparator;
import com.liferay.exportimport.web.internal.search.ExportImportConfigurationDisplayTerms;
import com.liferay.exportimport.web.internal.search.ExportImportConfigurationSearchTerms;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Péter Alius
 * @author Péter Borkuti
 */
public class ExportTemplatesToolbarDisplayContext
	extends BaseManagementToolbarDisplayContext {

	public ExportTemplatesToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest, long liveGroupId,
		Company company, PortletURL iteratorURL) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest);

		searchContainer = _createSearchContainer(
			liveGroupId, company, iteratorURL);
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getRenderURL();

		clearResultsURL.setParameter(
			"mvcPath", "/export/export_templates/view.jsp");

		return clearResultsURL.toString();
	}

	@Override
	public CreationMenu getCreationMenu() {
		return new CreationMenu() {
			{
				GroupDisplayContextHelper groupDisplayContextHelper =
					new GroupDisplayContextHelper(request);

				addPrimaryDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							getRenderURL(), "mvcRenderCommandName",
							"editExportConfiguration", Constants.CMD,
							Constants.ADD, "groupId",
							groupDisplayContextHelper.getGroupId(),
							"liveGroupId",
							groupDisplayContextHelper.getLiveGroupId(),
							"privateLayout", Boolean.FALSE.toString());
						dropdownItem.setLabel(LanguageUtil.get(request, "new"));
					});
			}
		};
	}

	@Override
	public int getItemsTotal() {
		return searchContainer.getTotal();
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getRenderURL();

		searchActionURL.setParameter(
			"mvcRenderCommandName", "viewExportConfigurations");

		return searchActionURL.toString();
	}

	public SearchContainer getSearchContainer() {
		return searchContainer;
	}

	protected PortletURL getRenderURL() {
		return liferayPortletResponse.createRenderURL();
	}

	protected SearchContainer searchContainer;

	private SearchContainer _createSearchContainer(
		long liveGroupId, Company company, PortletURL iteratorURL) {

		ExportImportConfigurationSearchTerms
			exportImportConfigurationSearchTerms =
				new ExportImportConfigurationSearchTerms(liferayPortletRequest);

		SearchContainer searchContainer = new SearchContainer(
			liferayPortletRequest,
			new ExportImportConfigurationDisplayTerms(liferayPortletRequest),
			exportImportConfigurationSearchTerms,
			SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA,
			iteratorURL, null, "there-are-no-saved-export-templates");

		searchContainer.setOrderByCol("name");
		searchContainer.setOrderByComparator(
			new ExportImportConfigurationNameComparator(
				Objects.equals(getOrderByType(), "asc")));
		searchContainer.setOrderByType(getOrderByType());

		int exportImportConfigurationType =
			ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT;

		List<ExportImportConfiguration> results =
			ExportImportConfigurationLocalServiceUtil.
				getExportImportConfigurations(
					company.getCompanyId(), liveGroupId,
					exportImportConfigurationSearchTerms.getKeywords(),
					exportImportConfigurationType, searchContainer.getStart(),
					searchContainer.getEnd(),
					searchContainer.getOrderByComparator());
		int total =
			ExportImportConfigurationLocalServiceUtil.
				getExportImportConfigurationsCount(
					company.getCompanyId(), liveGroupId,
					exportImportConfigurationSearchTerms.getKeywords(),
					exportImportConfigurationType);

		searchContainer.setResults(results);
		searchContainer.setTotal(total);

		return searchContainer;
	}

}