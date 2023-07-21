/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Fabio Diego Mastrorilli
 */
public class AddressModalTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			putValue(
				"countriesAPI",
				StringBundler.concat(
					PortalUtil.getPortalURL(request),
					"/o/commerce-ui/address/countries-by-channel-id?channelId=",
					String.valueOf(commerceContext.getCommerceChannelId()),
					"&p_auth=", AuthTokenUtil.getToken(request)));
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			putValue(
				"countriesAPI",
				StringBundler.concat(
					PortalUtil.getPortalURL(request),
					"/o/commerce-ui/address/countries/?p_auth=",
					AuthTokenUtil.getToken(request)));
		}

		putValue(
			"regionsAPI",
			PortalUtil.getPortalURL(request) +
				"/o/commerce-ui/address/regions/");
		putValue("spritemap", themeDisplay.getPathThemeImages() + "/icons.svg");

		setTemplateNamespace("AddressModal.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = ServletContextUtil.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/address_modal/AddressModal.es");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddressModalTag.class);

}