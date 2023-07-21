/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface DDMStructureFinder {

	public int countByKeywords(
		long companyId, long[] groupIds, long classNameId, long classPK,
		String keywords);

	public int countByKeywords(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int status);

	public int countByC_G_C_S(
		long companyId, long[] groupIds, long classNameId, int status);

	public int countByC_G_C_N_D_S_T_S(
		long companyId, long[] groupIds, long classNameId, String name,
		String description, String storageType, int type, int status,
		boolean andOperator);

	public int countByC_G_C_N_D_S_T_S(
		long companyId, long[] groupIds, long classNameId, String[] names,
		String[] descriptions, String storageType, int type, int status,
		boolean andOperator);

	public int filterCountByKeywords(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int status);

	public int filterCountByKeywords(
		long companyId, long[] groupIds, long classNameId, String keywords,
		int type, int status);

	public int filterCountByC_G_C_S(
		long companyId, long[] groupIds, long classNameId, int status);

	public int filterCountByC_G_C_N_D_S_T_S(
		long companyId, long[] groupIds, long classNameId, String name,
		String description, String storageType, int type, int status,
		boolean andOperator);

	public int filterCountByC_G_C_N_D_S_T_S(
		long companyId, long[] groupIds, long classNameId, String[] names,
		String[] descriptions, String storageType, int type, int status,
		boolean andOperator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		filterFindByKeywords(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int type, int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		filterFindByKeywords(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		filterFindByC_G_C_S(
			long companyId, long[] groupIds, long classNameId, int status,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		filterFindByC_G_C_N_D_S_T_S(
			long companyId, long[] groupIds, long classNameId, String name,
			String description, String storageType, int type, int status,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		filterFindByC_G_C_N_D_S_T_S(
			long companyId, long[] groupIds, long classNameId, String[] names,
			String[] descriptions, String storageType, int type, int status,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		findByKeywords(
			long companyId, long[] groupIds, long classNameId, long classPK,
			String keywords, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		findByKeywords(
			long companyId, long[] groupIds, long classNameId, String keywords,
			int status, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		findByC_G_C_S(
			long companyId, long[] groupIds, long classNameId, int status,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		findByC_G_C_N_D_S_T_S(
			long companyId, long[] groupIds, long classNameId, String name,
			String description, String storageType, int type, int status,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		findByC_G_C_N_D_S_T_S(
			long companyId, long[] groupIds, long classNameId, String[] names,
			String[] descriptions, String storageType, int type, int status,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.mapping.model.DDMStructure>
					orderByComparator);

}