/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.webserver;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.image.SpriteProcessor;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class DynamicResourceServlet extends WebServerServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		_tempDir = (File)getServletContext().getAttribute(
			JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);
	}

	@Override
	public void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		String servletPath = httpServletRequest.getServletPath();

		String pathInfo = StringPool.BLANK;

		if (httpServletRequest.getPathInfo() != null) {
			pathInfo = URLDecoder.decode(
				httpServletRequest.getPathInfo(), StringPool.UTF8);
		}

		String path = servletPath.concat(pathInfo);

		if (!isAllowedPath(path)) {
			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		File rootDir = _tempDir;

		File file = new File(rootDir, path);

		if (servletPath.equals(SpriteProcessor.PATH)) {
			String spriteRootDirName = PropsValues.SPRITE_ROOT_DIR;

			if (Validator.isNotNull(spriteRootDirName)) {
				rootDir = new File(spriteRootDirName);

				file = new File(rootDir, pathInfo);
			}
		}

		String canonicalPath = file.getCanonicalPath();

		if (!file.exists() || file.isDirectory() || !file.canRead() ||
			file.isHidden() ||
			!canonicalPath.startsWith(rootDir.getCanonicalPath())) {

			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		long lastModified = file.lastModified();

		if (lastModified > 0) {
			long ifModifiedSince = httpServletRequest.getDateHeader(
				HttpHeaders.IF_MODIFIED_SINCE);

			if ((ifModifiedSince > 0) && (ifModifiedSince == lastModified)) {
				httpServletResponse.setStatus(
					HttpServletResponse.SC_NOT_MODIFIED);

				return;
			}
		}

		if (lastModified > 0) {
			httpServletResponse.setDateHeader(
				HttpHeaders.LAST_MODIFIED, lastModified);
		}

		String fileName = file.getName();

		// Determine proper content type

		String contentType = MimeTypesUtil.getContentType(fileName);

		// Send file

		if (isSupportsRangeHeader(contentType)) {
			ServletResponseUtil.sendFileWithRangeHeader(
				httpServletRequest, httpServletResponse, fileName,
				new FileInputStream(file), file.length(), contentType);
		}
		else {
			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse, fileName,
				new FileInputStream(file), file.length(), contentType);
		}
	}

	protected boolean isAllowedPath(String path) {
		for (String allowedPath :
				PropsValues.DYNAMIC_RESOURCE_SERVLET_ALLOWED_PATHS) {

			if (path.startsWith(allowedPath)) {
				return true;
			}
		}

		return false;
	}

	private File _tempDir;

}