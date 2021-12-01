/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import PropTypes from 'prop-types';
import React from 'react';

function ExternalLink({link}) {
	let icon = '#logo-custom-link';

	switch (link.domain) {
		case 'dossiera':
			icon = '#logo-dossiera';
			break;
		case 'salesforce':
			icon = '#logo-salesforce';
			break;
		case 'web':
			icon = '#logo-corp-project';
			break;
		default:
			break;
	}

	return (
		<a className="external-link" href={link.url} target="_blank">
			<svg aria-label={link.label} className="link-logo" role="img">
				<use xlinkHref={icon} />
			</svg>

			<span>
				{link.label}

				{!!link.url && (
					<svg
						aria-label={Liferay.Language.get('external-link')}
						className="lexicon-icon-shortcut"
						role="img"
					>
						<use xlinkHref="#shortcut" />
					</svg>
				)}
			</span>
		</a>
	);
}

function ExternalLinksTabPane({links = []}) {
	return (
		<>
			{links.length > 0 && (
				<div className="external-links-container">
					{links.map(link => (
						<ExternalLink key={link.key} link={link} />
					))}
				</div>
			)}

			{links.length === 0 && (
				<div className="empty-state">
					{Liferay.Language.get('no-external-links-were-found')}
				</div>
			)}
		</>
	);
}

ExternalLinksTabPane.propTypes = {
	links: PropTypes.arrayOf(
		PropTypes.shape({
			domain: PropTypes.string.isRequired,
			entityId: PropTypes.string,
			entityName: PropTypes.string,
			key: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
			url: PropTypes.string
		})
	)
};

export default ExternalLinksTabPane;
