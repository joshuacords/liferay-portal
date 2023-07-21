/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.model.impl;

import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.mobile.device.rules.service.MDRRuleLocalServiceUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupImpl extends MDRRuleGroupBaseImpl {

	@Override
	public List<MDRRule> getRules() {
		if (getRuleGroupId() > 0) {
			return MDRRuleLocalServiceUtil.getRules(getRuleGroupId());
		}

		return Collections.emptyList();
	}

}