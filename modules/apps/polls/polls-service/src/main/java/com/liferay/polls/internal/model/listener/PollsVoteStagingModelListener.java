/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.model.listener;

import com.liferay.polls.model.PollsVote;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.staging.model.listener.StagingModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(immediate = true, service = ModelListener.class)
public class PollsVoteStagingModelListener
	extends BaseModelListener<PollsVote> {

	@Override
	public void onAfterCreate(PollsVote pollsVote)
		throws ModelListenerException {

		_stagingModelListener.onAfterCreate(pollsVote);
	}

	@Override
	public void onAfterRemove(PollsVote pollsVote)
		throws ModelListenerException {

		_stagingModelListener.onAfterRemove(pollsVote);
	}

	@Override
	public void onAfterUpdate(PollsVote pollsVote)
		throws ModelListenerException {

		_stagingModelListener.onAfterUpdate(pollsVote);
	}

	@Reference
	private StagingModelListener<PollsVote> _stagingModelListener;

}