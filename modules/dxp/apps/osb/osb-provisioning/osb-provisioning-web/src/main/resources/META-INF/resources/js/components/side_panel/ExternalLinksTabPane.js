/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
