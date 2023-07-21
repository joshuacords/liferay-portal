/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.util.CPCompareHelperUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Fabio Diego Mastrorilli
 */
public class CompareCheckboxTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		putValue("checkboxVisible", true);
		putValue("compareAvailable", true);
		putValue("inCompare", false);
		putValue("labelVisible", true);

		Map<String, Object> context = getContext();

		long cpDefinitionId = GetterUtil.getLong(context.get("productId"));

		try {
			CPDefinition cpDefinition =
				CPDefinitionLocalServiceUtil.getCPDefinition(cpDefinitionId);

			putValue("pictureUrl", cpDefinition.getDefaultImageThumbnailSrc());

			long commerceAccountId = 0;

			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			CommerceAccount commerceAccount =
				commerceContext.getCommerceAccount();

			if (commerceAccount != null) {
				commerceAccountId = commerceAccount.getCommerceAccountId();
			}

			HttpServletRequest originalHttpServletRequest =
				PortalUtil.getOriginalServletRequest(request);

			List<Long> cpDefinitionIds = CPCompareHelperUtil.getCPDefinitionIds(
				commerceContext.getCommerceChannelGroupId(), commerceAccountId,
				originalHttpServletRequest.getSession());

			putValue("inCompare", cpDefinitionIds.contains(cpDefinitionId));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		setTemplateNamespace("CompareCheckbox.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = ServletContextUtil.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/compare_checkbox/CompareCheckbox.es");
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		putValue("productId", cpDefinitionId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CompareCheckboxTag.class);

}