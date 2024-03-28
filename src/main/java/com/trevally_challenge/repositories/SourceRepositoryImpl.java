package com.trevally_challenge.repositories;

import com.trevally_challenge.domain.entities.Source;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public class SourceRepositoryImpl implements SourceRepository {
    /**
     * Inserts the given entity. Assumes the instance to be new to be able to apply insertion optimizations. Use the
     * returned instance for further operations as the save operation might have changed the entity instance completely.
     * Prefer using {@link #save(Object)} instead to avoid the usage of store-specific API.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity
     * @since 1.7
     */
    @Override
    public <S extends Source> S insert(S entity) {
        return null;
    }

    /**
     * Inserts the given entities. Assumes the given entities to have not been persisted yet and thus will optimize the
     * insert over a call to {@link #saveAll(Iterable)}. Prefer using {@link #saveAll(Iterable)} to avoid the usage of store
     * specific API.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities
     * @since 1.7
     */
    @Override
    public <S extends Source> List<S> insert(Iterable<S> entities) {
        return null;
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     *
     * @param example must not be {@literal null}.
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    @Override
    public <S extends Source> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    /**
     * Returns all entities matching the given {@link Example}. In case no match could be found an empty {@link List} is
     * returned. <br />
     * By default the {@link Example} uses typed matching restricting it to probe assignable types. For example, when
     * sticking with the default type key ({@code _class}), the query has restrictions such as
     * <code>_class : &#123; $in : [com.acme.Person] &#125;</code>. <br />
     * To avoid the above mentioned type restriction use an {@link UntypedExampleMatcher} with
     * {@link Example#of(Object, ExampleMatcher)}.
     *
     * @param example
     * @see QueryByExampleExecutor#findAll(Example)
     */
    @Override
    public <S extends Source> List<S> findAll(Example<S> example) {
        return null;
    }

    /**
     * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
     * found an empty {@link List} is returned. <br />
     * By default the {@link Example} uses typed matching restricting it to probe assignable types. For example, when
     * sticking with the default type key ({@code _class}), the query has restrictions such as
     * <code>_class : &#123; $in : [com.acme.Person] &#125;</code>. <br />
     * To avoid the above mentioned type restriction use an {@link UntypedExampleMatcher} with
     * {@link Example#of(Object, ExampleMatcher)}.
     *
     * @param example
     * @param sort
     * @see QueryByExampleExecutor#findAll(Example,
     * Sort)
     */
    @Override
    public <S extends Source> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example  must not be {@literal null}.
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *                 {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    @Override
    public <S extends Source> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    @Override
    public <S extends Source> long count(Example<S> example) {
        return 0;
    }

    /**
     * Checks whether the data store contains elements that match the given {@link Example}.
     *
     * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
     * @return {@literal true} if the data store contains elements that match the given {@link Example}.
     */
    @Override
    public <S extends Source> boolean exists(Example<S> example) {
        return false;
    }

    /**
     * Returns entities matching the given {@link Example} applying the {@link Function queryFunction} that defines the
     * query and its result type.
     *
     * @param example       must not be {@literal null}.
     * @param queryFunction the query function defining projection, sorting, and the result type
     * @return all entities matching the given {@link Example}.
     * @since 2.6
     */
    @Override
    public <S extends Source, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException          in case the given {@literal entity} is {@literal null}.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *                                           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *                                           present but does not exist in the database.
     */
    @Override
    public <S extends Source> S save(S entity) {
        return null;
    }

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
     * as the {@literal Iterable} passed as an argument.
     * @throws IllegalArgumentException          in case the given {@link Iterable entities} or one of its entities is
     *                                           {@literal null}.
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
     *                                           attribute with a different value from that found in the persistence store. Also thrown if at least one
     *                                           entity is assumed to be present but does not exist in the database.
     */
    @Override
    public <S extends Source> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param uuid must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public Optional<Source> findById(UUID uuid) {
        return Optional.empty();
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param uuid must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public List<Source> findAll() {
        return null;
    }

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param uuids must not be {@literal null} nor contain any {@literal null} values.
     * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
     * {@literal ids}.
     * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is {@literal null}.
     */
    @Override
    public List<Source> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    @Override
    public long count() {
        return 0;
    }

    /**
     * Deletes the entity with the given id.
     * <p>
     * If the entity is not found in the persistence store it is silently ignored.
     *
     * @param uuid must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
    @Override
    public void deleteById(UUID uuid) {

    }

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException          in case the given entity is {@literal null}.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *                                           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *                                           present but does not exist in the database.
     */
    @Override
    public void delete(Source entity) {

    }

    /**
     * Deletes all instances of the type {@code T} with the given IDs.
     * <p>
     * Entities that aren't found in the persistence store are silently ignored.
     *
     * @param uuids must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal ids} or one of its elements is {@literal null}.
     * @since 2.5
     */
    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException          in case the given {@literal entities} or one of its entities is {@literal null}.
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
     *                                           attribute with a different value from that found in the persistence store. Also thrown if at least one
     *                                           entity is assumed to be present but does not exist in the database.
     */
    @Override
    public void deleteAll(Iterable<? extends Source> entities) {

    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {

    }

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort the {@link Sort} specification to sort the results by, can be {@link Sort#unsorted()}, must not be
     *             {@literal null}.
     * @return all entities sorted by the given options
     */
    @Override
    public List<Source> findAll(Sort sort) {
        return null;
    }

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@link Pageable} object.
     *
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *                 {@literal null}.
     * @return a page of entities
     */
    @Override
    public Page<Source> findAll(Pageable pageable) {
        return null;
    }
}
