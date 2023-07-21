/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.comment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Adolfo Pérez
 */
@ProviderType
public interface DiscussionComment extends Comment {

	public List<DiscussionComment> getDescendantComments();

	public int getDescendantCommentsCount();

	public DiscussionComment getParentComment() throws PortalException;

	public RatingsEntry getRatingsEntry();

	public RatingsStats getRatingsStats();

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getDescendantComments()}
	 */
	@Deprecated
	public List<DiscussionComment> getThreadComments();

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getDescendantCommentsCount()}
	 */
	@Deprecated
	public int getThreadCommentsCount();

	public DiscussionCommentIterator getThreadDiscussionCommentIterator();

	public DiscussionCommentIterator getThreadDiscussionCommentIterator(
		int from);

	public boolean isInTrash();

}