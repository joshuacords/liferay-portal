/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * @author Manuel de la Peña
 */
public class RequiredParameterValidator implements IParameterValidator {

	@Override
	public void validate(String name, String value) throws ParameterException {
		if (value == null) {
			throw new ParameterException("Parameter " + name + " is null");
		}

		value = value.trim();

		if (value.length() == 0) {
			throw new ParameterException("Parameter " + name + " is empty");
		}
	}

}