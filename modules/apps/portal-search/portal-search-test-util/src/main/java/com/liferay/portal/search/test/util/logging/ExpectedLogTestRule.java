/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.logging;

import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author André de Oliveira
 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
 *             ExpectedLogMethodTestRule}
 */
@Deprecated
public class ExpectedLogTestRule implements TestRule {

	public static ExpectedLogTestRule none() {
		return new ExpectedLogTestRule(null, null);
	}

	public static ExpectedLogTestRule with(String name, Level level) {
		return new ExpectedLogTestRule(name, level);
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				configure(_name, _level);

				try {
					base.evaluate();

					verify();
				}
				finally {
					closeCaptureHandler();
				}
			}

		};
	}

	public void configure(Class<?> clazz, Level level) {
		configure(clazz.getName(), level);
	}

	public void configure(String name, Level level) {
		if ((name == null) || (level == null)) {
			return;
		}

		closeCaptureHandler();

		openCaptureHandler(name, level);
	}

	public void expect(Matcher<?> matcher) {
		_matcherBuilder.add(matcher);
	}

	public void expectMessage(Matcher<String> matcher) {
		expect(LogOutputMatcher.hasMessage(matcher));
	}

	public void expectMessage(String substring) {
		expectMessage(CoreMatchers.containsString(substring));
	}

	public void verify() {
		if (_captureHandler == null) {
			return;
		}

		if (!_matcherBuilder.isAnythingExpected()) {
			return;
		}

		Assert.assertThat(
			_captureHandler.getLogRecords(), _matcherBuilder.build());
	}

	protected void closeCaptureHandler() {
		if (_captureHandler == null) {
			return;
		}

		_captureHandler.close();

		_captureHandler = null;
	}

	protected void openCaptureHandler(String name, Level level) {
		_captureHandler = JDKLoggerTestUtil.configureJDKLogger(name, level);
	}

	protected static class LogOutputMatcher<T extends List<LogRecord>>
		extends TypeSafeMatcher<T> {

		@Factory
		public static <T extends List<LogRecord>> Matcher<T> hasMessage(
			Matcher<String> matcher) {

			return new LogOutputMatcher<>(matcher);
		}

		public LogOutputMatcher(Matcher<String> matcher) {
			this.matcher = matcher;
		}

		@Override
		public void describeTo(org.hamcrest.Description description) {
			description.appendText("log output with message ");

			description.appendDescriptionOf(matcher);
		}

		@Override
		protected void describeMismatchSafely(
			T logRecords, org.hamcrest.Description description) {

			description.appendText("log output ");

			matcher.describeMismatch(toString(logRecords), description);
		}

		@Override
		protected boolean matchesSafely(T logRecords) {
			if (matcher.matches(toString(logRecords))) {
				return true;
			}

			return false;
		}

		protected String toString(T logRecords) {
			Stream<LogRecord> stream = logRecords.stream();

			return stream.map(
				LogRecord::getMessage
			).collect(
				Collectors.joining()
			);
		}

		protected final Matcher<String> matcher;

	}

	protected class MatcherBuilder<T> {

		protected void add(Matcher<T> matcher) {
			matchers.add(matcher);
		}

		protected Matcher<?> build() {
			if (matchers.size() == 1) {
				return matchers.get(0);
			}

			return CoreMatchers.allOf(new ArrayList<>((List)matchers));
		}

		protected boolean isAnythingExpected() {
			if (matchers.isEmpty()) {
				return false;
			}

			return true;
		}

		protected final List<Matcher<T>> matchers = new ArrayList<>();

	}

	private ExpectedLogTestRule(String name, Level level) {
		_name = name;
		_level = level;
	}

	private CaptureHandler _captureHandler;
	private final Level _level;
	private final MatcherBuilder _matcherBuilder = new MatcherBuilder();
	private final String _name;

}