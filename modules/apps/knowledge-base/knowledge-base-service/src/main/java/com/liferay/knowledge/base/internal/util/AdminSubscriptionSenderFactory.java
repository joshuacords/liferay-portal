/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.util;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.SubscriptionSender;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class AdminSubscriptionSenderFactory {

	public static SubscriptionSender createSubscriptionSender(
		KBArticle kbArticle, ServiceContext serviceContext) {

		return new AdminSubscriptionSender(
			kbArticle, _kbArticleModelResourcePermission, serviceContext);
	}

	@Reference(
		target = "(model.class.name=com.liferay.knowledge.base.model.KBArticle)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<KBArticle> modelResourcePermission) {

		_kbArticleModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<KBArticle>
		_kbArticleModelResourcePermission;

}