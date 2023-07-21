/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.util;

import com.liferay.petra.string.StringPool;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Drew Brokke
 */
public class CSSClassNames {

	public static Builder builder(String... cssClassNames) {
		Builder builder = new Builder();

		builder.add(cssClassNames);

		return builder;
	}

	public static class Builder {

		public Builder add(String cssClassName) {
			return _add(cssClassName, true);
		}

		public Builder add(String... cssClassNames) {
			for (String cssClassName : cssClassNames) {
				_add(cssClassName, true);
			}

			return this;
		}

		public Builder add(String cssClassName, boolean condition) {
			return _add(cssClassName, condition);
		}

		public String build() {
			return _build();
		}

		private Builder() {
		}

		private Builder _add(String cssClassName, boolean condition) {
			if (condition) {
				_streamBuilder.accept(cssClassName);
			}

			return this;
		}

		private String _build() {
			return _streamBuilder.build(
			).distinct(
			).sorted(
			).collect(
				Collectors.joining(StringPool.SPACE)
			);
		}

		private final Stream.Builder<String> _streamBuilder = Stream.builder();

	}

	private CSSClassNames() {
	}

}