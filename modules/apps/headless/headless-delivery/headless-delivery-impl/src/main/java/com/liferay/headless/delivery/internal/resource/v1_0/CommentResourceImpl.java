/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.resource.v1_0;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.headless.common.spi.resource.SPICommentResource;
import com.liferay.headless.delivery.dto.v1_0.Comment;
import com.liferay.headless.delivery.internal.dto.v1_0.util.CommentUtil;
import com.liferay.headless.delivery.resource.v1_0.CommentResource;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.message.boards.exception.MessageSubjectException;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.comment.DiscussionComment;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.aggregation.Aggregation;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/comment.properties",
	scope = ServiceScope.PROTOTYPE, service = CommentResource.class
)
public class CommentResourceImpl
	extends BaseCommentResourceImpl implements EntityModelResource {

	@Override
	public void deleteComment(Long commentId) throws Exception {
		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		spiCommentResource.deleteComment(commentId);
	}

	@Override
	public Page<Comment> getBlogPostingCommentsPage(
			Long blogPostingId, String search, Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		BlogsEntry blogsEntry = _blogsEntryService.getEntry(blogPostingId);

		Discussion discussion = _commentManager.getDiscussion(
			blogsEntry.getUserId(), blogsEntry.getGroupId(),
			BlogsEntry.class.getName(), blogPostingId,
			_createServiceContextFunction());

		DiscussionComment rootDiscussionComment =
			discussion.getRootDiscussionComment();

		return _getComments(
			HashMapBuilder.put(
				"add-discussion",
				addAction(
					"ADD_DISCUSSION", blogPostingId, "postBlogPostingComment",
					blogsEntry.getUserId(), BlogsEntry.class.getName(),
					blogsEntry.getGroupId())
			).put(
				"get",
				addAction(
					"VIEW", blogPostingId, "getBlogPostingCommentsPage",
					blogsEntry.getUserId(), BlogsEntry.class.getName(),
					blogsEntry.getGroupId())
			).build(),
			rootDiscussionComment.getCommentId(), search, aggregation, filter,
			pagination, sorts);
	}

	@Override
	public Comment getComment(Long commentId) throws Exception {
		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		return spiCommentResource.getComment(commentId);
	}

	@Override
	public Page<Comment> getCommentCommentsPage(
			Long parentCommentId, String search, Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return _getComments(
			Collections.emptyMap(), parentCommentId, search, aggregation,
			filter, pagination, sorts);
	}

	@Override
	public Page<Comment> getDocumentCommentsPage(
			Long documentId, String search, Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		DLFileEntry dlFileEntry = _dlFileEntryService.getFileEntry(documentId);

		Discussion discussion = _commentManager.getDiscussion(
			dlFileEntry.getUserId(), dlFileEntry.getGroupId(),
			DLFileEntry.class.getName(), documentId,
			_createServiceContextFunction());

		DiscussionComment rootDiscussionComment =
			discussion.getRootDiscussionComment();

		return _getComments(
			HashMapBuilder.put(
				"add-discussion",
				addAction(
					"ADD_DISCUSSION", documentId, "postDocumentComment",
					dlFileEntry.getUserId(), DLFileEntry.class.getName(),
					dlFileEntry.getGroupId())
			).put(
				"get",
				addAction(
					"VIEW", documentId, "getDocumentCommentsPage",
					dlFileEntry.getUserId(), DLFileEntry.class.getName(),
					dlFileEntry.getGroupId())
			).build(),
			rootDiscussionComment.getCommentId(), search, aggregation, filter,
			pagination, sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		return spiCommentResource.getEntityModel(multivaluedMap);
	}

	@Override
	public Page<Comment> getStructuredContentCommentsPage(
			Long structuredContentId, String search, Aggregation aggregation,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		JournalArticle journalArticle = _journalArticleService.getLatestArticle(
			structuredContentId);

		Discussion discussion = _commentManager.getDiscussion(
			journalArticle.getUserId(), journalArticle.getGroupId(),
			JournalArticle.class.getName(), structuredContentId,
			_createServiceContextFunction());

		DiscussionComment rootDiscussionComment =
			discussion.getRootDiscussionComment();

		return _getComments(
			HashMapBuilder.put(
				"add-discussion",
				addAction(
					"ADD_DISCUSSION", structuredContentId,
					"postStructuredContentComment", journalArticle.getUserId(),
					JournalArticle.class.getName(), journalArticle.getGroupId())
			).put(
				"get",
				addAction(
					"VIEW", structuredContentId,
					"getStructuredContentCommentsPage",
					journalArticle.getUserId(), JournalArticle.class.getName(),
					journalArticle.getGroupId())
			).build(),
			rootDiscussionComment.getCommentId(), search, aggregation, filter,
			pagination, sorts);
	}

	@Override
	public Comment postBlogPostingComment(Long blogPostingId, Comment comment)
		throws Exception {

		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		BlogsEntry blogsEntry = _blogsEntryService.getEntry(blogPostingId);

		return spiCommentResource.postEntityComment(
			blogsEntry.getGroupId(), BlogsEntry.class.getName(), blogPostingId,
			comment.getText());
	}

	@Override
	public Comment postCommentComment(Long parentCommentId, Comment comment)
		throws Exception {

		com.liferay.portal.kernel.comment.Comment parentComment =
			_commentManager.fetchComment(parentCommentId);

		if (parentComment == null) {
			throw new NotFoundException();
		}

		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		return spiCommentResource.postCommentComment(
			parentComment, comment.getText());
	}

	@Override
	public Comment postDocumentComment(Long documentId, Comment comment)
		throws Exception {

		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		DLFileEntry fileEntry = _dlFileEntryService.getFileEntry(documentId);

		return spiCommentResource.postEntityComment(
			fileEntry.getGroupId(), DLFileEntry.class.getName(), documentId,
			comment.getText());
	}

	@Override
	public Comment postStructuredContentComment(
			Long structuredContentId, Comment comment)
		throws Exception {

		SPICommentResource<Comment> spiCommentResource =
			_getSPICommentResource();

		JournalArticle journalArticle = _journalArticleService.getLatestArticle(
			structuredContentId);

		return spiCommentResource.postEntityComment(
			journalArticle.getGroupId(), JournalArticle.class.getName(),
			structuredContentId, comment.getText());
	}

	@Override
	public Comment putComment(Long commentId, Comment comment)
		throws Exception {

		DiscussionPermission discussionPermission = _getDiscussionPermission();

		discussionPermission.checkUpdatePermission(commentId);

		com.liferay.portal.kernel.comment.Comment existingComment =
			_commentManager.fetchComment(commentId);

		try {
			_commentManager.updateComment(
				existingComment.getUserId(), existingComment.getClassName(),
				existingComment.getClassPK(), commentId, StringPool.BLANK,
				StringBundler.concat("<p>", comment.getText(), "</p>"),
				_createServiceContextFunction());

			return CommentUtil.toComment(
				_commentManager.fetchComment(commentId), _commentManager,
				_portal);
		}
		catch (MessageSubjectException messageSubjectException) {
			throw new ClientErrorException(
				"Comment text is null", 422, messageSubjectException);
		}
	}

	private Function<String, ServiceContext> _createServiceContextFunction() {
		return className -> {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			return serviceContext;
		};
	}

	private Page<Comment> _getComments(
			Map<String, Map<String, String>> actions, Long commentId,
			String search, Aggregation aggregation, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			actions,
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				booleanFilter.add(
					new TermFilter(
						"parentMessageId", String.valueOf(commentId)),
					BooleanClauseOccur.MUST);
			},
			filter, MBMessage.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.addVulcanAggregation(aggregation);
				searchContext.setAttribute("discussion", Boolean.TRUE);
				searchContext.setAttribute(
					"searchPermissionContext", StringPool.BLANK);
				searchContext.setCompanyId(contextCompany.getCompanyId());
				searchContext.setVulcanCheckPermissions(false);
			},
			sorts,
			document -> CommentUtil.toComment(
				_commentManager.fetchComment(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))),
				_commentManager, _portal));
	}

	private DiscussionPermission _getDiscussionPermission() {
		return _commentManager.getDiscussionPermission(
			PermissionThreadLocal.getPermissionChecker());
	}

	private SPICommentResource<Comment> _getSPICommentResource() {
		return new SPICommentResource<>(
			_commentManager, contextCompany,
			comment -> CommentUtil.toComment(
				comment, _commentManager, _portal));
	}

	@Reference
	private BlogsEntryService _blogsEntryService;

	@Reference
	private CommentManager _commentManager;

	@Reference
	private DLFileEntryService _dlFileEntryService;

	@Reference
	private JournalArticleService _journalArticleService;

	@Reference
	private Portal _portal;

}