/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.kernel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import java.util.Date;
import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public interface MDRRuleGroupInstance {

	public Object clone();

	public long getCompanyId();

	public Date getCreateDate();

	public ExpandoBridge getExpandoBridge();

	public long getGroupId();

	public Date getLastPublishDate();

	public Map<String, Object> getModelAttributes();

	public Date getModifiedDate();

	public long getPrimaryKey();

	public int getPriority();

	public long getRuleGroupId();

	public long getRuleGroupInstanceId();

	public StagedModelType getStagedModelType();

	public long getUserId();

	public String getUserName();

	public String getUserUuid();

	public String getUuid();

}