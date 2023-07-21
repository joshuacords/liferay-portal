/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

/**
 * @author Marco Leo
 */
@ProviderType
public interface DDMFormValuesHelper {

	/**
	 * @deprecated As of Mueller (7.2.x), without direct replacement, as this
	 * processing is no longer necessary
	 */
	@Deprecated
	public String cleanDDMFormValuesJSON(String json) throws PortalException;

	public DDMFormValues deserialize(
			DDMForm ddmForm, String json, Locale locale)
		throws PortalException;

	/**
	 * @param json1
	 * @param json2
	 * @return
	 *
	 * @throws PortalException
	 * @deprecated As of Athanasius (7.3.x), use {@link JsonHelper#equals(String, String)}
	 */
	@Deprecated
	public boolean equals(String json1, String json2) throws PortalException;

	public String serialize(DDMFormValues ddmFormValues);

}