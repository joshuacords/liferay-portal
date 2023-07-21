/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.polls.exception.DuplicateVoteException;
import com.liferay.polls.exception.NoSuchQuestionException;
import com.liferay.polls.exception.QuestionExpiredException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.base.PollsVoteLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Máté Thurzó
 */
@Component(
	property = "model.class.name=com.liferay.polls.model.PollsVote",
	service = AopService.class
)
public class PollsVoteLocalServiceImpl extends PollsVoteLocalServiceBaseImpl {

	@Override
	public PollsVote addVote(
			long userId, long questionId, long choiceId,
			ServiceContext serviceContext)
		throws PortalException {

		// Choice

		Date now = new Date();

		PollsChoice choice = pollsChoicePersistence.findByPrimaryKey(choiceId);

		if (choice.getQuestionId() != questionId) {
			throw new NoSuchQuestionException(
				"{questionId=" + questionId + "}");
		}

		// Question

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		if (question.isExpired(serviceContext, now)) {
			throw new QuestionExpiredException();
		}

		question.setLastVoteDate(serviceContext.getCreateDate(now));

		pollsQuestionPersistence.update(question);

		// Vote

		PollsVote vote = null;

		User user = userLocalService.getUser(userId);

		if (!user.isDefaultUser()) {
			vote = fetchQuestionUserVote(questionId, userId);
		}

		if (vote != null) {
			StringBundler sb = new StringBundler(5);

			sb.append("{questionId=");
			sb.append(questionId);
			sb.append(", userId=");
			sb.append(userId);
			sb.append("}");

			throw new DuplicateVoteException(sb.toString());
		}

		String userName = user.getFullName();

		if (user.isDefaultUser()) {
			userName = serviceContext.translate("anonymous");
		}

		long voteId = counterLocalService.increment();

		vote = pollsVotePersistence.create(voteId);

		vote.setUuid(serviceContext.getUuid());
		vote.setGroupId(serviceContext.getScopeGroupId());
		vote.setCompanyId(serviceContext.getCompanyId());
		vote.setUserId(userId);
		vote.setUserName(userName);
		vote.setQuestionId(questionId);
		vote.setChoiceId(choiceId);
		vote.setVoteDate(serviceContext.getCreateDate(now));

		return pollsVotePersistence.update(vote);
	}

	@Override
	public PollsVote fetchQuestionUserVote(long questionId, long userId) {
		List<PollsVote> votes = pollsVotePersistence.findByQ_U(
			questionId, userId);

		if (votes.isEmpty()) {
			return null;
		}

		return votes.get(0);
	}

	@Override
	public List<PollsVote> getChoiceVotes(long choiceId, int start, int end) {
		return pollsVotePersistence.findByChoiceId(choiceId, start, end);
	}

	@Override
	public int getChoiceVotesCount(long choiceId) {
		return pollsVotePersistence.countByChoiceId(choiceId);
	}

	@Override
	public List<PollsVote> getQuestionVotes(
		long questionId, int start, int end) {

		return pollsVotePersistence.findByQuestionId(questionId, start, end);
	}

	@Override
	public int getQuestionVotesCount(long questionId) {
		return pollsVotePersistence.countByQuestionId(questionId);
	}

}