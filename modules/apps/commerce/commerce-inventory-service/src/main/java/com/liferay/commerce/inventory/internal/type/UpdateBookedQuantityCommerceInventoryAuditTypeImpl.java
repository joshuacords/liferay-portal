/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.internal.type;

import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.commerce.inventory.type.CommerceInventoryAuditType;
import com.liferay.commerce.inventory.type.CommerceInventoryAuditTypeConstants;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.inventory.audit.type.key=" + CommerceInventoryConstants.AUDIT_TYPE_UPDATE_BOOKED_QUANTITY,
	service = CommerceInventoryAuditType.class
)
public class UpdateBookedQuantityCommerceInventoryAuditTypeImpl
	implements CommerceInventoryAuditType {

	@Override
	public String formatLog(long userId, String context, Locale locale)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject(context);

		User user = _userLocalService.getUserById(userId);

		StringBundler contextSB = new StringBundler(8);

		contextSB.append(LanguageUtil.get(locale, "order"));
		contextSB.append(CharPool.SPACE);
		contextSB.append(
			jsonObject.get(CommerceInventoryAuditTypeConstants.ORDER_ID));
		contextSB.append(CharPool.COMMA);
		contextSB.append(CharPool.SPACE);
		contextSB.append(LanguageUtil.get(locale, "user"));
		contextSB.append(CharPool.SPACE);
		contextSB.append(user.getFullName());

		return contextSB.toString();
	}

	@Override
	public String getLog(Map<String, String> context) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		for (Map.Entry<String, String> entry : context.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}

		return jsonObject.toString();
	}

	@Override
	public String getType() {
		return CommerceInventoryConstants.AUDIT_TYPE_BOOKED_QUANTITY;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private UserLocalService _userLocalService;

}