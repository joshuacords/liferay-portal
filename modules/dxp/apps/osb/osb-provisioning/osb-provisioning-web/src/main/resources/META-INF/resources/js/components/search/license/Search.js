/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import {NAMESPACE} from '../../../utilities/constants';
import {
	getLicenseKeySearchFilterDisplayName,
	getSearchParameter,
	getSearchPlaceholder,
	setAdvancedSearchAriaAttributes
} from '../../../utilities/search';
import AdvancedSearch from './AdvancedSearch';

function Search({
	licenseHomeURL = '',
	licenseTypes,
	productVersions,
	products
}) {
	const [keywords, setKeywords] = useState(
		getSearchParameter(`${NAMESPACE}licenseKeySearchKeywords`)
	);
	const [showAdvancedSearch, setShowAdvancedSearch] = useState(false);

	const searchRef = useRef();

	function buildSearchResultsURL() {
		return `${licenseHomeURL}&${NAMESPACE}licenseKeySearchKeywords=${keywords}`;
	}

	function convertSearchParamValuesByName(searchFilters) {
		const formattedSearchFilters = {...searchFilters};

		const productsParamValues = formattedSearchFilters['products'];

		if (productsParamValues) {
			formattedSearchFilters['products'] = extractLabels(
				productsParamValues.split(','),
				products
			);
		}

		const typesParamValues = formattedSearchFilters['types'];

		if (typesParamValues) {
			formattedSearchFilters['types'] = extractLabels(
				typesParamValues.split(','),
				licenseTypes
			);
		}

		return formattedSearchFilters;
	}

	function extractLabels(val, source) {
		const values = new Set(val);

		return source
			.filter(({value}) => values.has(value))
			.map(({label}) => label)
			.join(', ');
	}

	function handleClickOutside(event) {
		const activeDatePicker = document.querySelector(
			'.date-picker-dropdown-menu.show'
		);

		if (
			!activeDatePicker ||
			(activeDatePicker && !activeDatePicker.contains(event.target))
		) {
			setShowAdvancedSearch(false);

			handleOnToggle();
		}
	}

	function handleOnChange(event) {
		setKeywords(event.target.value);
	}

	function handleOnKeyDown(event) {
		if (event.keyCode === 13) {
			handleOnKeywordSearch();
		}
	}

	function handleOnKeywordSearch() {
		window.location.assign(buildSearchResultsURL());
	}

	function handleOnToggle() {
		const newState = !showAdvancedSearch;

		setShowAdvancedSearch(newState);

		setAdvancedSearchAriaAttributes('licenseAdvancedSearchBtn', newState);
	}

	return (
		<div ref={searchRef}>
			<div className="input-group">
				<div className="input-group-item">
					<input
						className="form-control search-input"
						disabled={showAdvancedSearch}
						onChange={handleOnChange}
						onKeyDown={handleOnKeyDown}
						placeholder={getSearchPlaceholder({
							defaultPlaceholder: Liferay.Language.get(
								'search-licenses'
							),
							getFilterDisplayNameCallback: getLicenseKeySearchFilterDisplayName,
							searchFilterProcesser: convertSearchParamValuesByName
						})}
						type=""
						value={keywords}
					/>

					<div className="advanced-search-trigger">
						<button
							aria-controls="advancedSearch"
							aria-expanded="false"
							aria-label={Liferay.Language.get(
								'open-advanced-search'
							)}
							className="advanced-search-btn btn btn-monospaced btn-sm"
							id="licenseAdvancedSearchBtn"
							onClick={handleOnToggle}
						>
							<svg
								aria-label={Liferay.Language.get(
									'advanced-search-icon'
								)}
								className="lexicon-icon lexicon-icon-advanced-search"
								role="image"
							>
								<use xlinkHref="#caret-bottom" />
							</svg>
						</button>
					</div>

					<button
						aria-label={Liferay.Language.get('keyword-search')}
						className="btn btn-default search-btn"
						disabled={showAdvancedSearch}
						onClick={handleOnKeywordSearch}
						role="link"
						type="button"
					>
						<svg
							aria-hidden="true"
							aria-label={Liferay.Language.get('search-icon')}
							className="lexicon-icon lexicon-icon-search"
							role="image"
						>
							<use xlinkHref="#search" />
						</svg>
					</button>
				</div>
			</div>

			{showAdvancedSearch && (
				<AdvancedSearch
					clickOutsideCallback={handleClickOutside}
					formAction={licenseHomeURL}
					licenseTypes={licenseTypes}
					products={products}
					productVersions={productVersions}
					ref={searchRef}
				/>
			)}
		</div>
	);
}

Search.propTypes = {
	licenseHomeURL: PropTypes.string.isRequired,
	licenseTypes: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired,
	productVersions: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired,
	products: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired
};

export default Search;
