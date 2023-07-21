/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal;

import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.constants.CommerceDiscountRuleConstants;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleTypeJSPContributor;
import com.liferay.commerce.discount.service.CommerceDiscountRuleService;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.pricing.web.internal.display.context.CartTotalCommerceDiscountRuleDisplayContext;
import com.liferay.commerce.pricing.web.internal.util.CommercePricingUtil;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.discount.rule.type.jsp.contributor.key=" + CommerceDiscountRuleConstants.TYPE_CART_TOTAL,
	service = CommerceDiscountRuleTypeJSPContributor.class
)
public class CartTotalCommerceDiscountRuleTypeJSPContributor
	implements CommerceDiscountRuleTypeJSPContributor {

	@Override
	public void render(
			long commerceDiscountId, long commerceDiscountRuleId,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CartTotalCommerceDiscountRuleDisplayContext
			cartTotalCommerceDiscountRuleDisplayContext =
				new CartTotalCommerceDiscountRuleDisplayContext(
					_commerceCurrencyLocalService, _commerceDiscountRuleService,
					httpServletRequest);

		httpServletRequest.setAttribute(
			"view.jsp-cartTotalCommerceDiscountRuleDisplayContext",
			cartTotalCommerceDiscountRuleDisplayContext);

		if (Objects.equals(
				CommercePricingUtil.getPricingEngineVersion(
					_configurationProvider),
				CommercePricingConstants.VERSION_1_0)) {

			_jspRenderer.renderJSP(
				_servletContext, httpServletRequest, httpServletResponse,
				"/discount/rule/type/cart_total/v1_0/view.jsp");
		}
		else {
			_jspRenderer.renderJSP(
				_servletContext, httpServletRequest, httpServletResponse,
				"/discount/rule/type/cart_total/v2_0/view.jsp");
		}
	}

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceDiscountRuleService _commerceDiscountRuleService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.pricing.web)"
	)
	private ServletContext _servletContext;

}