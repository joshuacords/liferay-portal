/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.batch.engine;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Javier Gamarra
 */
public interface VulcanBatchEngineTaskItemDelegate<T> {

	public void create(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception;

	public void delete(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception;

	public EntityModel getEntityModel(Map<String, List<String>> multivaluedMap)
		throws Exception;

	public Page<T> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception;

	public void setContextCompany(Company contextCompany);

	public void setContextUser(User contextUser);

	public void setLanguageId(String languageId);

	public void update(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception;

}