/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.subscription.social;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.social.BaseSocialActivityManager;
import com.liferay.portal.kernel.social.SocialActivityManager;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.social.kernel.service.SocialActivityLocalService;

/**
 * @author Adolfo Pérez
 */
@OSGiBeanProperties(
	property = "model.class.name=com.liferay.portal.kernel.model.Subscription",
	service = SocialActivityManager.class
)
public class SubscriptionSocialActivityManager
	extends BaseSocialActivityManager<Subscription> {

	@Override
	protected String getClassName(Subscription subscription) {
		return subscription.getClassName();
	}

	@Override
	protected long getPrimaryKey(Subscription subscription) {
		return subscription.getClassPK();
	}

	@Override
	protected SocialActivityLocalService getSocialActivityLocalService() {
		return socialActivityLocalService;
	}

	@BeanReference(type = SocialActivityLocalService.class)
	protected SocialActivityLocalService socialActivityLocalService;

}