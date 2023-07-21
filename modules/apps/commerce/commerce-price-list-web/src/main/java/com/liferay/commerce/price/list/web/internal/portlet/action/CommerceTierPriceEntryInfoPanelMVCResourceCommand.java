/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.web.internal.portlet.action;

import com.liferay.commerce.price.list.constants.CommercePriceListPortletKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListWebKeys;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.commerce.price.list.web.internal.display.context.CommerceTierPriceEntryDisplayContext;
import com.liferay.commerce.price.list.web.portlet.action.CommercePriceListActionHelper;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommercePriceListPortletKeys.COMMERCE_PRICE_LIST,
		"mvc.command.name=commerceTierPriceEntryInfoPanel"
	},
	service = MVCResourceCommand.class
)
public class CommerceTierPriceEntryInfoPanelMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		CommerceTierPriceEntryDisplayContext
			commerceTierPriceEntryDisplayContext =
				new CommerceTierPriceEntryDisplayContext(
					_commercePriceListActionHelper,
					_commercePriceListModelResourcePermission,
					_commerceTierPriceEntryService,
					_portal.getHttpServletRequest(resourceRequest));

		resourceRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceTierPriceEntryDisplayContext);

		resourceRequest.setAttribute(
			CommercePriceListWebKeys.COMMERCE_TIER_PRICE_ENTRIES,
			_commercePriceListActionHelper.getCommerceTierPriceEntries(
				resourceRequest));

		include(
			resourceRequest, resourceResponse,
			"/tier_price_entry_info_panel.jsp");
	}

	@Reference
	private CommercePriceListActionHelper _commercePriceListActionHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommercePriceList)"
	)
	private ModelResourcePermission<CommercePriceList>
		_commercePriceListModelResourcePermission;

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

	@Reference
	private Portal _portal;

}