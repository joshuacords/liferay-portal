/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayCheckbox} from '@clayui/form';
import React from 'react';

import {toggleShowResolvedComments} from '../../../actions/toggleShowResolvedComments.es';
import useDispatch from '../../../store/hooks/useDispatch.es';
import useSelector from '../../../store/hooks/useSelector.es';

const ResolvedCommentsToggle = () => {
	const dispatch = useDispatch();

	const showResolvedComments = useSelector(
		state => state.showResolvedComments
	);

	const hasResolvedComments = useSelector(state =>
		Object.values(state.fragmentEntryLinks).some(fragmentEntryLink =>
			(fragmentEntryLink.comments || []).some(comment => comment.resolved)
		)
	);

	return (
		<div className="pb-3 px-3">
			<ClayCheckbox
				checked={showResolvedComments}
				disabled={!showResolvedComments && !hasResolvedComments}
				label={Liferay.Language.get('show-resolved-comments')}
				onClick={() => dispatch(toggleShowResolvedComments())}
			/>
		</div>
	);
};

export {ResolvedCommentsToggle};
export default ResolvedCommentsToggle;
