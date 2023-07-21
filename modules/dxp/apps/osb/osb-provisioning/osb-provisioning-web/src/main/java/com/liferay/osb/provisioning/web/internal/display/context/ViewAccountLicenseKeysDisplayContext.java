/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class ViewAccountLicenseKeysDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountLicenseKeysDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_tabs2 = ParamUtil.getString(renderRequest, "tabs2", "active");

		_productKey = ParamUtil.getString(renderRequest, "productKey");
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							StringBundler.concat(
								"javascript:", renderResponse.getNamespace(),
								"downloadLicenseKeys();"));
						dropdownItem.setIcon("download");
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "download"));
						dropdownItem.setQuickAction(true);
					});

				if (hasManageLicenseKeysPermission()) {
					add(
						dropdownItem -> {
							dropdownItem.setHref(
								StringBundler.concat(
									"javascript:",
									renderResponse.getNamespace(),
									"extendLicenseKeys();"));
							dropdownItem.setIcon("time");
							dropdownItem.setLabel(
								LanguageUtil.get(httpServletRequest, "extend"));
							dropdownItem.setQuickAction(true);
						});

					add(
						dropdownItem -> {
							dropdownItem.setHref(
								StringBundler.concat(
									"javascript:",
									renderResponse.getNamespace(),
									"replaceLicenseKeys();"));
							dropdownItem.setIcon("change");
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, "replace"));
							dropdownItem.setQuickAction(true);
						});

					add(
						dropdownItem -> {
							dropdownItem.setHref(
								StringBundler.concat(
									"javascript:",
									renderResponse.getNamespace(),
									"updateLicenseKeysProperties('",
									_getConfirmMessage("make-complimentary"),
									"', 'complimentary', true);"));
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest, "make-complimentary"));
						});

					add(
						dropdownItem -> {
							dropdownItem.setHref(
								StringBundler.concat(
									"javascript:",
									renderResponse.getNamespace(),
									"updateLicenseKeysProperties('",
									_getConfirmMessage("remove-complimentary"),
									"', 'complimentary', false);"));
							dropdownItem.setLabel(
								LanguageUtil.get(
									httpServletRequest,
									"remove-complimentary"));
						});

					if (_tabs2.equals("active") || _tabs2.equals("expired")) {
						add(
							dropdownItem -> {
								dropdownItem.setHref(
									StringBundler.concat(
										"javascript:",
										renderResponse.getNamespace(),
										"updateLicenseKeysProperties('",
										_getConfirmMessage("deactivate"),
										"', 'active', false);"));
								dropdownItem.setLabel(
									LanguageUtil.get(
										httpServletRequest, "deactivate"));
							});
					}
					else if (_tabs2.equals("deactivated")) {
						add(
							dropdownItem -> {
								dropdownItem.setHref(
									StringBundler.concat(
										"javascript:",
										renderResponse.getNamespace(),
										"updateLicenseKeysProperties('",
										_getConfirmMessage("activate"),
										"', 'active', true);"));
								dropdownItem.setLabel(
									LanguageUtil.get(
										httpServletRequest, "activate"));
							});
					}
				}
			}
		};
	}

	@Override
	public String getClearResultsURL() {
		PortletURL portletURL = super.getPortletURL();

		if (Validator.isNotNull(_productKey)) {
			portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_subscription");
			portletURL.setParameter(
				"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
			portletURL.setParameter("accountKey", account.getKey());
			portletURL.setParameter("productKey", _productKey);
		}

		if (Validator.isNotNull(_tabs2)) {
			portletURL.setParameter("tabs2", _tabs2);
		}

		return portletURL.toString();
	}

	public CreationMenu getCreationMenu() throws Exception {
		if (!hasManageLicenseKeysPermission()) {
			return null;
		}

		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(getGenerateLicenseURL());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "generate-license"));
					});
			}
		};
	}

	@Override
	public PortletURL getPortletURL() {
		if (Validator.isNotNull(_productKey)) {
			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_subscription");
			portletURL.setParameter(
				"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
			portletURL.setParameter("accountKey", account.getKey());
			portletURL.setParameter("productKey", _productKey);

			return portletURL;
		}

		return super.getPortletURL();
	}

	public Map<String, Object> getReplaceLicenseKeysData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountKey", account.getKey());
		data.put("productKey", _productKey);

		PortletURL portletURL = renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/replace_license_keys");

		data.put("replacementURL", portletURL.toString());

		return data;
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		Date now = new Date();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountKey", account.getKey());

		if (Validator.isNotNull(_productKey)) {
			params.put("productKey", _productKey);
		}

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-licenses-were-found");

		Hits hits = null;

		Sort sort = SortFactoryUtil.getSort(
			LicenseKey.class, Sort.LONG_TYPE, Field.MODIFIED_DATE, "desc");

		if (_tabs2.equals("active")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), keywords, null, null, null, null,
				null, null, keywords, keywords, null, null, null, null,
				keywords, keywords, new String[] {keywords}, keywords, keywords,
				keywords, keywords, keywords, keywords, keywords, now, null,
				true, params, false, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else if (_tabs2.equals("deactivated")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), keywords, null, null, null, null,
				null, null, keywords, keywords, null, null, null, null,
				keywords, keywords, new String[] {keywords}, keywords, keywords,
				keywords, keywords, keywords, keywords, keywords, null, null,
				false, params, false, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else if (_tabs2.equals("expired")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), keywords, null, null, null, null,
				null, null, keywords, keywords, null, null, null, null,
				keywords, keywords, new String[] {keywords}, keywords, keywords,
				keywords, keywords, keywords, keywords, keywords, null, now,
				true, params, false, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), keywords, null, null, null, null,
				null, null, keywords, keywords, null, null, null, null,
				keywords, keywords, new String[] {keywords}, keywords, keywords,
				keywords, keywords, keywords, keywords, keywords, null, null,
				null, params, false, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}

		List<LicenseKey> licenseKeys = new ArrayList<>();

		for (Document document : hits.toList()) {
			long licenseKeyId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			licenseKeys.add(licenseKeyLocalService.getLicenseKey(licenseKeyId));
		}

		searchContainer.setResults(
			TransformUtil.transform(
				licenseKeys,
				licenseKey -> new LicenseKeyDisplay(
					renderRequest, renderResponse, licenseKey)));

		searchContainer.setTotal(hits.getLength());

		searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(renderResponse));

		return searchContainer;
	}

	public String getTabsNames() throws Exception {
		List<String> tabsNames = new ArrayList<>();

		Date now = new Date();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountKey", account.getKey());

		if (Validator.isNotNull(_productKey)) {
			params.put("productKey", _productKey);
		}

		int activeLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, now, null, true, params, true);

		tabsNames.add(getTabName("active", activeLicenseKeysCount));

		int expiredLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, now, true, params, true);

		tabsNames.add(getTabName("expired", expiredLicenseKeysCount));

		int deactivatedLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, false, params,
			true);

		tabsNames.add(getTabName("deactivated", deactivatedLicenseKeysCount));

		int allLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, params, true);

		tabsNames.add(getTabName("all", allLicenseKeysCount));

		return StringUtil.merge(tabsNames);
	}

	private String _getConfirmMessage(String action) {
		if (action.equals("activate")) {
			return LanguageUtil.get(
				httpServletRequest,
				"are-you-sure-you-want-to-activate-the-license-keys");
		}
		else if (action.equals("deactivate")) {
			return LanguageUtil.get(
				httpServletRequest,
				"are-you-sure-you-want-to-deactivate-the-license-keys");
		}
		else if (action.equals("make-complimentary")) {
			return LanguageUtil.get(
				httpServletRequest,
				"are-you-sure-you-want-to-make-the-license-keys-complimentary");
		}
		else if (action.equals("remove-complimentary")) {
			return LanguageUtil.get(
				httpServletRequest,
				"are-you-sure-you-want-to-proceed-the-license-keys-will-no-" +
					"longer-be-complimentary");
		}

		return StringPool.BLANK;
	}

	private String _productKey;
	private String _tabs2;

}