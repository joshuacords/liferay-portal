/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.mobile.device.rules.kernel.MDRRuleGroupInstance;

import java.util.Date;
import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public class MDRRuleGroupInstanceImpl implements MDRRuleGroupInstance {

	public MDRRuleGroupInstanceImpl(
		com.liferay.mobile.device.rules.model.MDRRuleGroupInstance
			mdrRuleGroupInstance) {

		_mdrRuleGroupInstance = mdrRuleGroupInstance;
	}

	@Override
	public Object clone() {
		return new MDRRuleGroupInstanceImpl(
			(com.liferay.mobile.device.rules.model.MDRRuleGroupInstance)
				_mdrRuleGroupInstance.clone());
	}

	@Override
	public long getCompanyId() {
		return _mdrRuleGroupInstance.getCompanyId();
	}

	@Override
	public Date getCreateDate() {
		return _mdrRuleGroupInstance.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mdrRuleGroupInstance.getExpandoBridge();
	}

	@Override
	public long getGroupId() {
		return _mdrRuleGroupInstance.getGroupId();
	}

	@Override
	public Date getLastPublishDate() {
		return _mdrRuleGroupInstance.getLastPublishDate();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		return _mdrRuleGroupInstance.getModelAttributes();
	}

	@Override
	public Date getModifiedDate() {
		return _mdrRuleGroupInstance.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _mdrRuleGroupInstance.getPrimaryKey();
	}

	@Override
	public int getPriority() {
		return _mdrRuleGroupInstance.getPriority();
	}

	@Override
	public long getRuleGroupId() {
		return _mdrRuleGroupInstance.getRuleGroupId();
	}

	@Override
	public long getRuleGroupInstanceId() {
		return _mdrRuleGroupInstance.getRuleGroupInstanceId();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _mdrRuleGroupInstance.getStagedModelType();
	}

	@Override
	public long getUserId() {
		return _mdrRuleGroupInstance.getUserId();
	}

	@Override
	public String getUserName() {
		return _mdrRuleGroupInstance.getUserName();
	}

	@Override
	public String getUserUuid() {
		return _mdrRuleGroupInstance.getUserUuid();
	}

	@Override
	public String getUuid() {
		return _mdrRuleGroupInstance.getUuid();
	}

	private final com.liferay.mobile.device.rules.model.MDRRuleGroupInstance
		_mdrRuleGroupInstance;

}