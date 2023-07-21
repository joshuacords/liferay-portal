/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.frontend.internal.account.model.Order;
import com.liferay.commerce.frontend.internal.account.model.OrderList;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceOrderResource.class)
public class CommerceOrderResource {

	public OrderList getOrderList(
			long groupId, String keywords, int page, int pageSize,
			HttpServletRequest httpServletRequest,
			CommerceAccount commerceAccount)
		throws PortalException {

		long companyId = _portal.getCompanyId(httpServletRequest);

		groupId =
			_commerceChannelLocalService.getCommerceChannelGroupIdBySiteGroupId(
				groupId);

		List<Order> orders = getOrders(
			companyId, groupId, keywords, page, pageSize, httpServletRequest);

		return new OrderList(
			orders, getOrdersCount(companyId, groupId, keywords));
	}

	protected List<Order> getOrders(
			long companyId, long groupId, String keywords, int page,
			int pageSize, HttpServletRequest httpServletRequest)
		throws PortalException {

		List<Order> orders = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		List<CommerceOrder> userCommerceOrders =
			_commerceOrderService.getUserPendingCommerceOrders(
				companyId, groupId, keywords, start, end);

		for (CommerceOrder commerceOrder : userCommerceOrders) {
			Date modifiedDate = commerceOrder.getModifiedDate();

			String modifiedDateTimeDescription =
				LanguageUtil.getTimeDescription(
					httpServletRequest,
					System.currentTimeMillis() - modifiedDate.getTime(), true);

			orders.add(
				new Order(
					commerceOrder.getCommerceOrderId(),
					commerceOrder.getCommerceAccountId(),
					commerceOrder.getCommerceAccountName(),
					commerceOrder.getPurchaseOrderNumber(),
					LanguageUtil.format(
						httpServletRequest, "x-ago",
						modifiedDateTimeDescription),
					WorkflowConstants.getStatusLabel(commerceOrder.getStatus()),
					_getOrderLinkURL(
						commerceOrder.getCommerceOrderId(),
						httpServletRequest)));
		}

		return orders;
	}

	protected int getOrdersCount(long companyId, long groupId, String keywords)
		throws PortalException {

		return (int)_commerceOrderService.getUserPendingCommerceOrdersCount(
			companyId, groupId, keywords);
	}

	protected Response getResponse(Object object) {
		if (object == null) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(object);

			return Response.ok(
				json, MediaType.APPLICATION_JSON
			).build();
		}
		catch (JsonProcessingException jpe) {
			_log.error(jpe, jpe);
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	private String _getOrderLinkURL(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL editURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.EDIT);

		editURL.setParameter(ActionRequest.ACTION_NAME, "editCommerceOrder");
		editURL.setParameter(Constants.CMD, "setCurrent");
		editURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		String redirect = _portal.getCurrentURL(httpServletRequest);

		editURL.setParameter("redirect", redirect);

		return editURL.toString();
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			disable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderResource.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Portal _portal;

}