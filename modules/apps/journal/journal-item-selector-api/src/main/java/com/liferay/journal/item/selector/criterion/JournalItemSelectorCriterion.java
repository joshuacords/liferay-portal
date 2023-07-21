/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.item.selector.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;

/**
 * @author Eduardo García
 * @author Roberto Díaz
 */
public class JournalItemSelectorCriterion extends BaseItemSelectorCriterion {

	public JournalItemSelectorCriterion() {
	}

	public JournalItemSelectorCriterion(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
	}

	public JournalItemSelectorCriterion(long resourcePrimKey, long folderId) {
		_resourcePrimKey = resourcePrimKey;
		_folderId = folderId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
	}

	private long _folderId;
	private long _resourcePrimKey;

}