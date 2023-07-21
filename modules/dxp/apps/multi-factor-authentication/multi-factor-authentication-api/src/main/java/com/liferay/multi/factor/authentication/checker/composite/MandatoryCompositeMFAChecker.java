/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.composite;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.checker.visitor.MFACheckerVisitor;

import java.util.List;

/**
 * @author Carlos Sierra Andrés
 */
public class MandatoryCompositeMFAChecker extends BaseCompositeMFAChecker {

	public MandatoryCompositeMFAChecker(List<MFAChecker> mfaCheckers) {
		super(mfaCheckers);
	}

	@Override
	public <T> T accept(MFACheckerVisitor<T> mfaCheckerVisitor) {
		return mfaCheckerVisitor.visit(this);
	}

}