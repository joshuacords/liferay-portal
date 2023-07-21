/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.constants;

/**
 * @author Kyle Bischof
 */
public class WorkflowConstants
	extends com.liferay.portal.kernel.workflow.WorkflowConstants {

	public static final String LABEL_ACTIVE = "Active";

	public static final String LABEL_APPROVED = "Approved";

	public static final String LABEL_CANCELLED = "Cancelled";

	public static final String LABEL_CLOSED = "Closed";

	public static final String LABEL_INACTIVE = "Inactive";

	public static final int STATUS_CANCELLED = 600;

	public static final int STATUS_CLOSED = 400;

	public static int getLabelStatus(String label) {
		if (label.equals(LABEL_ACTIVE) || label.equals(LABEL_APPROVED)) {
			return STATUS_APPROVED;
		}
		else if (label.equals(LABEL_CANCELLED)) {
			return STATUS_CANCELLED;
		}
		else if (label.equals(LABEL_CLOSED)) {
			return STATUS_CLOSED;
		}
		else if (label.equals(LABEL_INACTIVE)) {
			return STATUS_INACTIVE;
		}

		return com.liferay.portal.kernel.workflow.WorkflowConstants.
			getLabelStatus(label);
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_APPROVED) {
			return LABEL_APPROVED;
		}
		else if (status == STATUS_CANCELLED) {
			return LABEL_CANCELLED;
		}
		else if (status == STATUS_CLOSED) {
			return LABEL_CLOSED;
		}
		else if (status == STATUS_INACTIVE) {
			return LABEL_INACTIVE;
		}

		return com.liferay.portal.kernel.workflow.WorkflowConstants.
			getStatusLabel(status);
	}

}