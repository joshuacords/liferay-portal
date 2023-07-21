/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the PollsQuestion service. Represents a row in the &quot;PollsQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionModel
 * @generated
 */
@ImplementationClassName("com.liferay.polls.model.impl.PollsQuestionImpl")
@ProviderType
public interface PollsQuestion extends PersistedModel, PollsQuestionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.polls.model.impl.PollsQuestionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PollsQuestion, Long> QUESTION_ID_ACCESSOR =
		new Accessor<PollsQuestion, Long>() {

			@Override
			public Long get(PollsQuestion pollsQuestion) {
				return pollsQuestion.getQuestionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PollsQuestion> getTypeClass() {
				return PollsQuestion.class;
			}

		};

	public java.util.List<PollsChoice> getChoices();

	public java.util.List<PollsVote> getVotes();

	public java.util.List<PollsVote> getVotes(int start, int end);

	public int getVotesCount();

	public boolean isExpired();

	public boolean isExpired(
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Date defaultCreateDate);

}