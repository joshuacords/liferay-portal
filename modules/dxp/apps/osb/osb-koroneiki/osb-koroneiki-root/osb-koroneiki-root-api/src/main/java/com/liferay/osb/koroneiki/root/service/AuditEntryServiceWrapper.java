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

package com.liferay.osb.koroneiki.root.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryService
 * @generated
 */
public class AuditEntryServiceWrapper
	implements AuditEntryService, ServiceWrapper<AuditEntryService> {

	public AuditEntryServiceWrapper(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.AuditEntry addAuditEntry(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, String action, String field, String oldLabel,
			String oldValue, String newLabel, String newValue,
			String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.addAuditEntry(
			classNameId, classPK, fieldClassNameId, fieldClassPK, action, field,
			oldLabel, oldValue, newLabel, newValue, description,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.AuditEntry>
			getAuditEntries(
				long classNameId, long classPK, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.koroneiki.root.model.AuditEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntries(
			classNameId, classPK, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.AuditEntry>
			getAuditEntries(
				long classNameId, long classPK, long fieldClassNameId,
				long fieldClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntries(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end);
	}

	@Override
	public int getAuditEntriesCount(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntriesCount(classNameId, classPK);
	}

	@Override
	public int getAuditEntriesCount(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntriesCount(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntry(auditEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			String auditEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryService.getAuditEntry(auditEntryKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public AuditEntryService getWrappedService() {
		return _auditEntryService;
	}

	@Override
	public void setWrappedService(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	private AuditEntryService _auditEntryService;

}