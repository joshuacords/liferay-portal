/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.customer.web.service;

import com.liferay.osb.provisioning.customer.model.AccountEntry;

/**
 * @author Amos Fong
 */
public interface AccountEntryWebService {

	public AccountEntry fetchAccountEntry(String koroneikiAccountKey)
		throws Exception;

	public String getAccountAttachmentURL(long accountAttachmentId)
		throws Exception;

	public String getUpdateAccountAttachmentURL() throws Exception;

	public void syncToZendesk(String koroneikiAccountKey) throws Exception;

	public void updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws Exception;

	public void updateLanguageId(String koroneikiAccountKey, String languageId)
		throws Exception;

}