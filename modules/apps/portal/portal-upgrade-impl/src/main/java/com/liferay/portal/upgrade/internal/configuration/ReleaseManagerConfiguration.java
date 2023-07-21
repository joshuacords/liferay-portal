/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Carlos Sierra Andrés
 */
@ExtendedObjectClassDefinition(category = "upgrades")
@Meta.OCD(
	id = "com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration",
	localization = "content/Language",
	name = "release-manager-configuration-name"
)
public interface ReleaseManagerConfiguration {

	@Meta.AD(deflt = "true", name = "auto-upgrade", required = false)
	public boolean autoUpgrade();

}