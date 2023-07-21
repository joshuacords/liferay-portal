/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.display.field;

import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jürgen Kappler
 */
@ProviderType
public interface ClassTypesInfoDisplayFieldProvider {

	public List<InfoDisplayField> getClassTypeInfoDisplayFields(
			ClassType classType, Locale locale)
		throws PortalException;

	public List<ClassType> getClassTypes(
			long groupId, ClassTypeReader classTypeReader, Locale locale)
		throws PortalException;

}