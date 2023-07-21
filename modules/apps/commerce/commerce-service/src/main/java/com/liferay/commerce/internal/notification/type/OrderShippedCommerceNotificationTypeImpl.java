/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.notification.type;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_SHIPPED,
		"commerce.notification.type.order:Integer=70"
	},
	service = CommerceNotificationType.class
)
public class OrderShippedCommerceNotificationTypeImpl
	implements CommerceNotificationType {

	@Override
	public String getClassName(Object object) {
		if (!(object instanceof CommerceOrder)) {
			return null;
		}

		return CommerceOrder.class.getName();
	}

	@Override
	public long getClassPK(Object object) {
		if (!(object instanceof CommerceOrder)) {
			return 0;
		}

		CommerceOrder commerceOrder = (CommerceOrder)object;

		return commerceOrder.getCommerceOrderId();
	}

	@Override
	public String getKey() {
		return CommerceOrderConstants.ORDER_NOTIFICATION_SHIPPED;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, CommerceOrderConstants.ORDER_NOTIFICATION_SHIPPED);
	}

}