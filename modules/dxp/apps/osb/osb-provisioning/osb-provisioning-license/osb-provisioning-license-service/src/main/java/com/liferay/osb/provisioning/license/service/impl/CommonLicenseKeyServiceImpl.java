/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.provisioning.license.service.base.CommonLicenseKeyServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=provisioning",
		"json.web.service.context.path=CommonLicenseKey"
	},
	service = AopService.class
)
public class CommonLicenseKeyServiceImpl
	extends CommonLicenseKeyServiceBaseImpl {
}