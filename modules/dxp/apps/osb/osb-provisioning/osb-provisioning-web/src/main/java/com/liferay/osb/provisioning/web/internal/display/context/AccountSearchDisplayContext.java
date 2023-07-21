/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.CountryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.permission.AccountPermissionChecker;
import com.liferay.osb.provisioning.web.internal.search.AccountSearch;
import com.liferay.osb.provisioning.web.internal.search.AccountSearchTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class AccountSearchDisplayContext {

	public AccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest, AccountReader accountReader,
		AccountWebService accountWebService,
		ContactIdentityProvider contactIdentityProvider,
		CountryWebService countryWebService,
		ProductWebService productWebService,
		TeamRoleWebService teamRoleWebService,
		UserLocalService userLocalService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_accountWebService = accountWebService;
		_contactIdentityProvider = contactIdentityProvider;
		_countryWebService = countryWebService;
		_productWebService = productWebService;
		_teamRoleWebService = teamRoleWebService;
		_userLocalService = userLocalService;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = _currentURLObj;

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	public Map<String, Object> getData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL accountsHomeURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		data.put("accountsHomeURL", accountsHomeURL.toString());

		data.put(
			"activeSLANames", ListUtil.fromArray(EntitlementConstants.SLAS));

		List<Country> countries = _countryWebService.getCountries();

		Stream<Country> stream = countries.stream();

		data.put(
			"countryNames",
			stream.map(
				country -> country.getName()
			).collect(
				Collectors.toList()
			));

		List<String> regionNames = new ArrayList<>();

		for (Account.Region region : Account.Region.values()) {
			regionNames.add(region.toString());
		}

		data.put("regionNames", regionNames);

		ResourceURL autocompleteAccountURL =
			_renderResponse.createResourceURL();

		autocompleteAccountURL.setResourceID("/accounts/autocomplete");

		data.put("resourceURL", autocompleteAccountURL.toString());

		PortletURL selectAccountURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectAccountURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_account");
		selectAccountURL.setParameter("parent", Boolean.TRUE.toString());
		selectAccountURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectAccountURL", selectAccountURL.toString());

		PortletURL selectPartnerURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectPartnerURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		selectPartnerURL.setParameter("partner", Boolean.TRUE.toString());
		selectPartnerURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectPartnerURL", selectPartnerURL.toString());

		PortletURL selectFirstLineSupportURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectFirstLineSupportURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		selectFirstLineSupportURL.setParameter(
			"partner", Boolean.TRUE.toString());
		selectFirstLineSupportURL.setWindowState(LiferayWindowState.POP_UP);

		data.put(
			"selectFirstLineSupportURL", selectFirstLineSupportURL.toString());

		List<String> productPurchaseStates = ListUtil.fromArray(
			ProductPurchaseConstants.STATES);

		productPurchaseStates.add(ProductPurchaseConstants.STATE_NOT_AVAILABLE);

		data.put("subscriptionStateNames", productPurchaseStates);

		List<String> tierNames = new ArrayList<>();

		for (Account.Tier tier : Account.Tier.values()) {
			tierNames.add(tier.toString());
		}

		data.put("tierNames", tierNames);

		return data;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_accountSearch != null) {
			return _accountSearch;
		}

		_accountSearch = new AccountSearch(_renderRequest, _currentURLObj);

		AccountSearchTerms searchTerms =
			(AccountSearchTerms)_accountSearch.getSearchTerms();

		FilterQuery filterQuery = null;

		String[] subscriptionProductKeys = _getSubscriptionProductKeys();

		if (searchTerms.isAdvancedSearch()) {
			String createdByUuid = _getCreatedByUuid(
				searchTerms.getCreatedByEmailAddress());

			TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
				TeamRole.Type.ACCOUNT.toString(),
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);
			TeamRole partnerTeamRole = _teamRoleWebService.getTeamRole(
				TeamRole.Type.ACCOUNT.toString(),
				TeamRoleConstants.NAME_PARTNER);

			filterQuery = searchTerms.getAdvancedSearchFilter(
				subscriptionProductKeys, createdByUuid, flsTeamRole.getKey(),
				partnerTeamRole.getKey());
		}
		else {
			filterQuery = searchTerms.getBasicSearchFilter(
				subscriptionProductKeys);
		}

		String sort = StringPool.BLANK;

		if (!searchTerms.hasSearchTerms()) {
			sort = "name";
		}

		List<Account> accounts = _accountWebService.search(
			searchTerms.getKeywords(), filterQuery, _accountSearch.getCur(),
			_accountSearch.getEnd() - _accountSearch.getStart(), sort);

		_accountSearch.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					_renderRequest, _renderResponse, _accountReader, account)));

		int count = (int)_accountWebService.searchCount(
			searchTerms.getKeywords(), filterQuery);

		_accountSearch.setTotal(count);

		return _accountSearch;
	}

	public boolean hasManageAccountsPermission() throws Exception {
		return AccountPermissionChecker.contains(
			_themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_ACCOUNTS);
	}

	private String _getCreatedByUuid(String createdByEmailAddress)
		throws Exception {

		if (Validator.isNull(createdByEmailAddress)) {
			return StringPool.BLANK;
		}

		User user = _userLocalService.fetchUserByEmailAddress(
			_themeDisplay.getCompanyId(), createdByEmailAddress);

		if (user != null) {
			return user.getUuid();
		}

		Contact contact = _contactIdentityProvider.fetchContactByEmailAddress(
			createdByEmailAddress, false);

		if (contact != null) {
			return contact.getUuid();
		}

		return "not-available";
	}

	private String[] _getSubscriptionProductKeys() throws Exception {
		if (_subscriptionProductKeys != null) {
			return _subscriptionProductKeys;
		}

		Set<String> subscriptionProductKeys = new HashSet<>();

		for (String name : ProductConstants.NAMES_PARTNERSHIP) {
			Product product = _productWebService.fetchProductByName(name);

			if (product != null) {
				subscriptionProductKeys.add(product.getKey());
			}
		}

		for (String name : ProductConstants.NAMES_SUBSCRIPTION) {
			Product product = _productWebService.fetchProductByName(name);

			if (product != null) {
				subscriptionProductKeys.add(product.getKey());
			}
		}

		_subscriptionProductKeys = ArrayUtil.toStringArray(
			subscriptionProductKeys);

		return _subscriptionProductKeys;
	}

	private final AccountReader _accountReader;
	private AccountSearch _accountSearch;
	private final AccountWebService _accountWebService;
	private final ContactIdentityProvider _contactIdentityProvider;
	private final CountryWebService _countryWebService;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private String[] _subscriptionProductKeys;
	private final TeamRoleWebService _teamRoleWebService;
	private final ThemeDisplay _themeDisplay;
	private final UserLocalService _userLocalService;

}