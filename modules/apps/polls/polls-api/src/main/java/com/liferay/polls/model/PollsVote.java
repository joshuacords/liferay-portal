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
 * The extended model interface for the PollsVote service. Represents a row in the &quot;PollsVote&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteModel
 * @generated
 */
@ImplementationClassName("com.liferay.polls.model.impl.PollsVoteImpl")
@ProviderType
public interface PollsVote extends PersistedModel, PollsVoteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.polls.model.impl.PollsVoteImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PollsVote, Long> VOTE_ID_ACCESSOR =
		new Accessor<PollsVote, Long>() {

			@Override
			public Long get(PollsVote pollsVote) {
				return pollsVote.getVoteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PollsVote> getTypeClass() {
				return PollsVote.class;
			}

		};

	public PollsChoice getChoice()
		throws com.liferay.portal.kernel.exception.PortalException;

}