/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Iván Zaera
 */
public class ConvertProcessUtil {

	public static Collection<ConvertProcess> getConvertProcesses() {
		try {
			Registry registry = RegistryUtil.getRegistry();

			return registry.getServices(ConvertProcess.class, null);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	public static Collection<ConvertProcess> getEnabledConvertProcesses() {
		Collection<ConvertProcess> convertProcesses = new ArrayList<>(
			getConvertProcesses());

		Iterator<ConvertProcess> iterator = convertProcesses.iterator();

		while (iterator.hasNext()) {
			ConvertProcess convertProcess = iterator.next();

			if (!convertProcess.isEnabled()) {
				iterator.remove();
			}
		}

		return convertProcesses;
	}

}