/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.exception.DuplicateCommonLicenseKeyException;
import com.liferay.osb.provisioning.license.helper.constants.ProductEnvironment;
import com.liferay.osb.provisioning.license.service.CommonLicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.ProductGroup;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.InputStream;

import java.util.Date;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ADMIN,
		"mvc.command.name=/admin/upload_elasticsearch_license"
	},
	service = MVCActionCommand.class
)
public class UploadElasticsearchLicenseMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			Map<String, FileItem[]> parameterMap =
				uploadPortletRequest.getMultipartParameterMap();

			FileItem[] fileItems = parameterMap.get(
				"elasticsearchLicenseFiles");

			for (FileItem fileItem : fileItems) {
				try (InputStream inputStream = fileItem.getInputStream()) {
					String fileContent = StringUtil.read(inputStream);

					JSONObject jsonObject = _jsonFactory.createJSONObject(
						fileContent);

					JSONObject licenseJSONObject = jsonObject.getJSONObject(
						"license");

					String issuedTo = licenseJSONObject.getString("issued_to");

					String productEnvironment = null;

					if (issuedTo.contains(ProductEnvironment.BACKUP)) {
						productEnvironment = ProductEnvironment.BACKUP;
					}
					else if (issuedTo.contains(
								ProductEnvironment.NON_PRODUCTION)) {

						productEnvironment = ProductEnvironment.NON_PRODUCTION;
					}
					else {
						productEnvironment = ProductEnvironment.PRODUCTION;
					}

					Date startDate = new Date(
						licenseJSONObject.getLong("start_date_in_millis"));
					Date endDate = new Date(
						licenseJSONObject.getLong("expiry_date_in_millis"));

					_commonLicenseKeyLocalService.addCommonLicenseKey(
						themeDisplay.getUserId(),
						ProductGroup.Name.ENTERPRISE_SEARCH.toString(),
						productEnvironment, StringPool.BLANK, startDate,
						endDate, fileItem.getFileName(), fileContent);
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			if (exception instanceof DuplicateCommonLicenseKeyException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else {
				throw exception;
			}
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UploadElasticsearchLicenseMVCActionCommand.class);

	@Reference
	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}