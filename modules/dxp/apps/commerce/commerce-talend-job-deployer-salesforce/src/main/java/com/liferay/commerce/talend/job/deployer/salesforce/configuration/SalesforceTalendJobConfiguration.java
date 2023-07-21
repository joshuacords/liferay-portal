/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.talend.job.deployer.salesforce.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Danny Situ
 */
@ExtendedObjectClassDefinition(
	category = "salesforce-talend-job-deployer",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.talend.job.deployer.salesforce.configuration.SalesforceTalendJobConfiguration",
	localization = "content/Language",
	name = "salesforce-talend-job-deployer-configuration-name"
)
public interface SalesforceTalendJobConfiguration {

	@Meta.AD(deflt = "5000", name = "cache-size", required = false)
	public int cacheSize();

	@Meta.AD(
		deflt = "META-INF/", name = "salesforce-talend-job-file-path",
		required = false
	)
	public String salesforceTalendJobFilePath();

}