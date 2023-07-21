/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.setup.visitor;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.checker.setup.MFACheckerSetup;
import com.liferay.multi.factor.authentication.checker.visitor.BaseMFACheckerVisitor;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Carlos Sierra Andrés
 */
public class ForceUserSetupMFACheckerVisitor extends BaseMFACheckerVisitor {

	public ForceUserSetupMFACheckerVisitor(long userId) {
		super(
			MFACheckerSetup.class,
			mfaCheckerSetup -> mfaCheckerSetup.isForceUserSetup(userId));
	}

	@Override
	public Boolean visit(
		MandatoryCompositeMFAChecker mandatoryCompositeMFAChecker) {

		List<MFAChecker> mfaCheckers =
			mandatoryCompositeMFAChecker.getMFACheckers();

		Stream<MFAChecker> stream = mfaCheckers.stream();

		return stream.anyMatch(mfaChecker -> mfaChecker.accept(this));
	}

}