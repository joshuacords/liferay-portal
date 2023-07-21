/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.web.internal.portlet.action;

import com.liferay.polls.constants.PollsPortletKeys;
import com.liferay.polls.service.PollsVoteService;
import com.liferay.polls.web.internal.portlet.util.PollsUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Fellwock
 */
@Component(
	property = {
		"javax.portlet.name=" + PollsPortletKeys.POLLS,
		"javax.portlet.name=" + PollsPortletKeys.POLLS_DISPLAY,
		"mvc.command.name=/polls/view_question"
	},
	service = MVCActionCommand.class
)
public class ViewQuestionMVCActionCommand extends EditQuestionMVCActionCommand {

	@Reference(unbind = "-")
	protected void setPollsVoteService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	@Override
	protected void updateQuestion(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		long questionId = ParamUtil.getLong(actionRequest, "questionId");
		long choiceId = ParamUtil.getLong(actionRequest, "choiceId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		_pollsVoteService.addVote(questionId, choiceId, serviceContext);

		PollsUtil.saveVote(actionRequest, actionResponse, questionId);
	}

	private PollsVoteService _pollsVoteService;

}