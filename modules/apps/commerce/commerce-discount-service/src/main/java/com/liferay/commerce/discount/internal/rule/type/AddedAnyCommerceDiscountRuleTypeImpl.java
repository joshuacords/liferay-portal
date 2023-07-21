/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.rule.type;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.constants.CommerceDiscountRuleConstants;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleType;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.ToLongFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.discount.rule.type.key=" + CommerceDiscountRuleConstants.TYPE_ADDED_ANY,
		"commerce.discount.rule.type.order:Integer=60"
	},
	service = CommerceDiscountRuleType.class
)
public class AddedAnyCommerceDiscountRuleTypeImpl
	implements CommerceDiscountRuleType {

	@Override
	public boolean evaluate(
			CommerceDiscountRule commerceDiscountRule,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (commerceOrder == null) {
			return false;
		}

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		Stream<CommerceOrderItem> stream = commerceOrderItems.stream();

		LongStream longStream = stream.mapToLong(_getOrderItemToLongFunction());

		long[] orderItemDefinitionIds = longStream.toArray();

		String settingsProperty = commerceDiscountRule.getSettingsProperty(
			commerceDiscountRule.getType());

		long[] cpDefinitionIds = StringUtil.split(settingsProperty, 0L);

		for (long cpDefinitionId : cpDefinitionIds) {
			if (ArrayUtil.contains(orderItemDefinitionIds, cpDefinitionId)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String getKey() {
		return CommerceDiscountRuleConstants.TYPE_ADDED_ANY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "has-one-of-these-products");
	}

	private ToLongFunction<CommerceOrderItem> _getOrderItemToLongFunction() {
		return new ToLongFunction<CommerceOrderItem>() {

			@Override
			public long applyAsLong(CommerceOrderItem commerceOrderItem) {
				return commerceOrderItem.getCPDefinitionId();
			}

		};
	}

}