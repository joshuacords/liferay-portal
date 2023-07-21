/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.taglib.servlet.taglib;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Alessio Antonio Rendina
 */
public class FormatPriceTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			_formattedPrice = commercePriceFormatter.format(
				commerceContext.getCommerceCurrency(), _price,
				themeDisplay.getLocale());
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public BigDecimal getPrice() {
		return _price;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		commercePriceFormatter = ServletContextUtil.getCommercePriceFormatter();
		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPrice(BigDecimal price) {
		_price = price;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_formattedPrice = null;
		_price = BigDecimal.ZERO;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute(
			"liferay-commerce:format-price:formattedPrice", _formattedPrice);
	}

	protected CommercePriceFormatter commercePriceFormatter;

	private static final String _PAGE = "/format_price/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(FormatPriceTag.class);

	private String _formattedPrice;
	private BigDecimal _price = BigDecimal.ZERO;

}