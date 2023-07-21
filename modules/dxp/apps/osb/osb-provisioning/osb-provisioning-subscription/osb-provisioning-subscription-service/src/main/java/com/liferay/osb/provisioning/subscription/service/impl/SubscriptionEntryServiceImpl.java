/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service.impl;

import com.liferay.osb.provisioning.subscription.service.base.SubscriptionEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=provisioning",
		"json.web.service.context.path=SubscriptionEntry"
	},
	service = AopService.class
)
public class SubscriptionEntryServiceImpl
	extends SubscriptionEntryServiceBaseImpl {
}