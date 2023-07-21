/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upload;

import com.liferay.portal.kernel.util.ProgressTracker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author     Brian Myunghun Kim
 * @author     Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class LiferayFileUpload extends ServletFileUpload {

	public static final String FILE_NAME =
		LiferayFileUpload.class.getName() + "_FILE_NAME";

	public static final String PERCENT = ProgressTracker.PERCENT;

	public LiferayFileUpload(
		FileItemFactory fileItemFactory,
		HttpServletRequest httpServletRequest) {

		super(fileItemFactory);

		_session = httpServletRequest.getSession();
	}

	@Override
	public List<FileItem> parseRequest(HttpServletRequest httpServletRequest)
		throws FileUploadException {

		_session.removeAttribute(LiferayFileUpload.PERCENT);

		return super.parseRequest(httpServletRequest);
	}

	private final HttpSession _session;

}