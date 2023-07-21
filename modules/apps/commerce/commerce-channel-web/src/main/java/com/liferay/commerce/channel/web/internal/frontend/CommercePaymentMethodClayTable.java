/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.channel.web.internal.frontend;

import com.liferay.commerce.channel.web.internal.frontend.util.CommerceChannelClayTableUtil;
import com.liferay.commerce.channel.web.internal.model.PaymentMethod;
import com.liferay.commerce.frontend.ClayMenuActionItem;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetAction;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetActionProvider;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommercePaymentMethodClayTable.NAME,
		"commerce.data.set.display.name=" + CommercePaymentMethodClayTable.NAME
	},
	service = {
		ClayDataSetActionProvider.class, ClayDataSetDisplayView.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommercePaymentMethodClayTable
	extends ClayTableDataSetDisplayView
	implements ClayDataSetActionProvider,
			   CommerceDataSetDataProvider<PaymentMethod> {

	public static final String NAME = "payment-methods";

	@Override
	public List<ClayDataSetAction> clayDataSetActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayDataSetAction> clayTableActions = new ArrayList<>();

		try {
			PaymentMethod paymentMethod = (PaymentMethod)model;

			long commerceChannelId = ParamUtil.getLong(
				httpServletRequest, "commerceChannelId");

			PortletURL portletURL = PortletProviderUtil.getPortletURL(
				httpServletRequest,
				CommercePaymentMethodGroupRel.class.getName(),
				PortletProvider.Action.EDIT);

			portletURL.setParameter(
				"commerceChannelId", String.valueOf(commerceChannelId));
			portletURL.setParameter(
				"commercePaymentMethodEngineKey",
				String.valueOf(paymentMethod.getKey()));

			portletURL.setWindowState(LiferayWindowState.POP_UP);

			ClayDataSetAction clayDataSetAction = new ClayDataSetAction(
				StringPool.BLANK, portletURL.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "edit"), null, false,
				false);

			clayDataSetAction.setTarget("sidePanel");

			clayTableActions.add(clayDataSetAction);

			CommerceChannel commerceChannel =
				_commerceChannelService.getCommerceChannel(commerceChannelId);

			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					fetchCommercePaymentMethodGroupRel(
						commerceChannel.getGroupId(), paymentMethod.getKey());

			if (commercePaymentMethodGroupRel != null) {
				PortletURL permissionsURL = _getPaymentMethodPermissionURL(
					commercePaymentMethodGroupRel, paymentMethod.getKey(),
					httpServletRequest);

				ClayDataSetAction permissionsClayDataSetAction =
					new ClayDataSetAction(
						StringPool.BLANK, permissionsURL.toString(),
						StringPool.BLANK,
						LanguageUtil.get(httpServletRequest, "permissions"),
						StringPool.BLANK, false, false);

				permissionsClayDataSetAction.setTarget(
					ClayMenuActionItem.
						CLAY_MENU_ACTION_ITEM_TARGET_MODAL_PERMISSIONS);

				clayTableActions.add(permissionsClayDataSetAction);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		Map<String, CommercePaymentMethod> commercePaymentMethodMap =
			_commercePaymentMethodRegistry.getCommercePaymentMethods();

		return commercePaymentMethodMap.size();
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		ClayTableSchemaField nameField = clayTableSchemaBuilder.addField(
			"name", "name");

		nameField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addField("description", "description");

		clayTableSchemaBuilder.addField("paymentEngine", "payment-engine");

		ClayTableSchemaField statusField = clayTableSchemaBuilder.addField(
			"status", "status");

		statusField.setContentRenderer("label");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public List<PaymentMethod> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long commerceChannelId = ParamUtil.getLong(
			httpServletRequest, "commerceChannelId");

		CommerceChannel commerceChannel =
			_commerceChannelService.getCommerceChannel(commerceChannelId);

		Map<String, CommercePaymentMethod> commercePaymentMethodMap =
			_commercePaymentMethodRegistry.getCommercePaymentMethods();

		List<PaymentMethod> paymentMethods = new ArrayList<>();

		for (Map.Entry<String, CommercePaymentMethod> entry :
				commercePaymentMethodMap.entrySet()) {

			CommercePaymentMethod commercePaymentMethod = entry.getValue();

			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					fetchCommercePaymentMethodGroupRel(
						commerceChannel.getGroupId(),
						commercePaymentMethod.getKey());

			String commercePaymentDescription =
				commercePaymentMethod.getDescription(themeDisplay.getLocale());
			String commercePaymentName = commercePaymentMethod.getName(
				themeDisplay.getLocale());

			if (commercePaymentMethodGroupRel != null) {
				commercePaymentDescription =
					commercePaymentMethodGroupRel.getDescription(
						themeDisplay.getLocale());
				commercePaymentName = commercePaymentMethodGroupRel.getName(
					themeDisplay.getLocale());
			}

			paymentMethods.add(
				new PaymentMethod(
					commercePaymentDescription, commercePaymentMethod.getKey(),
					commercePaymentName,
					commercePaymentMethod.getName(themeDisplay.getLocale()),
					CommerceChannelClayTableUtil.getLabelField(
						_isActive(commercePaymentMethodGroupRel),
						themeDisplay.getLocale())));
		}

		return paymentMethods;
	}

	private PortletURL _getPaymentMethodPermissionURL(
			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
			String paymentMethodKey, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			httpServletRequest,
			"com_liferay_portlet_configuration_web_portlet_" +
				"PortletConfigurationPortlet",
			ActionRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/edit_permissions.jsp");

		String redirect = ParamUtil.getString(
			httpServletRequest, "currentUrl",
			_portal.getCurrentURL(httpServletRequest));

		portletURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			redirect);

		portletURL.setParameter(
			"modelResource", CommercePaymentMethodGroupRel.class.getName());
		portletURL.setParameter("modelResourceDescription", paymentMethodKey);
		portletURL.setParameter(
			"resourcePrimKey",
			String.valueOf(
				commercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId()));

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException windowStateException) {
			throw new PortalException(windowStateException);
		}

		return portletURL;
	}

	private boolean _isActive(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		if (commercePaymentMethodGroupRel == null) {
			return false;
		}

		return commercePaymentMethodGroupRel.isActive();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private Portal _portal;

}