/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import java.util.Date;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public interface WorkflowLog {

	public static final int TASK_ASSIGN = 1;

	public static final int TASK_COMPLETION = 3;

	public static final int TASK_UPDATE = 2;

	public static final int TRANSITION = 0;

	public long getAuditUserId();

	public String getComment();

	public Date getCreateDate();

	public long getPreviousRoleId();

	public String getPreviousState();

	public long getPreviousUserId();

	public long getRoleId();

	public String getState();

	public int getType();

	public long getUserId();

	public long getWorkflowLogId();

	public long getWorkflowTaskId();

}