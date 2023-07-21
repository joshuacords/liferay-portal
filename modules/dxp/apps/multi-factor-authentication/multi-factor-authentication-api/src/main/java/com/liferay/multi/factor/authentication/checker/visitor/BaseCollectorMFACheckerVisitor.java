/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.visitor;

import com.liferay.multi.factor.authentication.checker.MFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.MandatoryCompositeMFAChecker;
import com.liferay.multi.factor.authentication.checker.composite.OptionalCompositeMFAChecker;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Carlos Sierra Andrés
 */
@ProviderType
public abstract class BaseCollectorMFACheckerVisitor
	implements MFACheckerVisitor<List<MFAChecker>> {

	public BaseCollectorMFACheckerVisitor(Predicate<MFAChecker> predicate) {
		_predicate = predicate;
	}

	@Override
	public List<MFAChecker> visit(
		MandatoryCompositeMFAChecker mandatoryCompositeMFAChecker) {

		return _flatMapMFACheckers(
			mandatoryCompositeMFAChecker.getMFACheckers());
	}

	@Override
	public List<MFAChecker> visit(MFAChecker mfaChecker) {
		if (_predicate.test(mfaChecker)) {
			return Collections.singletonList(mfaChecker);
		}

		return Collections.emptyList();
	}

	@Override
	public List<MFAChecker> visit(
		OptionalCompositeMFAChecker optionalMFACheckerMFACheckers) {

		return _flatMapMFACheckers(
			optionalMFACheckerMFACheckers.getMFACheckers());
	}

	private List<MFAChecker> _flatMapMFACheckers(List<MFAChecker> mfaCheckers) {
		Stream<MFAChecker> stream = mfaCheckers.stream();

		return stream.flatMap(
			mfaChecker -> mfaChecker.accept(
				this
			).stream()
		).collect(
			Collectors.toList()
		);
	}

	private final Predicate<MFAChecker> _predicate;

}