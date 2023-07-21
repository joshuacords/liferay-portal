/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {PageContent} from './PageContent.es';

const PageContents = props => {
	return (
		<ul className="list-unstyled">
			{props.pageContents.map(pageContent => (
				<PageContent key={pageContent.classPK} {...pageContent} />
			))}
		</ul>
	);
};

PageContents.propTypes = {
	pageContents: PropTypes.arrayOf(
		PropTypes.shape({
			classPK: PropTypes.string
		})
	)
};

export {PageContents};
export default PageContents;
