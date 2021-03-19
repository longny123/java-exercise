package coaching.repository;

import java.util.Collection;

/**
 * Generic CRUD repository
 */
public interface CrudRepository<T> {

    /**
     * Save data into persistence storage
     *
     * @param data Data
     */
    void save(Collection<T> data);

    /**
     * Save data into persistence storage in batch
     * When an exception occurs, rollback
     *
     * @param data
     * @return
     */
    void saveBatch(Collection<T> data);

    /**
     * Get all data existing in storage
     *
     * @return Data
     */
    Collection<T> findAll();
}
