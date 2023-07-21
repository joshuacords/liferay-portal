/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.exportimport.data.handler;

import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsChoiceLocalService;
import com.liferay.polls.service.PollsQuestionLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 * @author Máté Thurzó
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class PollsChoiceStagedModelDataHandler
	extends BaseStagedModelDataHandler<PollsChoice> {

	public static final String[] CLASS_NAMES = {PollsChoice.class.getName()};

	@Override
	public void deleteStagedModel(PollsChoice choice) {
		_pollsChoiceLocalService.deletePollsChoice(choice);
	}

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		PollsChoice pollsChoice = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (pollsChoice != null) {
			deleteStagedModel(pollsChoice);
		}
	}

	@Override
	public PollsChoice fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _pollsChoiceLocalService.fetchPollsChoiceByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<PollsChoice> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _pollsChoiceLocalService.getPollsChoicesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<PollsChoice>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(PollsChoice choice) {
		return choice.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, PollsChoice choice)
		throws Exception {

		PollsQuestion question = _pollsQuestionLocalService.getQuestion(
			choice.getQuestionId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, choice, question,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element choiceElement = portletDataContext.getExportDataElement(choice);

		portletDataContext.addClassedModel(
			choiceElement, ExportImportPathUtil.getModelPath(choice), choice);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long choiceId)
		throws Exception {

		PollsChoice existingChoice = fetchMissingReference(uuid, groupId);

		if (existingChoice == null) {
			return;
		}

		Map<Long, Long> choiceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				PollsChoice.class);

		choiceIds.put(choiceId, existingChoice.getChoiceId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, PollsChoice choice)
		throws Exception {

		long userId = portletDataContext.getUserId(choice.getUserUuid());

		Map<Long, Long> questionIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				PollsQuestion.class);

		long questionId = MapUtil.getLong(
			questionIds, choice.getQuestionId(), choice.getQuestionId());

		PollsChoice importedChoice = null;

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			choice);

		if (portletDataContext.isDataStrategyMirror()) {
			PollsChoice existingChoice = fetchStagedModelByUuidAndGroupId(
				choice.getUuid(), portletDataContext.getScopeGroupId());

			if (existingChoice == null) {
				serviceContext.setUuid(choice.getUuid());

				importedChoice = _pollsChoiceLocalService.addChoice(
					userId, questionId, choice.getName(),
					choice.getDescription(), serviceContext);
			}
			else {
				importedChoice = _pollsChoiceLocalService.updateChoice(
					existingChoice.getChoiceId(), questionId, choice.getName(),
					choice.getDescription(), serviceContext);
			}
		}
		else {
			importedChoice = _pollsChoiceLocalService.addChoice(
				userId, questionId, choice.getName(), choice.getDescription(),
				serviceContext);
		}

		portletDataContext.importClassedModel(choice, importedChoice);
	}

	@Reference(unbind = "-")
	protected void setPollsChoiceLocalService(
		PollsChoiceLocalService pollsChoiceLocalService) {

		_pollsChoiceLocalService = pollsChoiceLocalService;
	}

	@Reference(unbind = "-")
	protected void setPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {

		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	private PollsChoiceLocalService _pollsChoiceLocalService;
	private PollsQuestionLocalService _pollsQuestionLocalService;

}