/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kyle Bischof
 */
@ExtendedObjectClassDefinition(category = "provisioning")
@Meta.OCD(
	id = "com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration",
	localization = "content/Language", name = "provisioning-configuration-name"
)
public interface ProvisioningWebConfiguration {

	@Meta.AD(
		deflt = "5.1|5.1 SP1|5.1 SP2|5.1 SP3|5.1 SP4|5.1 SP5|5.2|5.2 SP1|5.2 SP2|5.2 SP3|5.2 SP4|5.2 SP5|6.0|6.0 SP1|6.0 SP2",
		name = "add-license-hidden-versions", required = false
	)
	public String[] addLicenseHiddenVersions();

}