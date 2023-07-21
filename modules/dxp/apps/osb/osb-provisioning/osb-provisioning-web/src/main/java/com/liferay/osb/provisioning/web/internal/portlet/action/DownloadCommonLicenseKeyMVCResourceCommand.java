/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.osb.provisioning.license.service.CommonLicenseKeyLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.InputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ADMIN,
		"mvc.command.name=/admin/download_common_license_key"
	},
	service = MVCResourceCommand.class
)
public class DownloadCommonLicenseKeyMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			long commonLicenseKeyId = ParamUtil.getLong(
				resourceRequest, "commonLicenseKeyId");

			CommonLicenseKey commonlicenseKey =
				_commonLicenseKeyLocalService.getCommonLicenseKey(
					commonLicenseKeyId);

			InputStream inputStream =
				_commonLicenseKeyLocalService.getInputStream(
					commonLicenseKeyId);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse,
				commonlicenseKey.getFileName(), inputStream,
				(int)commonlicenseKey.getFileSize(),
				ContentTypes.APPLICATION_JSON,
				HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadCommonLicenseKeyMVCResourceCommand.class);

	@Reference
	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

}