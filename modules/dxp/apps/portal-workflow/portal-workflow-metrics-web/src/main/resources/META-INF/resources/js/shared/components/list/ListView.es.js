/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import LoadingState from '../loading/LoadingState.es';
import EmptyState from './EmptyState.es';

/**
 * ListView.
 * @extends React.Component
 */
export default class ListView extends React.Component {
	render() {
		const {
			children,
			className = 'border-1',
			emptyActionButton,
			emptyMessageClassName,
			emptyMessageText,
			emptyTitleText,
			errorMessageClassName = 'small',
			errorMessageText,
			fetching,
			hideAnimation = false,
			loading,
			searching
		} = this.props;

		const errorRender = secondaryRender =>
			errorMessageText ? (
				<EmptyState
					actionButton={emptyActionButton}
					className={className}
					hideAnimation
					message={errorMessageText}
					messageClassName={errorMessageClassName}
					type="error"
				/>
			) : (
				secondaryRender
			);

		const emptyFetchRender = secondaryRender =>
			fetching ? (
				<EmptyState
					className={className}
					hideAnimation={hideAnimation}
					message={emptyMessageText}
					messageClassName={emptyMessageClassName}
					title={emptyTitleText}
				/>
			) : (
				secondaryRender
			);

		const emptySearchRender = secondaryRender =>
			searching ? (
				<EmptyState
					className={className}
					hideAnimation={hideAnimation}
					message={emptyMessageText}
					messageClassName={emptyMessageClassName}
					type="not-found"
				/>
			) : (
				secondaryRender
			);

		const loadingRender = secondaryRender =>
			loading ? (
				<div className={`${className} pb-6 pt-6 sheet`}>
					<LoadingState />
				</div>
			) : (
				secondaryRender
			);

		return (
			<div>
				{loadingRender(
					errorRender(emptySearchRender(emptyFetchRender(children)))
				)}
			</div>
		);
	}
}
