# Search Pre-Filter Duplication Findings

Line numbers refer to branch `Search_Prefilter_Duplication` after the fix commit, unless marked "before fix".

## Symptom

- Each per-model clause under the `entryClassName` should-filter has its model pre-filters twice.
- Example: Layout gets the `type` terms filter, the `ctCollectionId` must-not-exists filter, and the `status` should-bool twice.
- The duplicates show up in both `query` and `highlight.highlight_query`. The highlight query reuses the same query object, so it is one bug, not two.

## Cause

`PreFilterContributorHelperImpl._createPreFilterForEntryClassName` builds each per-model clause:

- `PreFilterContributorHelperImpl.java:216` adds the `entryClassName` term, then:
    1. `_addPermissionFilter`
    1. `_addIndexerProvidedPreFilters` (`PreFilterContributorHelperImpl.java:227`), which calls `indexer.postProcessContextBooleanFilter(...)` (`PreFilterContributorHelperImpl.java:140`) and the `IndexerPostProcessor`s.
    1. Before fix: `_addModelProvidedPreFilters` ran unconditionally (`PreFilterContributorHelperImpl.java:228` before fix). It runs every `ModelPreFilterContributor` for the class name, plus the mandatory ones.

For a `DefaultIndexer` (any `ModelSearchConfigurator` model: Layout, BlogsEntry, JournalArticle, and others), step 2 already runs step 3:

- `DefaultIndexer.postProcessContextBooleanFilter` → `IndexerQueryBuilderImpl.addPreFiltersFromModel` (`DefaultIndexer.java:248`)
- → `PreFilterContributorHelper.contribute(booleanFilter, modelSearchSettings, searchContext)` (`IndexerQueryBuilderImpl.java:115`)
- → `_addModelProvidedPreFilters` (`PreFilterContributorHelperImpl.java:84`)

So every model pre-filter contributor ran twice in the same `BooleanFilter`.

Legacy indexers are different:

- `BaseIndexer.postProcessContextBooleanFilter` does nothing (`BaseIndexer.java:367`).
- Legacy models got their model pre-filters only once, from step 3.

Contributors behind the Layout duplicates:

- `type` terms: `LayoutModelPreFilterContributor.java:54`
- `status` should-bool (published, or the launch-preview draft branch with `status=2` and `entryClassPK`): `LayoutModelPreFilterContributor.java:94`, built by `_getStatusesFilter` at `LayoutModelPreFilterContributor.java:99`
- `ctCollectionId`: mandatory `CTModelPreFilterContributor.java:50` (`indexer.class.name=ALL`, `indexer.clauses.mandatory=true`)

## Not Duplicates: `slop` and `boost` `match_phrase` Pairs

- The `comments`, `content`, `description`, `url`, `assetCategoryTitles_*` and `content_*` fields use `DescriptionFieldQueryBuilder`. That class sets `exact.match.boost=2.0` and `proximity.slop=50` (`DescriptionFieldQueryBuilder.java:25`, under `internal/analysis`).
- `FullTextQueryBuilder.build` (`FullTextQueryBuilder.java:29`, under `internal/analysis`) adds:
    - a MUST match query for the words
    - a SHOULD proximity `match_phrase` with `slop: 50` (`FullTextQueryBuilder.java:85`)
    - a SHOULD exact `match_phrase` with `boost: 2` (`FullTextQueryBuilder.java:56`)
- The two phrase queries score different things: words near each other versus the exact phrase. They are intended, so the fix leaves them unchanged.

## Fix Chosen

`_createPreFilterForEntryClassName` now skips `_addModelProvidedPreFilters` when the indexer is a `DefaultIndexer`, unless the indexer-provided clauses are suppressed (`PreFilterContributorHelperImpl.java:229`).

- `DefaultIndexer`, normal search: one pass, through `DefaultIndexer.postProcessContextBooleanFilter`. That pass uses the real `ModelSearchSettingsImpl` instead of the helper's synthesized one, which only carries `className` and `stagingAware`.
- `DefaultIndexer`, suppressed (`search.full.query.suppress.indexer.provided.clauses`): `_addIndexerProvidedPreFilters` returns early, so the helper still runs `_addModelProvidedPreFilters` with `mandatoryOnly=true`. The mandatory contributors, such as change tracking, are kept.
- Legacy `BaseIndexer`: the behavior does not change. It gets one pass, from the helper.
- `includes`/`excludes`: both paths call the same `ModelPreFilterContributorsRegistry.filterModelPreFilterContributor` with the same `SearchContext` attributes, so filtering is the same. `excludes=*` still forces mandatory-only (`ModelPreFilterContributorsRegistry.java:43`).

### Rejected Option: Remove Pre-Filters From `DefaultIndexer.postProcessContextBooleanFilter`

Other code relies on `Indexer.postProcessContextBooleanFilter` to apply model pre-filters for `DefaultIndexer` models:

- `BaseRelatedEntryIndexer.addRelatedClassNames` (`BaseRelatedEntryIndexer.java:44`) calls it for each related entry class name, for example comments and attachments of a `DefaultIndexer` model. If the option were taken, related-entry clauses would lose model pre-filters such as status and CT.
- `NoAutoCommitIndexer.java:161` delegates to it.
- `CommerceDiscountIndexer.java:233` and `CommerceDiscountIndexer.java:248` call it on discount targets, not on indexers, so they are not affected.
- `FacetedSearcherImpl` and `IndexerQueryBuilderImpl` both reach it only through `PreFilterContributorHelper.contribute(BooleanFilter, Map, SearchContext)` (`FacetedSearcherImpl.java:170`, `IndexerQueryBuilderImpl.java:195`), so the chosen fix covers them.

## Risk to Other Callers

- `IndexerRegistryImpl.getIndexer` returns a `BufferedIndexerInvocationHandler` proxy when an `IndexerRequestBuffer` is active and buffering is on (`IndexerRegistryImpl.java:216`). The proxy fails `instanceof DefaultIndexer`, so those searches keep the old duplicated, but correct, pre-filters. Searches rarely run inside a buffered indexing scope. When they do, the result is the duplication that existed before the fix, and no filters are lost.
- Any third-party indexer that wraps a `DefaultIndexer` behaves the same way: duplicated, not lost.
- Custom `ModelPreFilterContributor`s that are not idempotent, for example ones that count calls or mutate the `SearchContext`, now run once instead of twice per model clause. This is the intended behavior.
