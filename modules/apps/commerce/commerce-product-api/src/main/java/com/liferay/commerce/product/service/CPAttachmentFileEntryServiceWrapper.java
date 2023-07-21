/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPAttachmentFileEntryService}.
 *
 * @author Marco Leo
 * @see CPAttachmentFileEntryService
 * @generated
 */
public class CPAttachmentFileEntryServiceWrapper
	implements CPAttachmentFileEntryService,
			   ServiceWrapper<CPAttachmentFileEntryService> {

	public CPAttachmentFileEntryServiceWrapper(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			addCPAttachmentFileEntry(
				long userId, long groupId, long classNameId, long classPK,
				long fileEntryId, int displayDateMonth, int displayDateDay,
				int displayDateYear, int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.addCPAttachmentFileEntry(
			userId, groupId, classNameId, classPK, fileEntryId,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, titleMap, json, priority, type, serviceContext);
	}

	@Override
	public void deleteCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			fetchCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPAttachmentFileEntry>
				getCPAttachmentFileEntries(
					long classNameId, long classPK, int type, int status,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.
							CPAttachmentFileEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
			long classNameId, long classPK, int type, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
			classNameId, classPK, type, status);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			getCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.getCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpAttachmentFileEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			updateCPAttachmentFileEntry(
				long cpAttachmentFileEntryId, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, fileEntryId, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, serviceContext);
	}

	/**
	 * @param classNameId
	 * @param classPK
	 * @param fileEntryId
	 * @param displayDateMonth
	 * @param displayDateDay
	 * @param displayDateYear
	 * @param displayDateHour
	 * @param displayDateMinute
	 * @param expirationDateMonth
	 * @param expirationDateDay
	 * @param expirationDateYear
	 * @param expirationDateHour
	 * @param expirationDateMinute
	 * @param neverExpire
	 * @param titleMap
	 * @param json
	 * @param priority
	 * @param type
	 * @param externalReferenceCode
	 * @param serviceContext
	 * @throws PortalException
	 * @deprecated As of Athanasius (7.3.x), use {@link
	 #upsertCPAttachmentFileEntry(long, long, long, long, int,
	 int, int, int, int, int, int, int, int, int, boolean, Map,
	 String, double, int, String, ServiceContext)}
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			upsertCPAttachmentFileEntry(
				long groupId, long classNameId, long classPK, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			groupId, classNameId, classPK, fileEntryId, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, titleMap,
			json, priority, type, externalReferenceCode, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPAttachmentFileEntry
			upsertCPAttachmentFileEntry(
				long groupId, long classNameId, long classPK,
				long cpAttachmentFileEntryId, long fileEntryId,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				java.util.Map<java.util.Locale, String> titleMap, String json,
				double priority, int type, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpAttachmentFileEntryService.upsertCPAttachmentFileEntry(
			groupId, classNameId, classPK, cpAttachmentFileEntryId, fileEntryId,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, titleMap, json, priority, type, externalReferenceCode,
			serviceContext);
	}

	@Override
	public CPAttachmentFileEntryService getWrappedService() {
		return _cpAttachmentFileEntryService;
	}

	@Override
	public void setWrappedService(
		CPAttachmentFileEntryService cpAttachmentFileEntryService) {

		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
	}

	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

}