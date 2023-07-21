/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.setup.visitor;

import com.liferay.multi.factor.authentication.checker.setup.MFACheckerSetup;
import com.liferay.multi.factor.authentication.checker.visitor.BaseMFACheckerVisitor;

/**
 * @author Carlos Sierra Andrés
 */
public class IsUserSetupCompleteMFACheckerVisitor
	extends BaseMFACheckerVisitor {

	public IsUserSetupCompleteMFACheckerVisitor(long userId) {
		super(
			MFACheckerSetup.class,
			mfaCheckerSetup -> mfaCheckerSetup.isUserSetupComplete(userId));
	}

}