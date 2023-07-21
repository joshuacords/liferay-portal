/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.internal.upgrade;

import com.liferay.commerce.payment.internal.upgrade.v1_0_1.CommercePaymentMethodGroupRelUpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CommercePaymentUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PAYMENT UPGRADE STEP REGISTRATOR STARTED");
		}

		registry.register(
			_SCHEMA_VERSION_1_0_0, _SCHEMA_VERSION_1_0_1,
			new CommercePaymentMethodGroupRelUpgradeProcess(
				_classNameLocalService, _groupLocalService));

		registry.register(
			_SCHEMA_VERSION_1_0_1, _SCHEMA_VERSION_1_1_0,
			new com.liferay.commerce.payment.internal.upgrade.v1_1_0.
				CommercePaymentMethodGroupRelUpgradeProcess(
					_resourceActionLocalService, _resourceLocalService));

		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE PAYMENT UPGRADE STEP REGISTRATOR FINISHED");
		}
	}

	private static final String _SCHEMA_VERSION_1_0_0 = "1.0.0";

	private static final String _SCHEMA_VERSION_1_0_1 = "1.0.1";

	private static final String _SCHEMA_VERSION_1_1_0 = "1.1.0";

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePaymentUpgradeStepRegistrator.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

}