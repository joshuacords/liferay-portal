/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.exportimport.kernel.lar.DataLevel;
import com.liferay.exportimport.test.util.lar.BasePortletDataHandlerTestCase;
import com.liferay.polls.constants.PollsPortletKeys;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.util.test.PollsTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@Ignore
@RunWith(Arquillian.class)
public class PollsPortletDataHandlerTest
	extends BasePortletDataHandlerTestCase {

	@Rule
	public final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void addStagedModels() throws Exception {
		PollsQuestion question = PollsTestUtil.addQuestion(
			stagingGroup.getGroupId());

		PollsChoice choice = PollsTestUtil.addChoice(
			stagingGroup.getGroupId(), question.getQuestionId());

		PollsTestUtil.addVote(
			stagingGroup.getGroupId(), question.getQuestionId(),
			choice.getChoiceId());
	}

	@Override
	protected DataLevel getDataLevel() {
		return DataLevel.SITE;
	}

	@Override
	protected String getPortletId() {
		return PollsPortletKeys.POLLS;
	}

	@Override
	protected boolean isDataPortalLevel() {
		return false;
	}

	@Override
	protected boolean isDataPortletInstanceLevel() {
		return false;
	}

	@Override
	protected boolean isDataSiteLevel() {
		return true;
	}

}