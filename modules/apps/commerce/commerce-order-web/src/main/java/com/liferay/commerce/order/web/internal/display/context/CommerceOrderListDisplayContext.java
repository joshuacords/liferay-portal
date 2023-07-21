/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.display.context;

import com.liferay.commerce.frontend.clay.data.set.ClayHeadlessDataSetActionTemplate;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.web.internal.display.context.util.CommerceOrderRequestHelper;
import com.liferay.commerce.order.web.internal.search.CommerceOrderDisplayTerms;
import com.liferay.commerce.order.web.internal.security.permission.resource.CommerceOrderPermission;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderListDisplayContext {

	public CommerceOrderListDisplayContext(
		CommerceOrderNoteService commerceOrderNoteService,
		RenderRequest renderRequest) {

		_commerceOrderNoteService = commerceOrderNoteService;

		_commerceOrderRequestHelper = new CommerceOrderRequestHelper(
			renderRequest);

		_keywords = ParamUtil.getString(renderRequest, "keywords");
	}

	public List<ClayHeadlessDataSetActionTemplate>
			getClayHeadlessDataSetActionTemplates()
		throws PortalException {

		List<ClayHeadlessDataSetActionTemplate>
			clayHeadlessDataSetActionTemplates = new ArrayList<>();

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			_commerceOrderRequestHelper.getRequest(),
			CommerceOrder.class.getName(), PortletProvider.Action.MANAGE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter("commerceOrderId", "{id}");

		clayHeadlessDataSetActionTemplates.add(
			new ClayHeadlessDataSetActionTemplate(
				portletURL.toString(), "view", "view",
				LanguageUtil.get(
					_commerceOrderRequestHelper.getRequest(), "view"),
				"get", null, null));

		clayHeadlessDataSetActionTemplates.add(
			new ClayHeadlessDataSetActionTemplate(
				"/o/headless-commerce-admin-order/v1.0/orders/{id}", "trash",
				"delete",
				LanguageUtil.get(
					_commerceOrderRequestHelper.getRequest(), "delete"),
				"delete", "delete", "async"));

		return clayHeadlessDataSetActionTemplates;
	}

	public int getCommerceOrderNotesCount(CommerceOrder commerceOrder)
		throws PortalException {

		if (CommerceOrderPermission.contains(
				_commerceOrderRequestHelper.getPermissionChecker(),
				commerceOrder, ActionKeys.UPDATE_DISCUSSION)) {

			return _commerceOrderNoteService.getCommerceOrderNotesCount(
				commerceOrder.getCommerceOrderId());
		}

		return _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrder.getCommerceOrderId(), false);
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = getSearchURL();

		for (String displayTerm : CommerceOrderDisplayTerms.VALID_TERMS) {
			String paramValue = ParamUtil.getString(
				_commerceOrderRequestHelper.getRequest(), displayTerm);

			if (Validator.isNotNull(paramValue)) {
				portletURL.setParameter(displayTerm, paramValue);
			}
		}

		return portletURL;
	}

	public PortletURL getSearchURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceOrderRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		if (Validator.isNotNull(_keywords)) {
			portletURL.setParameter("keywords", _keywords);
		}

		return portletURL;
	}

	private final CommerceOrderNoteService _commerceOrderNoteService;
	private final CommerceOrderRequestHelper _commerceOrderRequestHelper;
	private final String _keywords;

}