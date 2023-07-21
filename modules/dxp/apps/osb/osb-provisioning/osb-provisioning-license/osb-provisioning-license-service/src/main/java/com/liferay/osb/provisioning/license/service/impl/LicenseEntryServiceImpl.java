/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.provisioning.license.service.base.LicenseEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the license entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.osb.provisioning.license.service.LicenseEntryService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=provisioning",
		"json.web.service.context.path=LicenseEntry"
	},
	service = AopService.class
)
public class LicenseEntryServiceImpl extends LicenseEntryServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.osb.provisioning.license.service.LicenseEntryServiceUtil</code> to access the license entry remote service.
	 */

}