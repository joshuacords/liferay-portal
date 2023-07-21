/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.vldap.server.internal.directory.FilterConstraint;
import com.liferay.vldap.server.internal.directory.SearchBase;
import com.liferay.vldap.server.internal.directory.ldap.CompanyDirectory;
import com.liferay.vldap.server.internal.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class CompanyBuilder extends DirectoryBuilder {

	public CompanyBuilder() {
		attributeValidator.addAlwaysValidAttribute("ou");

		attributeValidator.addValidAttributeValues(
			"objectclass", "organizationalUnit", "top", "*");
	}

	@Override
	public List<Directory> buildDirectories(
			SearchBase searchBase, List<FilterConstraint> filterConstraints)
		throws PortalException {

		List<Directory> directories = new ArrayList<>();

		List<Company> companies = getCompanies(filterConstraints);

		for (Company company : companies) {
			Directory directory = new CompanyDirectory(
				searchBase.getTop(), company);

			directories.add(directory);
		}

		return directories;
	}

	protected List<Company> getCompanies(
			List<FilterConstraint> filterConstraints)
		throws PortalException {

		if (filterConstraints.isEmpty()) {
			return CompanyLocalServiceUtil.getCompanies();
		}

		List<Company> companies = new ArrayList<>();

		for (FilterConstraint filterConstraint : filterConstraints) {
			if (!isValidFilterConstraint(filterConstraint)) {
				continue;
			}

			String companyWebId = filterConstraint.getValue("ou");

			if (companyWebId == null) {
				companies.addAll(CompanyLocalServiceUtil.getCompanies(false));
			}
			else {
				Company company = CompanyLocalServiceUtil.getCompanyByWebId(
					companyWebId);

				companies.add(company);
			}
		}

		return ListUtil.unique(companies);
	}

}