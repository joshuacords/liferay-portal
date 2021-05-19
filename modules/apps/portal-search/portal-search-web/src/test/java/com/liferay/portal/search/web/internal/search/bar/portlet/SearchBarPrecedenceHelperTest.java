/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.web.internal.search.bar.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.web.constants.SearchBarPortletKeys;
import com.liferay.portal.search.web.internal.portlet.preferences.PortletPreferencesLookup;
import com.liferay.portal.search.web.internal.search.bar.portlet.configuration.SearchBarPortletInstanceConfiguration;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Joshua Cords
 */
public class SearchBarPrecedenceHelperTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		setUpPortletList();

		setUpLayout();
		setUpLayoutType();
		setUpThemeDisplay();
	}

	@Test
	public void testSearchBarAlreadyPresentWithDifferentFederatedKey()
		throws PortletException {

		SearchBarPrecedenceHelper searchBarPrecedenceHelper =
			new SearchBarPrecedenceHelper();

		searchBarPrecedenceHelper.setPortletLocalService(_portletLocalService);

		searchBarPrecedenceHelper.setPortletPreferencesLookup(
			_portletPreferencesLookup);

		setUpPortlet(
			SearchBarPortletKeys.SEARCH_BAR, "headerSearchBarPortletId",
			"headerFedKey", true);

		Portlet searchBarPortlet = setUpPortlet(
			"searchBar", "searchBarPortletId", "FedKey", false);
		setUpPortletPreferencesLookup();

		Assert.assertFalse(
			searchBarPrecedenceHelper.
				isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
					_themeDisplay, searchBarPortlet.getPortletId()));
	}

	@Test
	public void testSearchBarAlreadyPresentWithSameFederatedKey()
		throws PortletException {

		SearchBarPrecedenceHelper searchBarPrecedenceHelper =
			new SearchBarPrecedenceHelper();

		searchBarPrecedenceHelper.setPortletLocalService(_portletLocalService);

		searchBarPrecedenceHelper.setPortletPreferencesLookup(
			_portletPreferencesLookup);

		setUpPortlet(
			SearchBarPortletKeys.SEARCH_BAR, "headerSearchBarPortletId",
			"FedKey", true);

		Portlet searchBarPortlet = setUpPortlet(
			"searchBar", "searchBarPortletId", "FedKey", false);
		setUpPortletPreferencesLookup();

		Assert.assertTrue(
			searchBarPrecedenceHelper.
				isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
					_themeDisplay, searchBarPortlet.getPortletId()));
	}

	protected void setUpLayout() {
		Mockito.when(
			_layout.getLayoutType()
		).thenReturn(
			_layoutTypePortlet
		);
	}

	protected void setUpLayoutType() {
		Mockito.when(
			_layoutTypePortlet.getAllPortlets(false)
		).thenReturn(
			_portlets
		);
	}

	protected Portlet setUpPortlet(
		String portletName, String portletId, String federatedSearchKey,
		boolean isStatic) {

		Portlet portlet = Mockito.mock(Portlet.class);

		Mockito.when(
			portlet.isStatic()
		).thenReturn(
			isStatic
		);

		Mockito.when(
			portlet.getPortletName()
		).thenReturn(
			portletName
		);

		Mockito.when(
			portlet.getPortletId()
		).thenReturn(
			portletId
		);

		PortletPreferences portletPreferences = Mockito.mock(
			PortletPreferences.class);

		Mockito.when(
			portletPreferences.getValue(
				SearchBarPortletPreferences.PREFERENCE_KEY_FEDERATED_SEARCH_KEY,
				StringPool.BLANK)
		).thenReturn(
			federatedSearchKey
		);

		Mockito.when(
			_portletLocalService.getPortletById(0, portlet.getPortletId())
		).thenReturn(
			portlet
		);

		_portletPreferencesMap.put(portlet, portletPreferences);

		_portlets.add(portlet);

		return portlet;

		//_portletPreferencesMap.keySet()  keySet -> List<Portlet>
	}

	protected void setUpPortletList() {
		_portlets = new ArrayList<>();
		_portletPreferencesMap = new HashMap<>();
	}

	protected void setUpPortletPreferencesLookup() {
		for (Map.Entry<Portlet, PortletPreferences> entry :
				_portletPreferencesMap.entrySet()) {

			Mockito.when(
				_portletPreferencesLookup.fetchPreferences(
					entry.getKey(), _themeDisplay)
			).thenReturn(
				Optional.ofNullable(entry.getValue())
			);
		}
	}

	protected void setUpThemeDisplay() {
		Mockito.when(
			_themeDisplay.getScopeGroup()
		).thenReturn(
			_group
		);

		try {
			Mockito.when(
				_portletDisplay.getPortletInstanceConfiguration(Mockito.any())
			).thenReturn(
				Mockito.mock(SearchBarPortletInstanceConfiguration.class)
			);
		}
		catch (Exception exception) {
		}

		Mockito.when(
			_themeDisplay.getPortletDisplay()
		).thenReturn(
			_portletDisplay
		);

		Mockito.when(
			_themeDisplay.getLayout()
		).thenReturn(
			_layout
		);
	}

	@Mock
	private Group _group;

	@Mock
	private PortletPreferences _headerSearchBarPortletPreferences;

	@Mock
	private Layout _layout;

	@Mock
	private LayoutTypePortlet _layoutTypePortlet;

	@Mock
	private PortletDisplay _portletDisplay;

	@Mock
	private PortletLocalService _portletLocalService;

	@Mock
	private PortletPreferencesLookup _portletPreferencesLookup;

	private Map<Portlet, PortletPreferences> _portletPreferencesMap;
	private List<Portlet> _portlets;

	@Mock
	private PortletPreferences _searchBarPortletPreferences;

	@Mock
	private ThemeDisplay _themeDisplay;

}