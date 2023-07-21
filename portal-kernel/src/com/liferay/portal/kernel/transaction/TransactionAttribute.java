/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.transaction;

/**
 * @author Shuyang Zhou
 */
public interface TransactionAttribute {

	public Isolation getIsolation();

	public Propagation getPropagation();

	public boolean isReadOnly();

	public static class Builder {

		public TransactionAttribute build() {
			return new DefaultTransactionAttribute(this);
		}

		public Builder setIsolation(Isolation isolation) {
			_isolation = isolation;

			return this;
		}

		public Builder setPropagation(Propagation propagation) {
			_propagation = propagation;

			return this;
		}

		public Builder setReadOnly(boolean readOnly) {
			_readOnly = readOnly;

			return this;
		}

		private Isolation _isolation = Isolation.DEFAULT;
		private Propagation _propagation = Propagation.REQUIRED;
		private boolean _readOnly;

	}

	public static class DefaultTransactionAttribute
		implements TransactionAttribute {

		@Override
		public Isolation getIsolation() {
			return _isolation;
		}

		@Override
		public Propagation getPropagation() {
			return _propagation;
		}

		@Override
		public boolean isReadOnly() {
			return _readOnly;
		}

		private DefaultTransactionAttribute(Builder builder) {
			_isolation = builder._isolation;
			_propagation = builder._propagation;
			_readOnly = builder._readOnly;
		}

		private final Isolation _isolation;
		private final Propagation _propagation;
		private final boolean _readOnly;

	}

}