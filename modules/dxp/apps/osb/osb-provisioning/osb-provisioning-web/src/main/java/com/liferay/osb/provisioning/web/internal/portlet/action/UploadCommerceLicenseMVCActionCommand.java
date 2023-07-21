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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
		"mvc.command.name=/admin/upload_commerce_license"
	},
	service = MVCActionCommand.class
)
public class UploadCommerceLicenseMVCActionCommand
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

			FileItem[] fileItems = parameterMap.get("commerceLicenseFiles");

			for (FileItem fileItem : fileItems) {
				try (InputStream inputStream = fileItem.getInputStream()) {
					String fileContent = StringUtil.read(inputStream);

					Document document = SAXReaderUtil.read(fileContent);

					Element rootElement = document.getRootElement();

					String productEntryName = GetterUtil.getString(
						rootElement.elementTextTrim("product-name"));

					if (!productEntryName.contains("Commerce")) {
						continue;
					}

					String description = GetterUtil.getString(
						rootElement.elementTextTrim("description"));

					description = StringUtil.toLowerCase(description);

					String productEnvironment = null;

					if (description.contains(ProductEnvironment.BACKUP)) {
						productEnvironment = ProductEnvironment.BACKUP;
					}
					else if (description.contains(
								ProductEnvironment.NON_PRODUCTION)) {

						productEnvironment = ProductEnvironment.NON_PRODUCTION;
					}
					else {
						productEnvironment = ProductEnvironment.PRODUCTION;
					}

					DateFormat longDateFormatDateTime = new SimpleDateFormat(
						"EEEE, MMMM d, yyyy hh:mm:ss a z", LocaleUtil.US);

					Date startDate = longDateFormatDateTime.parse(
						rootElement.elementTextTrim("start-date"));

					Date expirationDate = longDateFormatDateTime.parse(
						rootElement.elementTextTrim("expiration-date"));

					_commonLicenseKeyLocalService.addCommonLicenseKey(
						themeDisplay.getUserId(),
						ProductGroup.Name.COMMERCE.toString(),
						productEnvironment, StringPool.BLANK, startDate,
						expirationDate, fileItem.getFileName(), fileContent);
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
		UploadCommerceLicenseMVCActionCommand.class);

	@Reference
	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}