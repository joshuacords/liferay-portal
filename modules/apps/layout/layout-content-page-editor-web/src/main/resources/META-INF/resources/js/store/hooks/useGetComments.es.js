/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import useSelector from './useSelector.es';

function useGetComments() {
	const showResolvedComments = useSelector(
		state => state.showResolvedComments
	);

	return fragmentEntryLink =>
		(fragmentEntryLink.comments || []).filter(
			comment => showResolvedComments || !comment.resolved
		);
}
export {useGetComments};
export default useGetComments;
