/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.visitor;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.OptionalCompositeMFAChecker;

/**
 * @author Carlos Sierra Andrés
 */
public interface MFACheckerVisitor<T> {

	public T visit(MandatoryCompositeMFAChecker mandatoryCompositeMFAChecker);

	public T visit(MFAChecker mfaChecker);

	public T visit(OptionalCompositeMFAChecker optionalCompositeMFAChecker);

}