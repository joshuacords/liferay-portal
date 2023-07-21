/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.subscription.internal.model.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.subscription.service.SubscriptionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = ModelListener.class)
public class SubscriptionPortletPreferencesModelListener
	extends BaseModelListener<PortletPreferences> {

	@Override
	public void onAfterRemove(PortletPreferences portletPreferences) {
		deleteSubscriptions(portletPreferences);
	}

	protected void deleteSubscriptions(PortletPreferences portletPreferences) {
		if (portletPreferences == null) {
			return;
		}

		try {
			_subscriptionLocalService.deleteSubscriptions(
				portletPreferences.getCompanyId(),
				portletPreferences.getModelClassName(),
				portletPreferences.getPortletPreferencesId());
		}
		catch (Exception exception) {
			_log.error("Unable to delete subscriptions", exception);
		}
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionPortletPreferencesModelListener.class);

	private SubscriptionLocalService _subscriptionLocalService;

}