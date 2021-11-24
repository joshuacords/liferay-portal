/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.provisioning.license.exception.DuplicateCommonLicenseKeyException;
import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.osb.provisioning.license.service.base.CommonLicenseKeyLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.io.InputStream;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.license.model.CommonLicenseKey",
	service = AopService.class
)
public class CommonLicenseKeyLocalServiceImpl
	extends CommonLicenseKeyLocalServiceBaseImpl {

	public CommonLicenseKey addCommonLicenseKey(
			long userId, String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			String fileName, String fileContent)
		throws Exception {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(fileName);

		byte[] bytes = fileContent.getBytes(StringPool.UTF8);

		long commonLicenseKeyId = counterLocalService.increment();

		CommonLicenseKey commonLicenseKey = commonLicenseKeyPersistence.create(
			commonLicenseKeyId);

		commonLicenseKey.setUserId(userId);
		commonLicenseKey.setUserName(user.getFullName());
		commonLicenseKey.setCreateDate(now);
		commonLicenseKey.setProductGroup(productGroup);
		commonLicenseKey.setProductEnvironment(productEnvironment);
		commonLicenseKey.setProductVersion(productVersion);
		commonLicenseKey.setStartDate(startDate);
		commonLicenseKey.setEndDate(endDate);
		commonLicenseKey.setFileName(fileName);
		commonLicenseKey.setFileSize(bytes.length);

		DLStoreUtil.addDirectory(
			user.getCompanyId(), CompanyConstants.SYSTEM,
			commonLicenseKey.getFileDir());

		DLStoreUtil.addFile(
			user.getCompanyId(), CompanyConstants.SYSTEM,
			commonLicenseKey.getFilePath(), true, bytes);

		return commonLicenseKeyPersistence.update(commonLicenseKey);
	}

	public CommonLicenseKey fetchCommonLicenseKey(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		List<CommonLicenseKey> commonLicenseKeys =
			commonLicenseKeyPersistence.findByPG_PE_PV_gtS_ltE(
				productGroup, productEnvironment, productVersion, startDate,
				endDate);

		if (commonLicenseKeys.isEmpty()) {
			return null;
		}

		return commonLicenseKeys.get(0);
	}

	public byte[] getBytes(long commonLicenseKeyId) throws PortalException {
		CommonLicenseKey commonLicenseKey =
			commonLicenseKeyPersistence.findByPrimaryKey(commonLicenseKeyId);

		return DLStoreUtil.getFileAsBytes(
			commonLicenseKey.getCompanyId(), CompanyConstants.SYSTEM,
			commonLicenseKey.getFilePath());
	}

	public List<CommonLicenseKey> getCommonLicenseKeys(
		String productGroup, int start, int end) {

		return commonLicenseKeyPersistence.findByProductGroup(
			productGroup, start, end);
	}

	public int getCommonLicenseKeysCount(String productGroup) {
		return commonLicenseKeyPersistence.countByProductGroup(productGroup);
	}

	public InputStream getInputStream(long commonLicenseKeyId)
		throws PortalException {

		CommonLicenseKey commonLicenseKey =
			commonLicenseKeyPersistence.findByPrimaryKey(commonLicenseKeyId);

		return DLStoreUtil.getFileAsStream(
			commonLicenseKey.getCompanyId(), CompanyConstants.SYSTEM,
			commonLicenseKey.getFilePath());
	}

	protected void validate(String fileName) throws PortalException {
		CommonLicenseKey commonLicenseKey =
			commonLicenseKeyPersistence.fetchByFileName(fileName);

		if (commonLicenseKey != null) {
			throw new DuplicateCommonLicenseKeyException();
		}
	}

}