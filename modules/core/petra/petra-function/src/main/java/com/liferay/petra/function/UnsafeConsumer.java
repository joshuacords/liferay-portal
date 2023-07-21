/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.function;

import java.util.Collection;

/**
 * @author Shuyang Zhou
 */
@FunctionalInterface
public interface UnsafeConsumer<E, T extends Throwable> {

	public static <E> void accept(
			Collection<E> collection,
			UnsafeConsumer<E, ? super Throwable> unsafeConsumer)
		throws Throwable {

		accept(collection, unsafeConsumer, Throwable.class);
	}

	public static <E, T extends Throwable> void accept(
			Collection<E> collection, UnsafeConsumer<E, T> unsafeConsumer,
			Class<? extends T> throwableClass)
		throws T {

		T throwable = null;

		for (E e : collection) {
			try {
				unsafeConsumer.accept(e);
			}
			catch (Throwable t) {
				if (!throwableClass.isInstance(t)) {

					// Unexpected exception stops the loop and suppresses
					// previous expected exceptions

					if (throwable != null) {
						t.addSuppressed(throwable);
					}

					throw t;
				}

				if (throwable == null) {
					throwable = throwableClass.cast(t);
				}
				else {
					throwable.addSuppressed(t);
				}
			}
		}

		if (throwable != null) {
			throw throwable;
		}
	}

	public void accept(E e) throws T;

}