/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.web.internal.search.DisplayTerm;
import com.liferay.osb.provisioning.web.internal.search.LicenseKeyDisplayTerms;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewLicenseKeysManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewLicenseKeysManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest, SearchContainer searchContainer,
		LicenseEntryLocalService licenseEntryLocalService,
		ProductWebService productWebService) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);

		_licenseEntryLocalService = licenseEntryLocalService;
		_productWebService = productWebService;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = liferayPortletResponse.createRenderURL();

		return clearResultsURL.toString();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		LicenseKeyDisplayTerms licenseKeyDisplayTerms =
			(LicenseKeyDisplayTerms)searchContainer.getDisplayTerms();

		if (!licenseKeyDisplayTerms.isAdvancedSearch()) {
			return null;
		}

		return new LabelItemList() {
			{
				List<DisplayTerm> displayTermsList =
					licenseKeyDisplayTerms.getDisplayTermsList();

				for (DisplayTerm displayTerm : displayTermsList) {
					String[] values = StringUtil.split(displayTerm.getValue());

					for (String value : values) {
						add(
							labelItem -> {
								labelItem.putData(
									"removeLabelURL",
									_getRemoveLabelURL(
										displayTerm.getName(), values, value));

								labelItem.setCloseable(true);

								labelItem.setLabel(
									_getLabel(displayTerm.getLabel(), value));
							});
					}
				}
			}
		};
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "licenseKeySearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	private String _getLabel(String key, String value) {
		if (key.equals("product")) {
			value = _getProductName(value);
		}

		if (key.equals("license-type")) {
			value = _getLicenseType(value);
		}

		if (value.equals(StringPool.TRUE)) {
			value = LanguageUtil.get(request, "yes");
		}
		else if (value.equals(StringPool.FALSE)) {
			value = LanguageUtil.get(request, "no");
		}

		return String.format("%s: %s", LanguageUtil.get(request, key), value);
	}

	private String _getLicenseType(String licenseTypeId) {
		try {
			LicenseEntry licenseEntry =
				_licenseEntryLocalService.getLicenseEntry(
					Long.valueOf(licenseTypeId));

			return licenseEntry.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return licenseTypeId;
		}
	}

	private String _getProductName(String productKey) {
		try {
			Product product = _productWebService.getProduct(productKey);

			return product.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return productKey;
		}
	}

	private String _getRemoveLabelURL(
		String displayTermName, String[] values, String value) {

		PortletURL removeLabelURL = getPortletURL();

		String[] removeKeywords = ArrayUtil.remove(values, value);

		removeLabelURL.setParameter(
			displayTermName, StringUtil.merge(removeKeywords));

		if (displayTermName.equals(LicenseKeyDisplayTerms.PRODUCTS)) {
			removeLabelURL.setParameter("product", StringPool.BLANK);
		}

		return removeLabelURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewLicenseKeysManagementToolbarDisplayContext.class);

	private final LicenseEntryLocalService _licenseEntryLocalService;
	private final ProductWebService _productWebService;

}