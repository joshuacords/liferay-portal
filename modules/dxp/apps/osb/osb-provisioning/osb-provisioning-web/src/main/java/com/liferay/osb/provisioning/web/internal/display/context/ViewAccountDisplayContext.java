/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.AccountEntryLocales;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.CountryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.permission.AccountPermissionChecker;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext() {
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest,
			LanguageUtil.get(httpServletRequest, "accounts"),
			accountsPortletURL.toString());

		List<Account> ancestorAccounts = accountReader.getAncestorAccounts(
			account);

		for (int i = ancestorAccounts.size() - 1; i >= 0; i--) {
			Account ancestorAccount = ancestorAccounts.get(i);

			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", ancestorAccount.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				httpServletRequest, ancestorAccount.getName(),
				portletURL.toString());
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, account.getName(), portletURL.toString());
	}

	public Map<String, Object> getAccountDetailsData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL assignParentAccountURL = renderResponse.createRenderURL();

		assignParentAccountURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_account");
		assignParentAccountURL.setParameter("accountKey", account.getKey());
		assignParentAccountURL.setParameter(
			"parentAccountKey", account.getParentAccountKey());
		assignParentAccountURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("assignParentAccountURL", assignParentAccountURL.toString());

		PortletURL assignPartnerTeamURL = renderResponse.createRenderURL();

		assignPartnerTeamURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		assignPartnerTeamURL.setParameter("partner", Boolean.TRUE.toString());
		assignPartnerTeamURL.setParameter(
			"teamKey", accountDisplay.getPartnerTeamKey());
		assignPartnerTeamURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("assignPartnerTeamURL", assignPartnerTeamURL.toString());

		PortletURL assignFirstLineSupportTeamURL =
			renderResponse.createRenderURL();

		assignFirstLineSupportTeamURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		assignFirstLineSupportTeamURL.setParameter(
			"partner", Boolean.TRUE.toString());
		assignFirstLineSupportTeamURL.setParameter(
			"teamKey", accountDisplay.getFirstLineSupportTeamKey());
		assignFirstLineSupportTeamURL.setWindowState(LiferayWindowState.POP_UP);

		data.put(
			"assignFirstLineSupportTeamURL",
			assignFirstLineSupportTeamURL.toString());

		data.put("countryOptions", getCountries());

		List<String> dataRegionNames = new ArrayList<>();

		for (Account.DataRegion dataRegion : Account.DataRegion.values()) {
			dataRegionNames.add(dataRegion.toString());
		}

		data.put("dataRegionNames", dataRegionNames);

		data.put("details", getAccountDisplay());
		data.put("hasManageAccountsPermission", hasManageAccountsPermission());
		data.put(
			"hasUpdateExternalLinksPermission",
			_hasPermission(ProvisioningActionKeys.UPDATE_EXTERNAL_LINKS));

		List<String> liferayVersionNames = new ArrayList<>();

		liferayVersionNames.add(ProductVersion.PORTAL_VERSION_6_2_10);

		for (String version : ProductVersion.DXP_VERSIONS) {
			liferayVersionNames.add("DXP " + version);
		}

		liferayVersionNames.add(ProductVersion.LXC);

		data.put("liferayVersionNames", liferayVersionNames);

		data.put("parentAccountName", getParentAccountName());

		List<String> tierNames = new ArrayList<>();

		for (Account.Tier tier : Account.Tier.values()) {
			tierNames.add(tier.toString());
		}

		data.put("tierNames", tierNames);

		return data;
	}

	public AccountDisplay getAccountDisplay() {
		return accountDisplay;
	}

	public AccountEntry getAccountEntry() throws Exception {
		return accountEntryWebService.fetchAccountEntry(account.getKey());
	}

	public PortletURL getAccountURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL;
	}

	public String getAssignProductsURL() throws Exception {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_products");
		portletURL.setParameter("redirect", getCurrentURL());
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL.toString();
	}

	public long getAuditEntryDisplaysCount() throws Exception {
		return auditEntryWebService.getAccountAuditEntriesCount(
			account.getKey());
	}

	public SearchContainer getAuditEntryDisplaysSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"there-is-no-account-history-yet");

		searchContainer.setDelta(100);

		searchContainer.setResults(
			TransformUtil.transform(
				auditEntryWebService.getAccountAuditEntries(
					account.getKey(), searchContainer.getCur(),
					searchContainer.getEnd() - searchContainer.getStart()),
				auditEntry -> new AuditEntryDisplay(
					httpServletRequest, auditEntry)));

		int count = (int)auditEntryWebService.getAccountAuditEntriesCount(
			account.getKey());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public String getClearResultsURL() {
		PortletURL portletURL = getPortletURL();

		return portletURL.toString();
	}

	public List<Country> getCountries() throws Exception {
		return countryWebService.getCountries();
	}

	public CreationMenu getCreationMenu() throws Exception {
		if (!hasManageAccountsPermission()) {
			return null;
		}

		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/accounts/edit_product_purchase", "redirect",
							getCurrentURL(), "accountKey", account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "add-subscriptions"));
					});
			}
		};
	}

	public String getCurrentURL() {
		return currentURLObj.toString();
	}

	public String getEditTeamURL(String teamKey) {
		PortletURL editTeamURL = renderResponse.createActionURL();

		editTeamURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account");
		editTeamURL.setParameter("teamKey", teamKey);

		return editTeamURL.toString();
	}

	public List<DropdownItem> getFilterCustomerRoleDropdownItems()
		throws Exception {

		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterQueryCustomerRoleDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "filter-by-role"));
					});
			}
		};
	}

	public List<LabelItem> getFilterCustomerRoleLabelItems() {
		return new LabelItemList() {
			{
				String[] contactRoleKeys = ParamUtil.getStringValues(
					renderRequest, "contactRoleKeys");

				for (String contactRoleKey : contactRoleKeys) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] removeContactRoleKeys = ArrayUtil.remove(
								contactRoleKeys, contactRoleKey);

							removeLabelURL.setParameter(
								"contactRoleKeys",
								StringUtil.merge(removeContactRoleKeys));

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);

							ContactRole contactRole =
								contactRoleWebService.getContactRole(
									contactRoleKey);

							String label = String.format(
								"%s: %s",
								LanguageUtil.get(
									httpServletRequest, "contact-role"),
								contactRole.getName());

							labelItem.setLabel(label);
						});
				}
			}
		};
	}

	public String getGenerateLicenseURL() {
		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, ProvisioningPortletKeys.LICENSES,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/add_license_key");
		portletURL.setParameter("redirect", currentURLObj.toString());
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL.toString();
	}

	public List<DropdownItem> getHeaderAddDropdownItems() {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getHeaderAddDropdownItems());
						dropdownGroupItem.setSeparator(true);
					});
				addGroup(
					dropdownGroupItem -> dropdownGroupItem.setDropdownItems(
						_getHeaderAddSubscriptionsDropdownItems()));
			}
		};
	}

	public Map<String, Object> getLatestActiveProductPurchaseDetails()
		throws Exception {

		Map<String, Object> data = new HashMap<>();

		PortletURL extendActiveSubscriptionsURL =
			renderResponse.createActionURL();

		extendActiveSubscriptionsURL.setParameter(
			ActionRequest.ACTION_NAME,
			"/accounts/edit_active_product_purchases_end_date");
		extendActiveSubscriptionsURL.setParameter("redirect", getCurrentURL());
		extendActiveSubscriptionsURL.setParameter(
			"accountKey", account.getKey());

		data.put(
			"extendActiveSubscriptionsURL",
			extendActiveSubscriptionsURL.toString());

		String latestActiveProductPurchaseEndDate =
			getLatestActiveProductPurchaseEndDate();

		if (Validator.isNotNull(latestActiveProductPurchaseEndDate)) {
			data.put(
				"latestActiveSubscriptionEndDate",
				latestActiveProductPurchaseEndDate);
		}

		return data;
	}

	public String getLatestActiveProductPurchaseEndDate() throws Exception {
		Date latestEndDate = null;

		List<ProductPurchaseView> activeProductPurchaseViews =
			_getActiveProductPurchaseViews();

		for (ProductPurchaseView productPurchaseView :
				activeProductPurchaseViews) {

			ProductPurchaseViewDisplay productPurchaseViewDisplay =
				new ProductPurchaseViewDisplay(
					httpServletRequest, account, productPurchaseView);

			Date curLatestEndDate =
				productPurchaseViewDisplay.getLatestEndDate();

			if ((curLatestEndDate != null) &&
				((latestEndDate == null) ||
				 curLatestEndDate.after(latestEndDate))) {

				latestEndDate = curLatestEndDate;
			}
		}

		if (latestEndDate != null) {
			return shortDateFormat.format(latestEndDate);
		}

		return null;
	}

	public Map<String, Object> getPanelData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL addNoteURL = renderResponse.createActionURL();

		addNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		addNoteURL.setParameter("accountKey", account.getKey());

		data.put("addNoteURL", addNoteURL.toString());

		data.put(
			"externalLinks",
			TransformUtil.transform(
				externalLinkWebService.getExternalLinks(
					account.getKey(), 1, 1000),
				externalLink -> new ExternalLinkDisplay(
					httpServletRequest, externalLink)));
		data.put(
			"hasUpdateNotesPermission",
			_hasPermission(ProvisioningActionKeys.UPDATE_NOTES));
		data.put(
			"hasUpdateSalesInfoPermission",
			_hasPermission(ProvisioningActionKeys.UPDATE_SALES_INFO));
		data.put(
			"notes",
			TransformUtil.transform(
				noteWebService.getNotes(
					account.getKey(), StringPool.BLANK, 0, StringPool.BLANK, 1,
					1000),
				note -> new NoteDisplay(
					renderRequest, renderResponse, note,
					userLocalService.fetchUserByUuidAndCompanyId(
						note.getCreatorUID(), themeDisplay.getCompanyId()))));

		return data;
	}

	public String getParentAccountName() throws Exception {
		String parentAccountKey = accountDisplay.getParentAccountKey();

		if (Validator.isNotNull(parentAccountKey)) {
			Account parentAccount = accountWebService.getAccount(
				parentAccountKey);

			if (parentAccount != null) {
				return parentAccount.getName();
			}
		}

		return StringPool.DASH;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL;
	}

	public String getPrimaryContactEmailAddress() throws Exception {
		ContactRole contactRole = contactRoleWebService.getContactRole(
			ContactRole.Type.ACCOUNT_WORKER.toString(),
			ContactRoleConstants.NAME_PRIMARY_CONTACT);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "accountKeysContactRoleKeys",
			account.getKey() + "_" + contactRole.getKey());

		List<Contact> contacts = contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

		if (!contacts.isEmpty()) {
			Contact primaryContact = contacts.get(0);

			return primaryContact.getEmailAddress();
		}

		return StringPool.DASH;
	}

	public SearchContainer getProductPurchaseViewsSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-subscriptions-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");
		String tabs2 = ParamUtil.getString(renderRequest, "tabs2", "active");

		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			renderRequest, "orderByType", "asc");

		List<ProductPurchaseView> productPurchaseViews =
			productPurchaseViewWebService.search(
				keywords, _getFilterQuery(tabs2), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart(),
				_getSorts(orderByCol, orderByType));

		searchContainer.setResults(
			TransformUtil.transform(
				productPurchaseViews,
				productPurchaseView -> new ProductPurchaseViewDisplay(
					httpServletRequest, account, productPurchaseView)));

		int count = (int)productPurchaseViewWebService.searchCount(
			keywords, _getFilterQuery(tabs2));

		searchContainer.setTotal(count);

		searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(renderResponse));

		return searchContainer;
	}

	public Map<String, Object> getSupportData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("account", getAccountDisplay());

		AccountEntry accountEntry = _fetchAccountEntry();

		if (accountEntry != null) {
			long accountAttachmentId =
				accountEntry.getOEMInstructionsAccountAttachmentId();

			data.put(
				"accountAttachmentURL",
				accountEntryWebService.getAccountAttachmentURL(
					accountAttachmentId));

			data.put(
				"oemInstructionsFileName",
				accountEntry.getOEMInstructionsFileName());

			String updateAccountAttachmentURL =
				accountEntryWebService.getUpdateAccountAttachmentURL();

			updateAccountAttachmentURL = HttpUtil.addParameter(
				updateAccountAttachmentURL, "redirect", getCurrentURL());

			data.put("updateAccountAttachmentURL", updateAccountAttachmentURL);

			PortletURL updateInstructionsURL = renderResponse.createActionURL();

			updateInstructionsURL.setParameter(
				ActionRequest.ACTION_NAME, "/edit_account_entry");
			updateInstructionsURL.setParameter(
				Constants.CMD, ProvisioningActionKeys.UPDATE_INSTRUCTIONS);
			updateInstructionsURL.setParameter("redirect", _getPortletURL());
			updateInstructionsURL.setParameter("accountKey", account.getKey());

			data.put("updateInstructionsURL", updateInstructionsURL.toString());

			PortletURL updateLanguageIdURL = renderResponse.createActionURL();

			updateLanguageIdURL.setParameter(
				ActionRequest.ACTION_NAME, "/edit_account_entry");
			updateLanguageIdURL.setParameter(
				Constants.CMD, ProvisioningActionKeys.UPDATE_LANGUAGE_ID);
			updateLanguageIdURL.setParameter("redirect", _getPortletURL());
			updateLanguageIdURL.setParameter("accountKey", account.getKey());

			data.put("updateLanguageIdURL", updateLanguageIdURL.toString());
		}

		data.put("hasManageAccountsPermission", hasManageAccountsPermission());
		data.put(
			"hasUpdateInstructionsPermission",
			_hasPermission(ProvisioningActionKeys.UPDATE_INSTRUCTIONS));
		data.put("instructions", _getSupportInstructions(accountEntry));
		data.put("language", _getSupportLanguage(accountEntry));
		data.put("languageList", _getLanguageList());

		List<String> regionNames = new ArrayList<>();

		for (Account.Region region : Account.Region.values()) {
			regionNames.add(region.toString());
		}

		data.put("regionNames", regionNames);

		PortletURL updateAccountURL = renderResponse.createActionURL();

		updateAccountURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account");
		updateAccountURL.setParameter("tabs1", "support");
		updateAccountURL.setParameter("redirect", _getPortletURL());
		updateAccountURL.setParameter("accountKey", account.getKey());

		data.put("updateAccountURL", updateAccountURL.toString());

		return data;
	}

	public String getTabsNames() throws Exception {
		List<String> tabsNames = new ArrayList<>();

		long activeProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery("active"));

		tabsNames.add(getTabName("active", activeProductPurchaseViewsCount));

		long futureProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery("future"));

		tabsNames.add(getTabName("future", futureProductPurchaseViewsCount));

		long complimentaryProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery("complimentary"));

		tabsNames.add(
			getTabName(
				"complimentary", complimentaryProductPurchaseViewsCount));

		long expiredProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery("expired"));

		tabsNames.add(getTabName("expired", expiredProductPurchaseViewsCount));

		long cancelledProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery("cancelled"));

		tabsNames.add(
			getTabName("cancelled", cancelledProductPurchaseViewsCount));

		long allProductPurchaseViewsCount =
			productPurchaseViewWebService.searchCount(
				StringPool.BLANK, _getFilterQuery(StringPool.BLANK));

		tabsNames.add(getTabName("all", allProductPurchaseViewsCount));

		return StringUtil.merge(tabsNames);
	}

	public boolean hasAssignContactsPermission() throws Exception {
		return _hasPermission(ProvisioningActionKeys.ASSIGN_CONTACTS);
	}

	public boolean hasManageAccountsPermission() throws Exception {
		return _hasPermission(ProvisioningActionKeys.MANAGE_ACCOUNTS);
	}

	public boolean hasManageLicenseKeysPermission() throws Exception {
		return licenseKeyPermission.contains(
			themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_LICENSE_KEYS);
	}

	public void init(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			AccountEntryWebService accountEntryWebService,
			AccountWebService accountWebService,
			AuditEntryWebService auditEntryWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService,
			CountryWebService countryWebService,
			ExternalLinkWebService externalLinkWebService,
			LicenseKeyLocalService licenseKeyLocalService,
			LicenseKeyPermission licenseKeyPermission,
			NoteWebService noteWebService,
			ProductConsumptionWebService productConsumptionWebService,
			ProductPurchaseViewWebService productPurchaseViewWebService,
			ProductWebService productWebService,
			TeamRoleWebService teamRoleWebService,
			TeamWebService teamWebService, UserLocalService userLocalService)
		throws Exception {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.httpServletRequest = httpServletRequest;
		this.accountReader = accountReader;
		this.accountEntryWebService = accountEntryWebService;
		this.accountWebService = accountWebService;
		this.auditEntryWebService = auditEntryWebService;
		this.contactRoleWebService = contactRoleWebService;
		this.contactWebService = contactWebService;
		this.countryWebService = countryWebService;
		this.externalLinkWebService = externalLinkWebService;
		this.licenseKeyLocalService = licenseKeyLocalService;
		this.licenseKeyPermission = licenseKeyPermission;
		this.noteWebService = noteWebService;
		this.productConsumptionWebService = productConsumptionWebService;
		this.productPurchaseViewWebService = productPurchaseViewWebService;
		this.productWebService = productWebService;
		this.teamRoleWebService = teamRoleWebService;
		this.teamWebService = teamWebService;
		this.userLocalService = userLocalService;

		doInit();
	}

	protected void doInit() throws Exception {
		account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		accountDisplay = new AccountDisplay(
			renderRequest, renderResponse, accountReader, account);

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);

		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		setWindowTitle();
	}

	protected String getTabName(String label, long count) {
		StringBundler sb = new StringBundler(4);

		sb.append(LanguageUtil.get(httpServletRequest, label));
		sb.append(" <span class=\"badge badge-secondary\">");
		sb.append(count);
		sb.append("</span>");

		return sb.toString();
	}

	protected void setWindowTitle() {
		String tabs1 = ParamUtil.getString(
			renderRequest, "tabs1", "subscriptions");

		renderResponse.setTitle(
			StringBundler.concat(
				account.getCode(), StringPool.SPACE,
				LanguageUtil.get(httpServletRequest, tabs1)));
	}

	protected Account account;
	protected AccountDisplay accountDisplay;
	protected AccountEntryWebService accountEntryWebService;
	protected AccountReader accountReader;
	protected AccountWebService accountWebService;
	protected AuditEntryWebService auditEntryWebService;
	protected ContactRoleWebService contactRoleWebService;
	protected ContactWebService contactWebService;
	protected CountryWebService countryWebService;
	protected PortletURL currentURLObj;
	protected final Format dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	protected ExternalLinkWebService externalLinkWebService;
	protected HttpServletRequest httpServletRequest;
	protected LicenseKeyLocalService licenseKeyLocalService;
	protected LicenseKeyPermission licenseKeyPermission;
	protected NoteWebService noteWebService;
	protected ProductConsumptionWebService productConsumptionWebService;
	protected ProductPurchaseViewWebService productPurchaseViewWebService;
	protected ProductWebService productWebService;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected final Format shortDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("MMM dd, yyyy");
	protected TeamRoleWebService teamRoleWebService;
	protected TeamWebService teamWebService;
	protected ThemeDisplay themeDisplay;
	protected UserLocalService userLocalService;

	private AccountEntry _fetchAccountEntry() throws Exception {
		return accountEntryWebService.fetchAccountEntry(account.getKey());
	}

	private List<ProductPurchaseView> _getActiveProductPurchaseViews()
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", account.getKey());
		filterQuery.addEquals(
			true, "state", ProductPurchaseConstants.STATE_ACTIVE);

		return productPurchaseViewWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);
	}

	private FilterQuery _getFilterQuery(String tabs2) throws Exception {
		String[] productKeys = ParamUtil.getStringValues(
			renderRequest, "productKeys");
		String[] states = ParamUtil.getStringValues(renderRequest, "states");
		int startDateMonth = ParamUtil.getInteger(
			renderRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(renderRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			renderRequest, "startDateYear");
		int endDateMonth = ParamUtil.getInteger(renderRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(renderRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(renderRequest, "endDateYear");

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, null);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, null);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", account.getKey());

		if (productKeys.length > 0) {
			filterQuery.addEquals(true, "productKey", productKeys);
		}

		if (tabs2.equals("active")) {
			filterQuery.addEquals(
				true, "state", ProductPurchaseConstants.STATE_ACTIVE);
		}
		else if (tabs2.equals("cancelled")) {
			filterQuery.addEquals(
				true, "state", ProductPurchaseConstants.STATE_CANCELLED);
		}
		else if (tabs2.equals("complimentary")) {
			filterQuery.addEquals(
				true, "status",
				String.valueOf(WorkflowConstants.STATUS_INACTIVE));
		}
		else if (tabs2.equals("expired")) {
			filterQuery.addEquals(
				true, "state", ProductPurchaseConstants.STATE_EXPIRED);
		}
		else if (tabs2.equals("future")) {
			filterQuery.addEquals(
				true, "state", ProductPurchaseConstants.STATE_UNACTIVATED);
		}

		if (!tabs2.equals("active") && (states.length > 0)) {
			filterQuery.addEquals(true, "state", states);
		}

		if ((startDate != null) && (endDate != null)) {
			filterQuery.addLessThanEquals(true, "supportLifeEndDate", endDate);
			filterQuery.addGreaterThanEquals(
				true, "supportLifeStartDate", startDate);
		}

		return filterQuery;
	}

	private List<DropdownItem> _getFilterQueryCustomerRoleDropdownItems()
		throws Exception {

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		return new DropdownItemList() {
			{
				FilterQuery filterQuery = new FilterQuery();

				filterQuery.addEquals(
					true, "type", ContactRole.Type.ACCOUNT_CUSTOMER.toString());

				List<ContactRole> contactRoles = contactRoleWebService.search(
					filterQuery, 1, 1000, "name");

				for (ContactRole contactRole : contactRoles) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								ArrayUtil.contains(
									contactRoleKeys, contactRole.getKey()));

							PortletURL portletURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] newContactRoleKeys = ArrayUtil.append(
								contactRoleKeys, contactRole.getKey());

							dropdownItem.setHref(
								portletURL, "contactRoleKeys",
								StringUtil.merge(newContactRoleKeys));

							dropdownItem.setLabel(contactRole.getName());
						});
				}
			}
		};
	}

	private List<DropdownItem> _getHeaderAddDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/assign_contacts",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "contacts"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/accounts/assign_liferay_workers", "redirect",
							getCurrentURL(), "accountKey", account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "liferay-workers"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/edit_team",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "team"));
					});
			}
		};
	}

	private List<DropdownItem> _getHeaderAddSubscriptionsDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref("/");
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "subscriptions"));
					});
			}
		};
	}

	private List<JSONObject> _getLanguageList() {
		List<JSONObject> languageList = new ArrayList<>();

		for (Locale locale : AccountEntryLocales.VALUES) {
			languageList.add(
				JSONUtil.put(
					"id", locale.toString()
				).put(
					"name", locale.getDisplayLanguage()
				));
		}

		return languageList;
	}

	private String _getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("tabs1", "support");
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL.toString();
	}

	private String _getSorts(String orderByCol, String orderByType) {
		StringBundler sb = new StringBundler(4);

		sb.append("property_type:desc,");

		if (Validator.isNotNull(orderByCol)) {
			sb.append(orderByCol);
			sb.append(StringPool.COLON);
			sb.append(orderByType);
		}
		else {
			sb.append("name:asc");
		}

		return sb.toString();
	}

	private String _getSupportInstructions(AccountEntry accountEntry) {
		if ((accountEntry != null) &&
			Validator.isNotNull(accountEntry.getInstructions())) {

			return accountEntry.getInstructions();
		}

		return StringPool.DASH;
	}

	private JSONObject _getSupportLanguage(AccountEntry accountEntry) {
		if ((accountEntry != null) &&
			Validator.isNotNull(accountEntry.getLanguageId())) {

			Locale languageLocale = LocaleUtil.fromLanguageId(
				accountEntry.getLanguageId());

			return JSONUtil.put(
				"id", accountEntry.getLanguageId()
			).put(
				"name", languageLocale.getDisplayLanguage()
			);
		}

		return JSONUtil.put(
			"id", StringPool.DASH
		).put(
			"name", StringPool.DASH
		);
	}

	private boolean _hasPermission(String actionId) throws Exception {
		return AccountPermissionChecker.contains(
			themeDisplay.getPermissionChecker(), actionId);
	}

}