package es.udc.fi.dc.fd.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The Interface UserDao.
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {

	/**
	 * Exists by user name.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 */
	boolean existsByLogin(String login);

	/**
	 * Find by user name.
	 *
	 * @param userName the user name
	 * @return the optional
	 */
	Optional<User> findByLogin(String login);

}
