/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.bar.portlet.helper;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.web.constants.SearchBarPortletKeys;
import com.liferay.portal.search.web.internal.portlet.preferences.PortletPreferencesLookup;
import com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletDestinationUtil;
import com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletPreferences;
import com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletPreferencesImpl;

import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(service = SearchBarPrecedenceHelper.class)
public class SearchBarPrecedenceHelper {

	public Portlet findHeaderSearchBarPortlet(ThemeDisplay themeDisplay) {
		Portlet headerSearchBarPortlet =
			_findMasterLayoutHeaderSearchBarPortlet(themeDisplay);

		if (headerSearchBarPortlet != null) {
			return headerSearchBarPortlet;
		}

		List<Portlet> portlets = _getPortlets(themeDisplay);

		for (Portlet portlet : portlets) {
			if (_isHeaderSearchBar(portlet)) {
				headerSearchBarPortlet = portlet;

				break;
			}
		}

		return headerSearchBarPortlet;
	}

	public boolean isDisplayWarningIgnoredConfiguration(
		ThemeDisplay themeDisplay, boolean usePortletResource) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String id = portletDisplay.getId();

		if (usePortletResource) {
			id = portletDisplay.getPortletResource();
		}

		if (id.endsWith("_INSTANCE_templateSearch")) {
			return false;
		}

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		boolean hasEditConfigurationPermission =
			permissionChecker.hasPermission(
				themeDisplay.getScopeGroupId(), SearchBarPortletKeys.SEARCH_BAR,
				SearchBarPortletKeys.SEARCH_BAR, ActionKeys.CONFIGURATION);

		if (hasEditConfigurationPermission &&
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
				themeDisplay, id)) {

			return true;
		}

		return false;
	}

	public boolean isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
		ThemeDisplay themeDisplay, String portletId) {

		Portlet headerSearchBarPortlet = findHeaderSearchBarPortlet(
			themeDisplay);

		if ((headerSearchBarPortlet == null) ||
			_isSamePortlet(headerSearchBarPortlet, portletId)) {

			return false;
		}

		SearchBarPortletPreferences searchBarPortletPreferences1 =
			_getSearchBarPortletPreferences(
				headerSearchBarPortlet, themeDisplay);

		if (!SearchBarPortletDestinationUtil.isSameDestination(
				searchBarPortletPreferences1, themeDisplay)) {

			return false;
		}

		SearchBarPortletPreferences searchBarPortletPreferences2 =
			_getSearchBarPortletPreferences(portletId, themeDisplay);

		return Objects.equals(
			searchBarPortletPreferences1.getFederatedSearchKey(),
			searchBarPortletPreferences2.getFederatedSearchKey());
	}

	private Portlet _findMasterLayoutHeaderSearchBarPortlet(
		ThemeDisplay themeDisplay) {

		Layout layout = themeDisplay.getLayout();

		if ((!layout.isTypeAssetDisplay() && !layout.isTypeContent()) ||
			(layout.getMasterLayoutPlid() <= 0)) {

			return null;
		}

		Layout masterLayout = _layoutLocalService.fetchLayout(
			layout.getMasterLayoutPlid());

		if (masterLayout == null) {
			return null;
		}

		for (FragmentEntryLink fragmentEntryLink :
				_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
					masterLayout.getGroupId(), masterLayout.getPlid())) {

			for (String portletId :
					_portletRegistry.getFragmentEntryLinkPortletIds(
						fragmentEntryLink)) {

				Portlet portlet = _portletLocalService.getPortletById(
					themeDisplay.getCompanyId(), portletId);

				if ((portlet != null) &&
					Objects.equals(
						portlet.getPortletName(),
						SearchBarPortletKeys.SEARCH_BAR)) {

					return portlet;
				}
			}
		}

		return null;
	}

	private List<Portlet> _getPortlets(ThemeDisplay themeDisplay) {
		Layout layout = themeDisplay.getLayout();

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		return layoutTypePortlet.getAllPortlets(false);
	}

	private SearchBarPortletPreferences _getSearchBarPortletPreferences(
		Portlet portlet, ThemeDisplay themeDisplay) {

		if (portlet == null) {
			return new SearchBarPortletPreferencesImpl(null);
		}

		return new SearchBarPortletPreferencesImpl(
			_portletPreferencesLookup.fetchPreferences(portlet, themeDisplay));
	}

	private SearchBarPortletPreferences _getSearchBarPortletPreferences(
		String portletId, ThemeDisplay themeDisplay) {

		return _getSearchBarPortletPreferences(
			_portletLocalService.getPortletById(
				themeDisplay.getCompanyId(), portletId),
			themeDisplay);
	}

	private boolean _isHeaderSearchBar(Portlet portlet) {
		if (portlet.isStatic() &&
			Objects.equals(
				portlet.getPortletName(), SearchBarPortletKeys.SEARCH_BAR)) {

			return true;
		}

		return false;
	}

	private boolean _isSamePortlet(Portlet portlet, String portletId) {
		return Objects.equals(portlet.getPortletId(), portletId);
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private PortletPreferencesLookup _portletPreferencesLookup;

	@Reference
	private PortletRegistry _portletRegistry;

}