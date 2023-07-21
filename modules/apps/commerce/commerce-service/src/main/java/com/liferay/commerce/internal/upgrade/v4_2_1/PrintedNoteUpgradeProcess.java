/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_2_1;

import com.liferay.commerce.model.impl.CommerceOrderItemModelImpl;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Marco Leo
 */
public class PrintedNoteUpgradeProcess extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		alter(
			CommerceOrderModelImpl.class,
			new AlterColumnType("printedNote", "STRING null"));
		alter(
			CommerceOrderItemModelImpl.class,
			new AlterColumnType("printedNote", "STRING null"));
	}

}