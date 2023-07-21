/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.router;

import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.ContactMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.DossieraCreateMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.DossieraUpdateMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.EntitlementCreateMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.EntitlementDeleteMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.OktaUsersMessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.ProductMessageSubscriber;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class ProvisioningMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setContactMessageSubscriber(
		ContactMessageSubscriber contactMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(contactMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setDossieraCreateMessageSubscriber(
		DossieraCreateMessageSubscriber dossieraCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(dossieraCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setDossieraUpdateMessageSubscriber(
		DossieraUpdateMessageSubscriber dossieraUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(dossieraUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setEntitlementCreateMessageSubscriber(
		EntitlementCreateMessageSubscriber entitlementCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(entitlementCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setEntitlementDeleteMessageSubscriber(
		EntitlementDeleteMessageSubscriber entitlementDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(entitlementDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setOktaUsersMessageSubscriber(
		OktaUsersMessageSubscriber oktaUsersMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(oktaUsersMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductMessageSubscriber(
		ProductMessageSubscriber productMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productMessageSubscriber, properties);
	}

}