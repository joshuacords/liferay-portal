/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.web.internal.portlet.action;

import com.liferay.osb.koroneiki.trunk.constants.TrunkPortletKeys;
import com.liferay.osb.koroneiki.trunk.constants.TrunkWebKeys;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TrunkPortletKeys.PRODUCTS_ADMIN,
		"mvc.command.name=/products_admin/edit_product_entry"
	},
	service = MVCRenderCommand.class
)
public class EditProductEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long productEntryId = ParamUtil.getLong(
				renderRequest, "productEntryId");

			if (productEntryId > 0) {
				renderRequest.setAttribute(
					TrunkWebKeys.PRODUCT_ENTRY,
					_productEntryLocalService.getProductEntry(productEntryId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("external-links")) {
				return "/products_admin/edit_product_entry_external_links.jsp";
			}

			return "/products_admin/edit_product_entry.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/products_admin/error.jsp";
		}
	}

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}