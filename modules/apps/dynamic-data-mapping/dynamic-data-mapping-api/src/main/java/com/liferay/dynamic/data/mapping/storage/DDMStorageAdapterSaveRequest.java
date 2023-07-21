/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.storage;

/**
 * @author Leonardo Barros
 */
public final class DDMStorageAdapterSaveRequest {

	public String getClassName() {
		return _className;
	}

	public DDMFormValues getDDMFormValues() {
		return _ddmFormValues;
	}

	public long getPrimaryKey() {
		return _primaryKey;
	}

	public long getScopeGroupId() {
		return _scopeGroupId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isInsert() {
		if (_primaryKey == 0) {
			return true;
		}

		return false;
	}

	public static class Builder {

		public static Builder newBuilder(
			long userId, long scopeGroupId, DDMFormValues ddmFormValues) {

			return new Builder(userId, scopeGroupId, ddmFormValues);
		}

		public DDMStorageAdapterSaveRequest build() {
			return _ddmStorageAdapterSaveRequest;
		}

		public Builder withClassName(String className) {
			_ddmStorageAdapterSaveRequest._className = className;

			return this;
		}

		public Builder withPrimaryKey(long primaryKey) {
			_ddmStorageAdapterSaveRequest._primaryKey = primaryKey;

			return this;
		}

		public Builder withUuid(String uuid) {
			_ddmStorageAdapterSaveRequest._uuid = uuid;

			return this;
		}

		private Builder(
			long userId, long scopeGroupId, DDMFormValues ddmFormValues) {

			_ddmStorageAdapterSaveRequest._userId = userId;
			_ddmStorageAdapterSaveRequest._scopeGroupId = scopeGroupId;
			_ddmStorageAdapterSaveRequest._ddmFormValues = ddmFormValues;
		}

		private final DDMStorageAdapterSaveRequest
			_ddmStorageAdapterSaveRequest = new DDMStorageAdapterSaveRequest();

	}

	private DDMStorageAdapterSaveRequest() {
	}

	private String _className;
	private DDMFormValues _ddmFormValues;
	private long _primaryKey;
	private long _scopeGroupId;
	private long _userId;
	private String _uuid;

}