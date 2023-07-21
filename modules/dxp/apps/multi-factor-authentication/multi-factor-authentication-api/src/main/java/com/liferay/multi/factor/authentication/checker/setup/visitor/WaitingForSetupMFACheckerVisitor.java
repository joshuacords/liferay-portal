/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.setup.visitor;

import com.liferay.multi.factor.authentication.checker.visitor.BaseCollectorMFACheckerVisitor;

/**
 * @author Carlos Sierra Andrés
 */
public class WaitingForSetupMFACheckerVisitor
	extends BaseCollectorMFACheckerVisitor {

	public WaitingForSetupMFACheckerVisitor(
		boolean onlyForcedSetup, long userId) {

		super(
			mfaChecker -> {
				if (!mfaChecker.accept(_supportsSetupMFACheckerVisitor)) {
					return false;
				}

				if (mfaChecker.accept(
						new IsUserSetupCompleteMFACheckerVisitor(userId))) {

					return false;
				}

				if (onlyForcedSetup &&
					!mfaChecker.accept(
						new ForceUserSetupMFACheckerVisitor(userId))) {

					return false;
				}

				return true;
			});
	}

	private static final SupportsSetupMFACheckerVisitor
		_supportsSetupMFACheckerVisitor = new SupportsSetupMFACheckerVisitor();

}