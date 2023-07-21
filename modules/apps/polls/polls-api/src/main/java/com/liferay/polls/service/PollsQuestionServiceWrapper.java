/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PollsQuestionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionService
 * @generated
 */
public class PollsQuestionServiceWrapper
	implements PollsQuestionService, ServiceWrapper<PollsQuestionService> {

	public PollsQuestionServiceWrapper(
		PollsQuestionService pollsQuestionService) {

		_pollsQuestionService = pollsQuestionService;
	}

	@Override
	public com.liferay.polls.model.PollsQuestion addQuestion(
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			java.util.List<com.liferay.polls.model.PollsChoice> choices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pollsQuestionService.addQuestion(
			titleMap, descriptionMap, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, choices, serviceContext);
	}

	@Override
	public void deleteQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_pollsQuestionService.deleteQuestion(questionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _pollsQuestionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.polls.model.PollsQuestion getQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pollsQuestionService.getQuestion(questionId);
	}

	@Override
	public com.liferay.polls.model.PollsQuestion updateQuestion(
			long questionId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			java.util.List<com.liferay.polls.model.PollsChoice> choices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _pollsQuestionService.updateQuestion(
			questionId, titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, choices, serviceContext);
	}

	@Override
	public PollsQuestionService getWrappedService() {
		return _pollsQuestionService;
	}

	@Override
	public void setWrappedService(PollsQuestionService pollsQuestionService) {
		_pollsQuestionService = pollsQuestionService;
	}

	private PollsQuestionService _pollsQuestionService;

}