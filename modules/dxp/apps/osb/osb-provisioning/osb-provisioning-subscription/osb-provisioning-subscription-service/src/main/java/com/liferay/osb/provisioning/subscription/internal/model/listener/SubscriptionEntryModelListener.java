/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.subscription.internal.model.listener;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class SubscriptionEntryModelListener
	extends BaseModelListener<SubscriptionEntry> {

	@Override
	public void onAfterCreate(SubscriptionEntry subscriptionEntry)
		throws ModelListenerException {

		try {
			_reindex(subscriptionEntry);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterRemove(SubscriptionEntry subscriptionEntry)
		throws ModelListenerException {

		try {
			_reindex(subscriptionEntry);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(SubscriptionEntry subscriptionEntry)
		throws PortalException {

		ClassName subscriptionEntryClassName =
			_classNameLocalService.getClassName(
				subscriptionEntry.getClassNameId());

		String className = subscriptionEntryClassName.getClassName();

		if (className.equals(LicenseKey.class.getName())) {
			_licenseKeyLocalService.reindex(subscriptionEntry.getClassPK());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionEntryModelListener.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}