/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v1_1_0;

import com.liferay.commerce.model.impl.CommerceOrderNoteModelImpl;

/**
 * @author Marco Leo
 */
public class CommerceOrderNoteUpgradeProcess
	extends BaseCommerceOrderUpgradeProcess {

	public CommerceOrderNoteUpgradeProcess() {
		super(
			CommerceOrderNoteModelImpl.class,
			CommerceOrderNoteModelImpl.TABLE_NAME, "externalReferenceCode",
			"VARCHAR(75)");
	}

}