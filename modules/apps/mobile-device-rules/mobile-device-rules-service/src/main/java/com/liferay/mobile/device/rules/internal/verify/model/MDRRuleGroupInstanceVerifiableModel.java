/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.verify.model;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceModelImpl;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;

/**
 * @author Tomas Polesovsky
 */
public class MDRRuleGroupInstanceVerifiableModel
	implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return MDRRuleGroupInstance.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "ruleGroupInstanceId";
	}

	@Override
	public String getTableName() {
		return MDRRuleGroupInstanceModelImpl.TABLE_NAME;
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}