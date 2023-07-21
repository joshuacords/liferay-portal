/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.test.util.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;
import com.liferay.polls.util.test.PollsTestUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 * @author Máté Thurzó
 */
@RunWith(Arquillian.class)
public class PollsVoteStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected Map<String, List<StagedModel>> addDependentStagedModelsMap(
			Group group)
		throws Exception {

		Map<String, List<StagedModel>> dependentStagedModelsMap =
			new HashMap<>();

		PollsQuestion question = PollsTestUtil.addQuestion(group.getGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, PollsQuestion.class, question);

		PollsChoice choice = PollsTestUtil.addChoice(
			group.getGroupId(), question.getQuestionId());

		addDependentStagedModel(
			dependentStagedModelsMap, PollsChoice.class, choice);

		return dependentStagedModelsMap;
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		List<StagedModel> questionDependentStagedModels =
			dependentStagedModelsMap.get(PollsQuestion.class.getSimpleName());

		PollsQuestion question =
			(PollsQuestion)questionDependentStagedModels.get(0);

		List<StagedModel> choiceDependentStagedModels =
			dependentStagedModelsMap.get(PollsChoice.class.getSimpleName());

		PollsChoice choice = (PollsChoice)choiceDependentStagedModels.get(0);

		return PollsTestUtil.addVote(
			group.getGroupId(), question.getQuestionId(), choice.getChoiceId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group)
		throws PortalException {

		return PollsVoteLocalServiceUtil.getPollsVoteByUuidAndGroupId(
			uuid, group.getGroupId());
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return PollsVote.class;
	}

	@Override
	protected void validateImport(
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		List<StagedModel> choiceDependentStagedModels =
			dependentStagedModelsMap.get(PollsChoice.class.getSimpleName());

		Assert.assertEquals(
			choiceDependentStagedModels.toString(), 1,
			choiceDependentStagedModels.size());

		PollsChoice choice = (PollsChoice)choiceDependentStagedModels.get(0);

		PollsChoiceLocalServiceUtil.getPollsChoiceByUuidAndGroupId(
			choice.getUuid(), group.getGroupId());

		List<StagedModel> questionDependentStagedModels =
			dependentStagedModelsMap.get(PollsQuestion.class.getSimpleName());

		Assert.assertEquals(
			questionDependentStagedModels.toString(), 1,
			questionDependentStagedModels.size());

		PollsQuestion question =
			(PollsQuestion)questionDependentStagedModels.get(0);

		PollsQuestionLocalServiceUtil.getPollsQuestionByUuidAndGroupId(
			question.getUuid(), group.getGroupId());
	}

}