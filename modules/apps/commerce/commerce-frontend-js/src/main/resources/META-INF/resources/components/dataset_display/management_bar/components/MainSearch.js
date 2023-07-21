/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Icon from '@clayui/icon';
import classNames from 'classnames';
import React, {useContext, useEffect, useState} from 'react';

import DatasetDisplayContext from '../../DatasetDisplayContext';

function MainSearch() {
	const {searchParam, updateSearchParam} = useContext(DatasetDisplayContext);

	const [inputValue, updateInputValue] = useState(searchParam);

	useEffect(() => {
		updateInputValue(searchParam || '');
	}, [searchParam]);

	function handleKeyDown(e) {
		if (e.keyCode === 13) {
			e.preventDefault();

			return updateSearchParam(inputValue);
		}
	}

	return (
		<div className="d-inline">
			<div className="input-group">
				<div className="input-group-item">
					<div className="main-input-wrapper">
						<input
							className="form-control input-group-inset input-group-inset-after main-input-search"
							onChange={e => updateInputValue(e.target.value)}
							onKeyDown={handleKeyDown}
							placeholder={Liferay.Language.get('search')}
							type="text"
							value={inputValue}
						/>

						<button
							className={classNames(
								'main-input-reset-button btn btn-unstyled',
								!inputValue.length && 'd-none'
							)}
							disabled={!inputValue.length}
							onClick={e => {
								e.preventDefault();
								updateInputValue('');

								return updateSearchParam('');
							}}
							type="button"
						>
							<Icon symbol="times-circle" />
						</button>
					</div>

					<span className="input-group-inset-item input-group-inset-item-after">
						<button
							className="btn btn-unstyled"
							onClick={e => {
								e.preventDefault();

								return updateSearchParam(inputValue);
							}}
							type="button"
						>
							<Icon symbol="search" />
						</button>
					</span>
				</div>
			</div>
		</div>
	);
}

export default MainSearch;
