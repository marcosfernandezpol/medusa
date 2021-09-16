package es.udc.fi.dc.fd.model.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class User.
 */

@Entity
public class User {

	/**
	 * The Enum RoleType.
	 */
	public enum RoleType {
		/** The user. */
		ADMIN,
		CLIENT
	}

	/** The id. */
	private Long id;

	/** The user login. */
	private String login;
	
	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;
	
	/** The password. */
	private String password;

	/** The city. */
	private String city;
	
	/** The country. */
	private String country;

	/** The role. */
	private RoleType role;

	
	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param login     the user login
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param password  the password
	 * @param city      the city
	 * @param country   the country
	 */
	
	public User(String login, String userName, String firstName, String lastName, String password, String city, String country) {

		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.city = city;
		this.country = country;

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public RoleType getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(RoleType role) {
		this.role = role;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, firstName, id, lastName, login, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", city=" + city + ", country=" + country
				+ ", role=" + role + "]";
	}

	
}