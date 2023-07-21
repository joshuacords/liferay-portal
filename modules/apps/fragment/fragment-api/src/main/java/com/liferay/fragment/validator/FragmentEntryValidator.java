/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.validator;

import com.liferay.fragment.exception.FragmentEntryConfigurationException;

/**
 * @author Rubén Pulido
 */
public interface FragmentEntryValidator {

	public void validateConfiguration(String configuration)
		throws FragmentEntryConfigurationException;

}