/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.CustomAttributesUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionOptionValueRelDisplayContext
	extends BaseCPDefinitionsDisplayContext {

	public CPDefinitionOptionValueRelDisplayContext(
		ActionHelper actionHelper,
		CommerceCatalogLocalService commerceCatalogLocalService,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		HttpServletRequest httpServletRequest) {

		super(actionHelper, httpServletRequest);

		_commerceCatalogLocalService = commerceCatalogLocalService;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
	}

	public CPInstance fetchCPInstance() throws PortalException {
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			getCPDefinitionOptionValueRel();

		if (cpDefinitionOptionValueRel == null) {
			return null;
		}

		return cpDefinitionOptionValueRel.fetchCPInstance();
	}

	public CommerceCurrency getCommerceCurrency() throws PortalException {
		CPDefinition cpDefinition = getCPDefinition();

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
				cpDefinition.getGroupId());

		return _commerceCurrencyLocalService.getCommerceCurrency(
			commerceCatalog.getCompanyId(),
			commerceCatalog.getCommerceCurrencyCode());
	}

	public CPDefinitionOptionRel getCPDefinitionOptionRel()
		throws PortalException {

		if (_cpDefinitionOptionRel != null) {
			return _cpDefinitionOptionRel;
		}

		_cpDefinitionOptionRel = actionHelper.getCPDefinitionOptionRel(
			cpRequestHelper.getRenderRequest());

		return _cpDefinitionOptionRel;
	}

	public long getCPDefinitionOptionRelId() throws PortalException {
		CPDefinitionOptionRel cpDefinitionOptionRel =
			getCPDefinitionOptionRel();

		if (cpDefinitionOptionRel == null) {
			return 0;
		}

		return cpDefinitionOptionRel.getCPDefinitionOptionRelId();
	}

	public CPDefinitionOptionValueRel getCPDefinitionOptionValueRel()
		throws PortalException {

		return actionHelper.getCPDefinitionOptionValueRel(
			cpRequestHelper.getRenderRequest());
	}

	public long getCPDefinitionOptionValueRelId() throws PortalException {
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			getCPDefinitionOptionValueRel();

		if (cpDefinitionOptionValueRel == null) {
			return 0;
		}

		return cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId();
	}

	public String getRemoveSkuUrl(String redirect) throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(Constants.CMD, "deleteSku");
		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editProductDefinitionOptionValueRel");
		portletURL.setParameter(
			"cpDefinitionOptionValueRelId",
			String.valueOf(getCPDefinitionOptionValueRelId()));
		portletURL.setParameter("redirect", redirect);

		return portletURL.toString();
	}

	@Override
	public String getScreenNavigationCategoryKey() {
		return CPDefinitionScreenNavigationConstants.CATEGORY_KEY_OPTIONS;
	}

	public boolean hasCustomAttributesAvailable() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return CustomAttributesUtil.hasCustomAttributes(
			themeDisplay.getCompanyId(),
			CPDefinitionOptionValueRel.class.getName(),
			getCPDefinitionOptionValueRelId(), null);
	}

	private final CommerceCatalogLocalService _commerceCatalogLocalService;
	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;
	private CPDefinitionOptionRel _cpDefinitionOptionRel;

}