/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.verify;

import com.liferay.mobile.device.rules.internal.verify.model.MDRRuleGroupInstanceVerifiableModel;
import com.liferay.mobile.device.rules.internal.verify.model.MDRRuleGroupVerifiableModel;
import com.liferay.mobile.device.rules.service.MDRActionLocalService;
import com.liferay.portal.verify.VerifyProcess;
import com.liferay.portal.verify.VerifyResourcePermissions;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Tomas Polesovsky
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.mobile.device.rules.service",
	service = VerifyProcess.class
)
@Deprecated
public class MDRServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyResourcedModels();
	}

	@Reference(unbind = "-")
	protected void setMDRActionLocalService(
		MDRActionLocalService mdrActionLocalService) {
	}

	protected void verifyResourcedModels() throws Exception {
		_verifyResourcePermissions.verify(
			new MDRRuleGroupInstanceVerifiableModel());
		_verifyResourcePermissions.verify(new MDRRuleGroupVerifiableModel());
	}

	private final VerifyResourcePermissions _verifyResourcePermissions =
		new VerifyResourcePermissions();

}