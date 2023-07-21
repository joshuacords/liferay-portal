/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.attachment;

import com.liferay.mail.reader.attachment.AttachmentHandler;

import java.io.InputStream;

import javax.mail.Folder;

/**
 * @author Scott Lee
 */
public class DefaultAttachmentHandler implements AttachmentHandler {

	public DefaultAttachmentHandler(InputStream inputStream, Folder folder) {
		_inputStream = inputStream;
		_folder = folder;
	}

	@Override
	public void cleanUp() {
	}

	public Folder getFolder() {
		return _folder;
	}

	@Override
	public InputStream getInputStream() {
		return _inputStream;
	}

	private final Folder _folder;
	private final InputStream _inputStream;

}