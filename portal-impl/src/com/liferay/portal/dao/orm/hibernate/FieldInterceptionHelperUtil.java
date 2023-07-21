/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.petra.concurrent.ConcurrentReferenceKeyHashMap;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Field;

import org.hibernate.intercept.FieldInterceptionHelper;

/**
 * This utility class collaborates with hibernate FieldInterceptionHelper patch
 * from LPS-52218 to ensure the _instrumentedCache does not cause ClassLoader
 * leaking for plugins
 *
 * @author Shuyang Zhou
 */
public class FieldInterceptionHelperUtil {

	public static void initialize() {
		try {
			Field instrumentedCacheField = ReflectionUtil.getDeclaredField(
				FieldInterceptionHelper.class, "_instrumentedCache");

			instrumentedCacheField.set(
				null,
				new ConcurrentReferenceKeyHashMap<Class<?>, Boolean>(
					FinalizeManager.WEAK_REFERENCE_FACTORY));
		}
		catch (NoSuchFieldException noSuchFieldException) {
			_log.error(
				"Missing Hibernate FieldInterceptionHelper patch from " +
					"LPS-52218",
				noSuchFieldException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FieldInterceptionHelperUtil.class);

}