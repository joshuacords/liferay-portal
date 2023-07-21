/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.metatype.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

/**
 * @author     Drew Brokke
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ConfigurationScopedPidUtil {

	public static String buildConfigurationScopedPid(
		String basePid, ExtendedObjectClassDefinition.Scope scope,
		String scopePrimKey) {

		Objects.requireNonNull(
			basePid,
			"The base PID must not be null. A scoped PID must correspond to " +
				"a configuration PID.");

		if ((scope == null) ||
			scope.equals(ExtendedObjectClassDefinition.Scope.SYSTEM)) {

			return basePid;
		}

		Objects.requireNonNull(
			scopePrimKey,
			"The scope primary key must not be null. A scoped PID must " +
				"correspond to a primary key for its scope.");

		return StringBundler.concat(
			basePid, scope.getDelimiterString(), scopePrimKey);
	}

	public static String getBasePid(String scopedPid) {
		if (Validator.isNull(scopedPid)) {
			return null;
		}

		for (ExtendedObjectClassDefinition.Scope scope :
				ExtendedObjectClassDefinition.Scope.values()) {

			String separator = scope.getDelimiterString();

			if (scopedPid.contains(separator)) {
				return StringUtil.split(scopedPid, separator)[0];
			}
		}

		return scopedPid;
	}

	public static ExtendedObjectClassDefinition.Scope getScope(
		String scopedPid) {

		if (Validator.isNull(scopedPid)) {
			return null;
		}

		for (ExtendedObjectClassDefinition.Scope scope :
				ExtendedObjectClassDefinition.Scope.values()) {

			if (scopedPid.contains(scope.getDelimiterString())) {
				return scope;
			}
		}

		return ExtendedObjectClassDefinition.Scope.SYSTEM;
	}

	public static String getScopePrimKey(String scopedPid) {
		if (Validator.isNull(scopedPid)) {
			return null;
		}

		for (ExtendedObjectClassDefinition.Scope scope :
				ExtendedObjectClassDefinition.Scope.values()) {

			String separator = scope.getDelimiterString();

			if (scopedPid.contains(separator)) {
				return StringUtil.split(scopedPid, separator)[1];
			}
		}

		return null;
	}

}