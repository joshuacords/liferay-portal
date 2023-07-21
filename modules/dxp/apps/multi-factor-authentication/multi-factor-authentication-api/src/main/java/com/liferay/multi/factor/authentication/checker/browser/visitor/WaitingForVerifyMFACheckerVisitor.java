/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.browser.visitor;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.checker.browser.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.OptionalCompositeMFAChecker;
import com.liferay.multi.factor.authentication.checker.setup.visitor.IsUserSetupCompleteMFACheckerVisitor;
import com.liferay.multi.factor.authentication.checker.setup.visitor.SupportsSetupMFACheckerVisitor;
import com.liferay.multi.factor.authentication.checker.visitor.MFACheckerVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Sierra Andrés
 */
public class WaitingForVerifyMFACheckerVisitor
	implements MFACheckerVisitor<List<BrowserMFAChecker>> {

	public WaitingForVerifyMFACheckerVisitor(
		HttpServletRequest httpServletRequest, long userId) {

		_isBrowserVerifiedVisitor = new IsBrowserVerifiedMFACheckerVisitor(
			httpServletRequest, userId);
		_isUserSetupCompleteVisitor = new IsUserSetupCompleteMFACheckerVisitor(
			userId);
	}

	@Override
	public List<BrowserMFAChecker> visit(
		MandatoryCompositeMFAChecker mandatoryCompositeMFAChecker) {

		List<BrowserMFAChecker> mfaCheckers = new ArrayList<>();

		for (MFAChecker mfaChecker :
				mandatoryCompositeMFAChecker.getMFACheckers()) {

			if (!mfaCheckers.isEmpty()) {
				break;
			}

			if (mfaChecker.accept(_supportsSetupVisitor) &&
				!mfaChecker.accept(_isUserSetupCompleteVisitor)) {

				throw new IllegalStateException("Setup was not completed");
			}

			mfaCheckers.addAll(mfaChecker.accept(this));
		}

		return mfaCheckers;
	}

	@Override
	public List<BrowserMFAChecker> visit(MFAChecker mfaChecker) {
		if (!_supportsBrowserVisitor.visit(mfaChecker)) {
			throw new RuntimeException(
				mfaChecker.getClass() + " must implement BrowserMFAChecker");
		}

		if (_isBrowserVerifiedVisitor.visit(mfaChecker)) {
			return Collections.emptyList();
		}

		return Collections.singletonList((BrowserMFAChecker)mfaChecker);
	}

	@Override
	public List<BrowserMFAChecker> visit(
		OptionalCompositeMFAChecker optionalCompositeMFAChecker) {

		List<BrowserMFAChecker> mfaCheckers = new ArrayList<>();

		for (MFAChecker mfaChecker :
				optionalCompositeMFAChecker.getMFACheckers()) {

			if (mfaChecker.accept(_supportsSetupVisitor) &&
				!mfaChecker.accept(_isUserSetupCompleteVisitor)) {

				continue;
			}

			mfaCheckers.addAll(mfaChecker.accept(this));
		}

		return mfaCheckers;
	}

	private static final SupportsBrowserMFACheckerVisitor
		_supportsBrowserVisitor = new SupportsBrowserMFACheckerVisitor();
	private static final SupportsSetupMFACheckerVisitor _supportsSetupVisitor =
		new SupportsSetupMFACheckerVisitor();

	private final IsBrowserVerifiedMFACheckerVisitor _isBrowserVerifiedVisitor;
	private final IsUserSetupCompleteMFACheckerVisitor
		_isUserSetupCompleteVisitor;

}