package com.liferay.portal.search.tuning.rankings.web.internal.index.creation;

import org.osgi.service.component.annotations.Component;


/**
 * @author Joshua Cords
 */
@Component(service = {})
public class ResultRankingsElasticsearchConfigurationObserver {
//	implements ElasticsearchConfigurationObserver {
//
//	@Activate
//	protected void activate(BundleContext bundleContext) {
////		_elasticsearchConfigurationWrapper.register(this);
////change pattern from registering to the wrapper finding all components
//		//_createCompanyIndexes();
//	}
//	@Override
//	public int getPriority() {
//		return 0;
//	}
//
//	@Override
//	public void onElasticsearchConfigurationUpdate() {
//		if (_singleIndexToMultipleIndexImporter.needImport()) {
//			_singleIndexToMultipleIndexImporter.importRankings();
//		}
//	}
//
//	@Override
//	public int compareTo(
//		ElasticsearchConfigurationObserver elasticsearchConfigurationObserver) {
//
////		return _elasticsearchConfigurationWrapper.compare(
////			this, elasticsearchConfigurationObserver);
//		return 1;
//	}
//
////	@Reference
////	private volatile ElasticsearchConfigurationWrapper
////		_elasticsearchConfigurationWrapper;
//
//	@Reference
//	private SingleIndexToMultipleIndexImporter
//		_singleIndexToMultipleIndexImporter;
}
