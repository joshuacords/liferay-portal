/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.license.deployer.internal.installer;

import com.liferay.portal.file.install.FileInstaller;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.File;

import java.net.URL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = FileInstaller.class)
public class LicenseInstaller implements FileInstaller {

	@Override
	public boolean canTransformURL(File artifact) {
		String extension = FileUtil.getExtension(artifact.getName());

		if (!extension.equals("xml")) {
			return false;
		}

		try {
			String content = FileUtil.read(artifact);

			Document document = SAXReaderUtil.read(content);

			Element rootElement = document.getRootElement();

			String rootElementName = rootElement.getName();

			if (rootElementName.equals("license") ||
				rootElementName.equals("licenses")) {

				return true;
			}
		}
		catch (Exception exception) {
		}

		return false;
	}

	@Override
	public URL transformURL(File file) throws Exception {
		String content = FileUtil.read(file);

		JSONObject jsonObject = JSONUtil.put("licenseXML", content);

		LicenseManagerUtil.registerLicense(jsonObject);

		return null;
	}

	@Override
	public void uninstall(File file) {
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}