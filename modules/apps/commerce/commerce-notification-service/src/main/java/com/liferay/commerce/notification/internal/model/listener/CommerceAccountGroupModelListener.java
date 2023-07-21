/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.internal.model.listener;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateCommerceAccountGroupRelLocalService;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceAccountGroupModelListener
	extends BaseModelListener<CommerceAccountGroup> {

	@Override
	public void onBeforeRemove(CommerceAccountGroup commerceAccountGroup) {
		_commerceNotificationTemplateCommerceAccountGroupRelLocalService.
			deleteCNTemplateCommerceAccountGroupRelsByCommerceNotificationTemplateId(
				commerceAccountGroup.getCommerceAccountGroupId());
	}

	@Reference
	private CommerceNotificationTemplateCommerceAccountGroupRelLocalService
		_commerceNotificationTemplateCommerceAccountGroupRelLocalService;

}