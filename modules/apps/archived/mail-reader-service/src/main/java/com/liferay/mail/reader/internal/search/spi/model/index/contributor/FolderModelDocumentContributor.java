/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.search.spi.model.index.contributor;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexerUtil;
import com.liferay.mail.reader.model.Folder;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Scott Lee
 * @author Peter Fellwock
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.mail.reader.model.Folder",
	service = ModelDocumentContributor.class
)
public class FolderModelDocumentContributor
	implements ModelDocumentContributor<Folder> {

	@Override
	public void contribute(Document document, Folder folder) {
		ExpandoBridge expandoBridge = folder.getExpandoBridge();

		document.addKeyword(Field.FOLDER_ID, folder.getFolderId());
		document.addText(Field.NAME, folder.getDisplayName());

		document.addKeyword("accountId", folder.getAccountId());

		ExpandoBridgeIndexerUtil.addAttributes(document, expandoBridge);
	}

}