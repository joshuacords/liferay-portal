/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker;

import com.liferay.multi.factor.authentication.checker.visitor.MFACheckerVisitor;

import java.util.Locale;

/**
 * @author Tomas Polesovsky
 */
public interface MFAChecker {

	public default <T> T accept(MFACheckerVisitor<T> mfaCheckerVisitor) {
		return mfaCheckerVisitor.visit(this);
	}

	public default String getLabel(Locale locale) {
		return getName();
	}

	public String getName();

	public boolean isEnabled();

}