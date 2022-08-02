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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.exception.LicenseEntryNameException;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.base.LicenseEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.license.model.LicenseEntry",
	service = AopService.class
)
public class LicenseEntryLocalServiceImpl
	extends LicenseEntryLocalServiceBaseImpl {

	public LicenseEntry addLicenseEntry(
			long userId, String productKey, String name, String type,
			String versionMin, String versionMax)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(name);

		long licenseEntryId = counterLocalService.increment();

		LicenseEntry licenseEntry = licenseEntryPersistence.create(
			licenseEntryId);

		licenseEntry.setUserId(user.getUserId());
		licenseEntry.setUserName(user.getFullName());
		licenseEntry.setCreateDate(now);
		licenseEntry.setModifiedDate(now);
		licenseEntry.setProductKey(productKey);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	public List<LicenseEntry> getLicenseEntries(String productKey) {
		return licenseEntryPersistence.findByProductKey(productKey);
	}

	public List<LicenseEntry> getLicenseEntriesByNameVersion(
		String name, String version, boolean supportedVersion) {

		List<LicenseEntry> licenseEntries =
			licenseEntryPersistence.findByLikeName(name);

		return filterByVersion(licenseEntries, name, version, supportedVersion);
	}

	public List<LicenseEntry> getLicenseEntriesByType(String type) {
		return licenseEntryPersistence.findByType(type);
	}

	public List<LicenseEntry> getLicenseEntriesByVersion(
			String productKey, String version, boolean supportedVersion)
		throws Exception {

		Product product = _productWebService.getProduct(productKey);

		List<LicenseEntry> licenseEntries =
			licenseEntryPersistence.findByProductKey(productKey);

		return filterByVersion(
			licenseEntries, product.getName(), version, supportedVersion);
	}

	public LicenseEntry getLicenseEntry(String productKey, String type)
		throws PortalException {

		return licenseEntryPersistence.findByPK_T(productKey, type);
	}

	public LicenseEntry updateLicenseEntry(
			long licenseEntryId, String productKey, String name, String type,
			String versionMin, String versionMax)
		throws PortalException {

		validate(name);

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		licenseEntry.setModifiedDate(new Date());
		licenseEntry.setProductKey(productKey);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	protected List<LicenseEntry> filterByVersion(
		List<LicenseEntry> licenseEntries, String name, String version,
		boolean supportedVersion) {

		List<LicenseEntry> curLicenseEntries = new ArrayList<>();

		for (LicenseEntry licenseEntry : licenseEntries) {
			int productVersionMinOrder = ProductVersion.getOrder(
				name, licenseEntry.getVersionMin(), supportedVersion);
			int productVersionMaxOrder = ProductVersion.getOrder(
				name, licenseEntry.getVersionMax(), supportedVersion);

			if ((Validator.isNull(licenseEntry.getVersionMin()) ||
				 (productVersionMinOrder <= ProductVersion.getOrder(
					 name, version, supportedVersion))) &&
				(Validator.isNull(licenseEntry.getVersionMax()) ||
				 (ProductVersion.getOrder(name, version, supportedVersion) <=
					 productVersionMaxOrder))) {

				curLicenseEntries.add(licenseEntry);
			}
		}

		return curLicenseEntries;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new LicenseEntryNameException();
		}
	}

	@Reference
	private ProductWebService _productWebService;

}