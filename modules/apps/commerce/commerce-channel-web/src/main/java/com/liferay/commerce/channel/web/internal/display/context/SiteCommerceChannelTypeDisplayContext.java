/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.channel.web.internal.display.context;

import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.item.selector.criterion.SimpleSiteItemSelectorCriterion;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.product.channel.CommerceChannelHealthStatusRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPTaxCategoryLocalService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;

import java.util.Collections;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class SiteCommerceChannelTypeDisplayContext
	extends CommerceChannelDisplayContext {

	public SiteCommerceChannelTypeDisplayContext(
		ModelResourcePermission<CommerceChannel>
			commerceChannelModelResourcePermission,
		CommerceChannelHealthStatusRegistry commerceChannelHealthStatusRegistry,
		CommerceChannelService commerceChannelService,
		CommerceChannelTypeRegistry commerceChannelTypeRegistry,
		CommerceCurrencyService commerceCurrencyService,
		CommercePaymentMethodRegistry commercePaymentMethodRegistry,
		ConfigurationProvider configurationProvider,
		GroupLocalService groupLocalService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		Portal portal,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService,
		WorkflowDefinitionManager workflowDefinitionManager,
		CPTaxCategoryLocalService cpTaxCategoryLocalService) {

		super(
			commerceChannelModelResourcePermission,
			commerceChannelHealthStatusRegistry, commerceChannelService,
			commerceChannelTypeRegistry, commerceCurrencyService,
			commercePaymentMethodRegistry, configurationProvider,
			httpServletRequest, portal, workflowDefinitionLinkLocalService,
			workflowDefinitionManager, cpTaxCategoryLocalService);

		_groupLocalService = groupLocalService;
		_itemSelector = itemSelector;
	}

	public Group getChannelSite() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return null;
		}

		return _groupLocalService.fetchGroup(commerceChannel.getSiteGroupId());
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		SimpleSiteItemSelectorCriterion simpleSiteItemSelectorCriterion =
			new SimpleSiteItemSelectorCriterion();

		simpleSiteItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "sitesSelectItem",
			simpleSiteItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	private final GroupLocalService _groupLocalService;
	private final ItemSelector _itemSelector;

}