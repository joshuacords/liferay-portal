/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model.version;

/**
 * @author Preston Crary
 */
public interface VersionedModel<V extends VersionModel> {

	public long getHeadId();

	public long getPrimaryKey();

	/**
	 * @deprecated As of Judson (7.1.x), replaced by the inverse of {@link
	 *             #isHead()}
	 */
	@Deprecated
	public default boolean isDraft() {
		return !isHead();
	}

	public default boolean isHead() {
		if (getHeadId() >= 0) {
			return false;
		}

		return true;
	}

	public void populateVersionModel(V versionModel);

	public void setHeadId(long headId);

	public void setPrimaryKey(long primaryKey);

}