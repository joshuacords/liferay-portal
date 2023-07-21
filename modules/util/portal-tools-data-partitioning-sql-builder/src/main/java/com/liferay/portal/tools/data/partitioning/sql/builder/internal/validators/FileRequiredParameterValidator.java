/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators;

import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * @author Manuel de la Peña
 */
public class FileRequiredParameterValidator extends RequiredParameterValidator {

	@Override
	public void validate(String name, String value) throws ParameterException {
		super.validate(name, value);

		File file = new File(value);

		if (!file.exists()) {
			throw new ParameterException(
				"Parameter " + name + " does not reference an existing file");
		}
	}

}