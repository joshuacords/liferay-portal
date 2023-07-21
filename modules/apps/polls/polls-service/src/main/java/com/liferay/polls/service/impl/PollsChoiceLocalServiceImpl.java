/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service.impl;

import com.liferay.polls.exception.QuestionChoiceException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.service.base.PollsChoiceLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.polls.model.PollsChoice",
	service = AopService.class
)
public class PollsChoiceLocalServiceImpl
	extends PollsChoiceLocalServiceBaseImpl {

	@Override
	public PollsChoice addChoice(
			long userId, long questionId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		validate(name, description);

		User user = userLocalService.getUser(userId);

		long choiceId = counterLocalService.increment();

		PollsChoice choice = pollsChoicePersistence.create(choiceId);

		choice.setUuid(serviceContext.getUuid());
		choice.setGroupId(serviceContext.getScopeGroupId());
		choice.setCompanyId(user.getCompanyId());
		choice.setUserId(user.getUserId());
		choice.setUserName(user.getFullName());
		choice.setQuestionId(questionId);
		choice.setName(name);
		choice.setDescription(description);

		return pollsChoicePersistence.update(choice);
	}

	@Override
	public PollsChoice getChoice(long choiceId) throws PortalException {
		return pollsChoicePersistence.findByPrimaryKey(choiceId);
	}

	@Override
	public List<PollsChoice> getChoices(long questionId) {
		return pollsChoicePersistence.findByQuestionId(questionId);
	}

	@Override
	public int getChoicesCount(long questionId) {
		return pollsChoicePersistence.countByQuestionId(questionId);
	}

	@Override
	public PollsChoice updateChoice(
			long choiceId, long questionId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		validate(name, description);

		pollsQuestionPersistence.findByPrimaryKey(questionId);

		PollsChoice choice = pollsChoicePersistence.findByPrimaryKey(choiceId);

		choice.setQuestionId(questionId);
		choice.setName(name);
		choice.setDescription(description);

		return pollsChoicePersistence.update(choice);
	}

	protected void validate(String name, String description)
		throws PortalException {

		if (Validator.isNull(name) || Validator.isNull(description)) {
			throw new QuestionChoiceException();
		}
	}

}