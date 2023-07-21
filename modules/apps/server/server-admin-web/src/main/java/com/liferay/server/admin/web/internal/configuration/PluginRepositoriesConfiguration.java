/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.admin.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.scheduler.TimeUnit;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(category = "infrastructure")
@Meta.OCD(
	id = "com.liferay.server.admin.web.internal.configuration.PluginRepositoriesConfiguration",
	localization = "content/Language",
	name = "plugin-repositories-configuration-name"
)
public interface PluginRepositoriesConfiguration {

	@Meta.AD(deflt = "true", name = "enabled", required = false)
	public boolean enabled();

	@Meta.AD(deflt = "1", name = "interval", required = false)
	public int interval();

	@Meta.AD(deflt = "DAY", name = "time-unit", required = false)
	public TimeUnit timeUnit();

}