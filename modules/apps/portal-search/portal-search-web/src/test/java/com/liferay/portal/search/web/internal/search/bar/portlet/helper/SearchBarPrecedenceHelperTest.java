/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.bar.portlet.helper;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.web.constants.SearchBarPortletKeys;
import com.liferay.portal.search.web.internal.portlet.preferences.PortletPreferencesLookup;
import com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletPreferences;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import jakarta.portlet.PortletPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 * @author André de Oliveira
 */
public class SearchBarPrecedenceHelperTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		Layout layout = _createLayout(_portlets);

		_layout = layout;

		_searchBarPrecedenceHelper = _createSearchBarPrecedenceHelper();
		_themeDisplay = _createThemeDisplay(layout);
	}

	@Test
	public void testContentPageWithMasterLayoutHeaderSearchBar() {
		_setThemeDisplayLayoutFriendlyURL(_DESTINATION);

		String federatedSearchKey = RandomTestUtil.randomString();

		_addSearchBarPortletToMasterLayout(federatedSearchKey);

		Portlet portlet = _addSearchBarPortletToPage(federatedSearchKey);

		Assert.assertTrue(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testDifferentDestinationDifferentFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(RandomTestUtil.randomString());

		_addSearchBarPortletToHeader(RandomTestUtil.randomString());

		Portlet portlet = _addSearchBarPortletToPage(
			RandomTestUtil.randomString());

		Assert.assertFalse(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testDifferentDestinationSameFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(RandomTestUtil.randomString());

		String federatedSearchKey = RandomTestUtil.randomString();

		_addSearchBarPortletToHeader(federatedSearchKey);

		Portlet portlet = _addSearchBarPortletToPage(federatedSearchKey);

		Assert.assertFalse(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testIsDisplayWarningIgnoredConfiguration() {
		_addPortlet("", "", true, SearchBarPortletKeys.SEARCH_BAR + "_test");

		_createPermissionChecker();

		_setThemeDisplayLayoutFriendlyURL("");

		Assert.assertFalse(isDisplayWarningIgnoredConfiguration());
	}

	@Test
	public void testOverlappingDestinationDifferentFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(
			_DESTINATION + RandomTestUtil.randomString());

		_addSearchBarPortletToHeader(RandomTestUtil.randomString());

		Portlet portlet = _addSearchBarPortletToPage(
			RandomTestUtil.randomString());

		Assert.assertFalse(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testOverlappingDestinationSameFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(
			_DESTINATION + RandomTestUtil.randomString());

		String federatedSearchKey = RandomTestUtil.randomString();

		_addSearchBarPortletToHeader(federatedSearchKey);

		Portlet portlet = _addSearchBarPortletToPage(federatedSearchKey);

		Assert.assertFalse(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testSameDestinationDifferentFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(_DESTINATION);

		_addSearchBarPortletToHeader(RandomTestUtil.randomString());

		Portlet portlet = _addSearchBarPortletToPage(
			RandomTestUtil.randomString());

		Assert.assertFalse(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	@Test
	public void testSameDestinationSameFederatedKey() {
		_setThemeDisplayLayoutFriendlyURL(_DESTINATION);

		String federatedSearchKey = RandomTestUtil.randomString();

		_addSearchBarPortletToHeader(federatedSearchKey);

		Portlet portlet = _addSearchBarPortletToPage(federatedSearchKey);

		Assert.assertTrue(
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(portlet));
	}

	protected boolean isDisplayWarningIgnoredConfiguration() {
		return _searchBarPrecedenceHelper.isDisplayWarningIgnoredConfiguration(
			_themeDisplay, true);
	}

	protected boolean isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
		Portlet portlet) {

		return _searchBarPrecedenceHelper.
			isSearchBarInBodyWithHeaderSearchBarAlreadyPresent(
				_themeDisplay, portlet.getPortletId());
	}

	private Portlet _addPortlet(
		String destination, String federatedSearchKey, boolean isStatic,
		String portletId) {

		Portlet portlet = _createPortlet(isStatic, portletId);

		_portlets.add(portlet);

		Mockito.doReturn(
			_createPortletPreferences(federatedSearchKey, destination)
		).when(
			_portletPreferencesLookup
		).fetchPreferences(
			portlet, _themeDisplay
		);

		Mockito.when(
			_portletLocalService.getPortletById(0, portlet.getPortletId())
		).thenReturn(
			portlet
		);

		return portlet;
	}

	private void _addSearchBarPortletToHeader(String federatedSearchKey) {
		_addPortlet(
			_DESTINATION, federatedSearchKey, true, "headerSearchBarPortletId");
	}

	private void _addSearchBarPortletToMasterLayout(String federatedSearchKey) {
		long masterLayoutPlid = RandomTestUtil.randomLong();

		Mockito.when(
			_layout.getMasterLayoutPlid()
		).thenReturn(
			masterLayoutPlid
		);

		Mockito.when(
			_layout.isTypeContent()
		).thenReturn(
			true
		);

		Layout masterLayout = Mockito.mock(Layout.class);

		Mockito.when(
			masterLayout.getPlid()
		).thenReturn(
			masterLayoutPlid
		);

		Mockito.when(
			_layoutLocalService.fetchLayout(masterLayoutPlid)
		).thenReturn(
			masterLayout
		);

		String masterHeaderSearchBarPortletId =
			"masterHeaderSearchBarPortletId";

		Portlet portlet = _createPortlet(false, masterHeaderSearchBarPortletId);

		Mockito.when(
			_portletLocalService.getPortletById(
				0, masterHeaderSearchBarPortletId)
		).thenReturn(
			portlet
		);

		Mockito.doReturn(
			_createPortletPreferences(federatedSearchKey, _DESTINATION)
		).when(
			_portletPreferencesLookup
		).fetchPreferences(
			portlet, _themeDisplay
		);

		FragmentEntryLink fragmentEntryLink = Mockito.mock(
			FragmentEntryLink.class);

		Mockito.when(
			_fragmentEntryLinkLocalService.getFragmentEntryLinksByPlid(
				0L, masterLayoutPlid)
		).thenReturn(
			Collections.singletonList(fragmentEntryLink)
		);

		Mockito.when(
			_portletRegistry.getFragmentEntryLinkPortletIds(fragmentEntryLink)
		).thenReturn(
			Collections.singletonList(masterHeaderSearchBarPortletId)
		);
	}

	private Portlet _addSearchBarPortletToPage(String federatedSearchKey) {
		return _addPortlet(
			_DESTINATION, federatedSearchKey, false, "searchBarPortletId");
	}

	private Layout _createLayout(List<Portlet> portlets) {
		LayoutTypePortlet layoutTypePortlet = Mockito.mock(
			LayoutTypePortlet.class);

		Mockito.when(
			layoutTypePortlet.getAllPortlets(false)
		).thenReturn(
			portlets
		);

		Layout layout = Mockito.mock(Layout.class);

		Mockito.when(
			layout.getLayoutType()
		).thenReturn(
			layoutTypePortlet
		);

		return layout;
	}

	private void _createPermissionChecker() {
		PermissionChecker permissionChecker = Mockito.mock(
			PermissionChecker.class);

		Mockito.when(
			_themeDisplay.getPermissionChecker()
		).thenReturn(
			permissionChecker
		);

		Mockito.when(
			permissionChecker.hasPermission(
				_themeDisplay.getScopeGroupId(),
				SearchBarPortletKeys.SEARCH_BAR,
				SearchBarPortletKeys.SEARCH_BAR, ActionKeys.CONFIGURATION)
		).thenReturn(
			true
		);
	}

	private Portlet _createPortlet(boolean isStatic, String portletId) {
		Portlet portlet = Mockito.mock(Portlet.class);

		Mockito.when(
			portlet.getPortletId()
		).thenReturn(
			portletId
		);

		Mockito.when(
			portlet.getPortletName()
		).thenReturn(
			SearchBarPortletKeys.SEARCH_BAR
		);

		Mockito.when(
			portlet.isStatic()
		).thenReturn(
			isStatic
		);

		return portlet;
	}

	private PortletDisplay _createPortletDisplay() throws Exception {
		PortletDisplay portletDisplay = Mockito.mock(PortletDisplay.class);

		Mockito.when(
			portletDisplay.getPortletResource()
		).thenReturn(
			SearchBarPortletKeys.SEARCH_BAR + "_test"
		);

		Mockito.when(
			portletDisplay.getId()
		).thenReturn(
			RandomTestUtil.randomString()
		);

		return portletDisplay;
	}

	private PortletPreferences _createPortletPreferences(
		String federatedSearchKey, String destination) {

		PortletPreferences portletPreferences = Mockito.mock(
			PortletPreferences.class);

		Mockito.when(
			portletPreferences.getValue(
				SearchBarPortletPreferences.PREFERENCE_KEY_DESTINATION,
				StringPool.BLANK)
		).thenReturn(
			destination
		);

		Mockito.when(
			portletPreferences.getValue(
				SearchBarPortletPreferences.PREFERENCE_KEY_FEDERATED_SEARCH_KEY,
				StringPool.BLANK)
		).thenReturn(
			federatedSearchKey
		);

		return portletPreferences;
	}

	private SearchBarPrecedenceHelper _createSearchBarPrecedenceHelper() {
		SearchBarPrecedenceHelper searchBarPrecedenceHelper =
			new SearchBarPrecedenceHelper();

		ReflectionTestUtil.setFieldValue(
			searchBarPrecedenceHelper, "_fragmentEntryLinkLocalService",
			_fragmentEntryLinkLocalService);
		ReflectionTestUtil.setFieldValue(
			searchBarPrecedenceHelper, "_layoutLocalService",
			_layoutLocalService);
		ReflectionTestUtil.setFieldValue(
			searchBarPrecedenceHelper, "_portletLocalService",
			_portletLocalService);
		ReflectionTestUtil.setFieldValue(
			searchBarPrecedenceHelper, "_portletPreferencesLookup",
			_portletPreferencesLookup);
		ReflectionTestUtil.setFieldValue(
			searchBarPrecedenceHelper, "_portletRegistry", _portletRegistry);

		return searchBarPrecedenceHelper;
	}

	private ThemeDisplay _createThemeDisplay(Layout layout) throws Exception {
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);

		Mockito.when(
			themeDisplay.getScopeGroup()
		).thenReturn(
			Mockito.mock(Group.class)
		);

		Mockito.doReturn(
			_createPortletDisplay()
		).when(
			themeDisplay
		).getPortletDisplay();

		Mockito.when(
			themeDisplay.getLayout()
		).thenReturn(
			layout
		);

		return themeDisplay;
	}

	private void _setThemeDisplayLayoutFriendlyURL(String destination) {
		Mockito.when(
			_themeDisplay.getLayoutFriendlyURL(_layout)
		).thenReturn(
			"/" + destination
		);
	}

	private static final String _DESTINATION = RandomTestUtil.randomString();

	private final FragmentEntryLinkLocalService _fragmentEntryLinkLocalService =
		Mockito.mock(FragmentEntryLinkLocalService.class);
	private Layout _layout;
	private final LayoutLocalService _layoutLocalService = Mockito.mock(
		LayoutLocalService.class);
	private final PortletLocalService _portletLocalService = Mockito.mock(
		PortletLocalService.class);
	private final PortletPreferencesLookup _portletPreferencesLookup =
		Mockito.mock(PortletPreferencesLookup.class);
	private final PortletRegistry _portletRegistry = Mockito.mock(
		PortletRegistry.class);
	private final List<Portlet> _portlets = new ArrayList<>();
	private SearchBarPrecedenceHelper _searchBarPrecedenceHelper;
	private ThemeDisplay _themeDisplay;

}