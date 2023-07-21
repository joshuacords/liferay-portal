/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.resource.v1_0;

import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.headless.common.spi.resource.SPIRatingResource;
import com.liferay.headless.common.spi.service.context.ServiceContextUtil;
import com.liferay.headless.delivery.dto.v1_0.MessageBoardThread;
import com.liferay.headless.delivery.dto.v1_0.Rating;
import com.liferay.headless.delivery.internal.dto.v1_0.converter.MessageBoardThreadDTOConverter;
import com.liferay.headless.delivery.internal.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.EntityFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.RatingUtil;
import com.liferay.headless.delivery.internal.odata.entity.v1_0.MessageBoardMessageEntityModel;
import com.liferay.headless.delivery.resource.v1_0.MessageBoardThreadResource;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.constants.MBThreadConstants;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBCategoryService;
import com.liferay.message.boards.service.MBMessageService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.message.boards.settings.MBGroupServiceSettings;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.aggregation.Aggregation;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.SearchUtil;
import com.liferay.ratings.kernel.service.RatingsEntryLocalService;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/message-board-thread.properties",
	scope = ServiceScope.PROTOTYPE, service = MessageBoardThreadResource.class
)
public class MessageBoardThreadResourceImpl
	extends BaseMessageBoardThreadResourceImpl implements EntityModelResource {

	@Override
	public void deleteMessageBoardThread(Long messageBoardThreadId)
		throws Exception {

		_mbThreadService.deleteThread(messageBoardThreadId);
	}

	@Override
	public void deleteMessageBoardThreadMyRating(Long messageBoardThreadId)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		MBThread mbThread = _mbThreadLocalService.getMBThread(
			messageBoardThreadId);

		spiRatingResource.deleteRating(mbThread.getRootMessageId());
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return new MessageBoardMessageEntityModel(
			new ArrayList<>(
				EntityFieldsUtil.getEntityFields(
					_portal.getClassNameId(MBMessage.class.getName()),
					contextCompany.getCompanyId(), _expandoColumnLocalService,
					_expandoTableLocalService)));
	}

	@Override
	public Page<MessageBoardThread>
			getMessageBoardSectionMessageBoardThreadsPage(
				Long messageBoardSectionId, String search,
				Aggregation aggregation, Filter filter, Pagination pagination,
				Sort[] sorts)
		throws Exception {

		MBCategory mbCategory = _mbCategoryService.getCategory(
			messageBoardSectionId);

		return _getSiteMessageBoardThreadsPage(
			_getMessageBoardSectionListActions(mbCategory),
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				booleanFilter.add(
					new TermFilter(
						"categoryId",
						String.valueOf(mbCategory.getCategoryId())),
					BooleanClauseOccur.MUST);
				booleanFilter.add(
					new TermFilter("parentMessageId", "0"),
					BooleanClauseOccur.MUST);
			},
			mbCategory.getGroupId(), search, aggregation, filter, pagination,
			sorts);
	}

	@Override
	public MessageBoardThread getMessageBoardThread(Long messageBoardThreadId)
		throws Exception {

		return _toMessageBoardThread(
			_mbThreadLocalService.getMBThread(messageBoardThreadId));
	}

	@Override
	public Rating getMessageBoardThreadMyRating(Long messageBoardThreadId)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		MBThread mbThread = _mbThreadLocalService.getMBThread(
			messageBoardThreadId);

		return spiRatingResource.getRating(mbThread.getRootMessageId());
	}

	@Override
	public Page<MessageBoardThread> getSiteMessageBoardThreadsPage(
			Long siteId, Boolean flatten, String search,
			Aggregation aggregation, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		return _getSiteMessageBoardThreadsPage(
			HashMapBuilder.put(
				"create",
				addAction(
					"ADD_MESSAGE", "postSiteMessageBoardThread",
					"com.liferay.message.boards", siteId)
			).put(
				"get",
				addAction(
					"VIEW", "getSiteMessageBoardThreadsPage",
					"com.liferay.message.boards", siteId)
			).build(),
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				if (!GetterUtil.getBoolean(flatten)) {
					booleanFilter.add(
						new TermFilter(Field.CATEGORY_ID, "0"),
						BooleanClauseOccur.MUST);
				}

				booleanFilter.add(
					new TermFilter("parentMessageId", "0"),
					BooleanClauseOccur.MUST);
			},
			siteId, search, aggregation, filter, pagination, sorts);
	}

	@Override
	public MessageBoardThread postMessageBoardSectionMessageBoardThread(
			Long messageBoardSectionId, MessageBoardThread messageBoardThread)
		throws Exception {

		MBCategory mbCategory = _mbCategoryService.getCategory(
			messageBoardSectionId);

		return _addMessageBoardThread(
			mbCategory.getGroupId(), messageBoardSectionId, messageBoardThread);
	}

	@Override
	public Rating postMessageBoardThreadMyRating(
			Long messageBoardThreadId, Rating rating)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		MBThread mbThread = _mbThreadLocalService.getThread(
			messageBoardThreadId);

		return spiRatingResource.addOrUpdateRating(
			rating.getRatingValue(), mbThread.getRootMessageId());
	}

	@Override
	public MessageBoardThread postSiteMessageBoardThread(
			Long siteId, MessageBoardThread messageBoardThread)
		throws Exception {

		return _addMessageBoardThread(siteId, 0L, messageBoardThread);
	}

	@Override
	public MessageBoardThread putMessageBoardThread(
			Long messageBoardThreadId, MessageBoardThread messageBoardThread)
		throws Exception {

		MBThread mbThread = _mbThreadLocalService.getMBThread(
			messageBoardThreadId);

		MBMessage mbMessage = _mbMessageService.getMessage(
			mbThread.getRootMessageId());

		_updateQuestion(mbMessage, messageBoardThread);

		return _toMessageBoardThread(
			_mbMessageService.updateMessage(
				mbThread.getRootMessageId(), messageBoardThread.getHeadline(),
				messageBoardThread.getArticleBody(), null,
				_toPriority(
					mbThread.getGroupId(), messageBoardThread.getThreadType()),
				false,
				ServiceContextUtil.createServiceContext(
					null,
					Optional.ofNullable(
						messageBoardThread.getKeywords()
					).orElse(
						new String[0]
					),
					_getExpandoBridgeAttributes(messageBoardThread),
					mbThread.getGroupId(),
					messageBoardThread.getViewableByAsString())));
	}

	@Override
	public Rating putMessageBoardThreadMyRating(
			Long messageBoardThreadId, Rating rating)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		MBThread mbThread = _mbThreadLocalService.getThread(
			messageBoardThreadId);

		return spiRatingResource.addOrUpdateRating(
			rating.getRatingValue(), mbThread.getRootMessageId());
	}

	@Override
	public void putMessageBoardThreadSubscribe(Long messageBoardThreadId)
		throws Exception {

		MBThread mbThread = _mbThreadLocalService.getThread(
			messageBoardThreadId);

		_mbMessageService.subscribeMessage(mbThread.getRootMessageId());
	}

	@Override
	public void putMessageBoardThreadUnsubscribe(Long messageBoardThreadId)
		throws Exception {

		MBThread mbThread = _mbThreadLocalService.getThread(
			messageBoardThreadId);

		_mbMessageService.unsubscribeMessage(mbThread.getRootMessageId());
	}

	private MessageBoardThread _addMessageBoardThread(
			Long siteId, Long messageBoardSectionId,
			MessageBoardThread messageBoardThread)
		throws Exception {

		MBMessage mbMessage = _mbMessageService.addMessage(
			siteId, messageBoardSectionId, messageBoardThread.getHeadline(),
			messageBoardThread.getArticleBody(),
			MBMessageConstants.DEFAULT_FORMAT, Collections.emptyList(), false,
			_toPriority(siteId, messageBoardThread.getThreadType()), false,
			ServiceContextUtil.createServiceContext(
				null, messageBoardThread.getKeywords(),
				_getExpandoBridgeAttributes(messageBoardThread), siteId,
				messageBoardThread.getViewableByAsString()));

		_updateQuestion(mbMessage, messageBoardThread);

		return _toMessageBoardThread(mbMessage);
	}

	private Map<String, Serializable> _getExpandoBridgeAttributes(
		MessageBoardThread messageBoardThread) {

		return CustomFieldsUtil.toMap(
			MBMessage.class.getName(), contextCompany.getCompanyId(),
			messageBoardThread.getCustomFields(),
			contextAcceptLanguage.getPreferredLocale());
	}

	private Map<String, Map<String, String>> _getMessageBoardSectionListActions(
		MBCategory mbCategory) {

		return HashMapBuilder.<String, Map<String, String>>put(
			"create",
			addAction(
				"ADD_MESSAGE", mbCategory.getCategoryId(),
				"postMessageBoardSectionMessageBoardThread",
				mbCategory.getUserId(), "com.liferay.message.boards",
				mbCategory.getGroupId())
		).put(
			"get",
			addAction(
				"VIEW", mbCategory.getCategoryId(),
				"getMessageBoardSectionMessageBoardThreadsPage",
				mbCategory.getUserId(), "com.liferay.message.boards",
				mbCategory.getGroupId())
		).build();
	}

	private Page<MessageBoardThread> _getSiteMessageBoardThreadsPage(
			Map<String, Map<String, String>> actions,
			UnsafeConsumer<BooleanQuery, Exception> booleanQueryUnsafeConsumer,
			Long siteId, String keywords, Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			actions, booleanQueryUnsafeConsumer, filter, MBMessage.class,
			keywords, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.addVulcanAggregation(aggregation);
				searchContext.setCompanyId(contextCompany.getCompanyId());
				searchContext.setGroupIds(new long[] {siteId});
			},
			sorts,
			document -> _toMessageBoardThread(
				_mbMessageService.getMessage(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))));
	}

	private SPIRatingResource<Rating> _getSPIRatingResource() {
		return new SPIRatingResource<>(
			MBMessage.class.getName(), _ratingsEntryLocalService,
			ratingsEntry -> {
				MBMessage mbMessage = _mbMessageService.getMessage(
					ratingsEntry.getClassPK());

				return RatingUtil.toRating(
					HashMapBuilder.put(
						"create",
						addAction(
							"UPDATE", mbMessage,
							"postMessageBoardThreadMyRating")
					).put(
						"delete",
						addAction(
							"UPDATE", mbMessage,
							"deleteMessageBoardThreadMyRating")
					).put(
						"get",
						addAction(
							"VIEW", mbMessage, "getMessageBoardThreadMyRating")
					).put(
						"replace",
						addAction(
							"UPDATE", mbMessage,
							"putMessageBoardThreadMyRating")
					).build(),
					_portal, ratingsEntry, _userLocalService);
			},
			contextUser);
	}

	private MessageBoardThread _toMessageBoardThread(MBMessage mbMessage)
		throws Exception {

		return _toMessageBoardThread(mbMessage.getThread());
	}

	private MessageBoardThread _toMessageBoardThread(MBThread mbThread)
		throws Exception {

		MBMessage mbMessage = _mbMessageService.getMessage(
			mbThread.getRootMessageId());

		return _messageBoardThreadDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				HashMapBuilder.put(
					"delete",
					addAction("DELETE", mbMessage, "deleteMessageBoardThread")
				).put(
					"get", addAction("VIEW", mbMessage, "getMessageBoardThread")
				).put(
					"replace",
					addAction("UPDATE", mbMessage, "putMessageBoardThread")
				).put(
					"reply-to-thread",
					ActionUtil.addAction(
						"REPLY_TO_MESSAGE",
						MessageBoardMessageResourceImpl.class,
						mbMessage.getMessageId(),
						"postMessageBoardThreadMessageBoardMessage",
						contextScopeChecker, mbMessage.getUserId(),
						"com.liferay.message.boards", mbMessage.getGroupId(),
						contextUriInfo)
				).put(
					"subscribe",
					addAction(
						"SUBSCRIBE", mbMessage,
						"putMessageBoardThreadSubscribe")
				).put(
					"unsubscribe",
					addAction(
						"SUBSCRIBE", mbMessage,
						"putMessageBoardThreadUnsubscribe")
				).build(),
				_dtoConverterRegistry, mbThread.getThreadId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private double _toPriority(Long siteId, String threadType)
		throws Exception {

		if (threadType == null) {
			return MBThreadConstants.PRIORITY_NOT_GIVEN;
		}

		MBGroupServiceSettings mbGroupServiceSettings =
			MBGroupServiceSettings.getInstance(siteId);

		String[] priorities = mbGroupServiceSettings.getPriorities(
			contextAcceptLanguage.getPreferredLanguageId());

		for (String priorityString : priorities) {
			String[] parts = StringUtil.split(priorityString, StringPool.PIPE);

			if (StringUtil.equalsIgnoreCase(parts[0], threadType)) {
				return GetterUtil.getDouble(parts[2]);
			}
		}

		throw new BadRequestException(
			StringBundler.concat(
				"Thread type \"", threadType, "\" is not available in ",
				Arrays.toString(
					transform(
						priorities,
						priority -> {
							String[] parts = StringUtil.split(
								priority, StringPool.PIPE);

							return parts[0];
						},
						String.class))));
	}

	private void _updateQuestion(
			MBMessage mbMessage, MessageBoardThread messageBoardThread)
		throws Exception {

		Boolean showAsQuestion = messageBoardThread.getShowAsQuestion();

		if (showAsQuestion != null) {
			_mbThreadLocalService.updateQuestion(
				mbMessage.getThreadId(), showAsQuestion);

			MBThread mbThread = mbMessage.getThread();

			mbThread.setQuestion(showAsQuestion);
		}
	}

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private MBCategoryService _mbCategoryService;

	@Reference
	private MBMessageService _mbMessageService;

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference
	private MBThreadService _mbThreadService;

	@Reference
	private MessageBoardThreadDTOConverter _messageBoardThreadDTOConverter;

	@Reference
	private Portal _portal;

	@Reference
	private RatingsEntryLocalService _ratingsEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}