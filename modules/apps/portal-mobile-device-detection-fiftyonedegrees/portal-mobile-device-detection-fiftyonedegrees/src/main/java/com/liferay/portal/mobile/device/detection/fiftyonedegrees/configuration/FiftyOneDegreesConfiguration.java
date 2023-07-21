/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.mobile.device.detection.fiftyonedegrees.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@ExtendedObjectClassDefinition(category = "devices")
@Meta.OCD(
	id = "com.liferay.portal.mobile.device.detection.fiftyonedegrees.configuration.FiftyOneDegreesConfiguration",
	localization = "content/Language",
	name = "51degrees-device-detection-configuration-name"
)
public interface FiftyOneDegreesConfiguration {

	@Meta.AD(deflt = "5000", name = "cache-size", required = false)
	public int cacheSize();

	@Meta.AD(
		deflt = "META-INF/51Degrees-LiteV3.2.dat",
		name = "fifty-one-degrees-data-file-name", required = false
	)
	public String fiftyOneDegreesDataFileName();

}