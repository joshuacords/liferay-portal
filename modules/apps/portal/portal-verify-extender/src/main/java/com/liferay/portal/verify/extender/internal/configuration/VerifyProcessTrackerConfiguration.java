/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.extender.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Miguel Pastor
 */
@ExtendedObjectClassDefinition(category = "upgrades")
@Meta.OCD(
	id = "com.liferay.portal.verify.extender.internal.configuration.VerifyProcessTrackerConfiguration",
	localization = "content/Language",
	name = "verify-process-configuration-name"
)
public interface VerifyProcessTrackerConfiguration {

	@Meta.AD(deflt = "true", name = "auto-verify", required = false)
	public boolean autoVerify();

	@Meta.AD(deflt = "true", name = "index-read-only", required = false)
	public boolean indexReadOnly();

}