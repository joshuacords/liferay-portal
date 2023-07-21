/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author Matthew Tambara
 */
public abstract class AbstractTestRule<C, M> implements TestRule {

	@Override
	public Statement apply(Statement statement, Description description) {
		if (description.getMethodName() != null) {
			return createMethodStatement(statement, description);
		}

		return createClassStatement(statement, description);
	}

	protected abstract void afterClass(Description description, C c)
		throws Throwable;

	protected abstract void afterMethod(
			Description description, M m, Object target)
		throws Throwable;

	protected abstract C beforeClass(Description description) throws Throwable;

	protected abstract M beforeMethod(Description description, Object target)
		throws Throwable;

	protected Statement createClassStatement(
		Statement statement, Description description) {

		return new StatementWrapper(statement) {

			@Override
			public void evaluate() throws Throwable {
				C c = beforeClass(description);

				Throwable throwable = null;

				try {
					statement.evaluate();
				}
				catch (Throwable t) {
					throwable = t;
				}
				finally {
					try {
						afterClass(description, c);
					}
					catch (Throwable t) {
						if (throwable != null) {
							t.addSuppressed(throwable);
						}

						throwable = t;
					}
				}

				if (throwable != null) {
					throw throwable;
				}
			}

		};
	}

	protected Statement createMethodStatement(
		Statement statement, Description description) {

		return new StatementWrapper(statement) {

			@Override
			public void evaluate() throws Throwable {
				Object target = inspectTarget(statement);

				M m = beforeMethod(description, target);

				Throwable throwable = null;

				try {
					statement.evaluate();
				}
				catch (Throwable t) {
					throwable = t;
				}
				finally {
					try {
						afterMethod(description, m, target);
					}
					catch (Throwable t) {
						if (throwable != null) {
							t.addSuppressed(throwable);
						}

						throwable = t;
					}
				}

				if (throwable != null) {
					throw throwable;
				}
			}

		};
	}

}