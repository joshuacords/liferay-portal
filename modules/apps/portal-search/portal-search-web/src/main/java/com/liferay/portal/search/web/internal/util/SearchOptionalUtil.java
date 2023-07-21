/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class SearchOptionalUtil {

	public static <T> void copy(Supplier<Optional<T>> from, Consumer<T> to) {
		Optional<T> optional = from.get();

		optional.ifPresent(to);
	}

	public static <T> T findFirstPresent(
		Stream<Optional<T>> stream, T defaultValue) {

		return stream.filter(
			Optional::isPresent
		).map(
			Optional::get
		).findFirst(
		).orElse(
			defaultValue
		);
	}

}