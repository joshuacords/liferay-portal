/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.web.internal.display.context;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.frontend.ClayCreationMenu;
import com.liferay.commerce.frontend.ClayCreationMenuActionItem;
import com.liferay.commerce.percentage.PercentageFormatter;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPTaxCategoryService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate;
import com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateService;
import com.liferay.commerce.tax.engine.fixed.web.internal.servlet.taglib.ui.CommerceTaxMethodFixedRatesScreenNavigationEntry;
import com.liferay.commerce.tax.service.CommerceTaxMethodService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceTaxFixedRatesDisplayContext
	extends BaseCommerceTaxFixedRateDisplayContext {

	public CommerceTaxFixedRatesDisplayContext(
		CommerceChannelLocalService commerceChannelLocalService,
		ModelResourcePermission<CommerceChannel>
			commerceChannelModelResourcePermission,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceTaxFixedRateService commerceTaxFixedRateService,
		CommerceTaxMethodService commerceTaxMethodService,
		CPTaxCategoryService cpTaxCategoryService,
		PercentageFormatter percentageFormatter, RenderRequest renderRequest) {

		super(
			commerceChannelLocalService, commerceChannelModelResourcePermission,
			commerceCurrencyLocalService, commerceTaxMethodService,
			cpTaxCategoryService, percentageFormatter, renderRequest);

		_commerceTaxFixedRateService = commerceTaxFixedRateService;
	}

	public String getAddTaxRateURL() throws Exception {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			commerceTaxFixedRateRequestHelper.getRequest(),
			CommercePortletKeys.COMMERCE_TAX_METHODS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceTaxFixedRate");
		portletURL.setParameter(
			"commerceTaxMethodId", String.valueOf(getCommerceTaxMethodId()));

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public ClayCreationMenu getClayCreationMenu() throws Exception {
		ClayCreationMenu clayCreationMenu = new ClayCreationMenu();

		if (hasUpdateCommerceChannelPermission()) {
			clayCreationMenu.addClayCreationMenuActionItem(
				getAddTaxRateURL(),
				LanguageUtil.get(
					commerceTaxFixedRateRequestHelper.getRequest(),
					"add-tax-rate"),
				ClayCreationMenuActionItem.
					CLAY_MENU_ACTION_ITEM_TARGET_MODAL_LARGE);
		}

		return clayCreationMenu;
	}

	public CommerceTaxFixedRate getCommerceTaxFixedRate()
		throws PortalException {

		long commerceTaxFixedRateId = ParamUtil.getLong(
			commerceTaxFixedRateRequestHelper.getRequest(),
			"commerceTaxFixedRateId");

		return _commerceTaxFixedRateService.fetchCommerceTaxFixedRate(
			commerceTaxFixedRateId);
	}

	@Override
	public String getScreenNavigationCategoryKey() {
		return CommerceTaxMethodFixedRatesScreenNavigationEntry.CATEGORY_KEY;
	}

	private final CommerceTaxFixedRateService _commerceTaxFixedRateService;

}