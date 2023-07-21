/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.search.util;

/**
 * @author Marcela Cunha
 */
public interface DDLRecordBatchReindexer {

	public void reindex(long ddlRecordId, long companyId);

}