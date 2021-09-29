package es.udc.fi.dc.fd.rest.dtos;

import es.udc.fi.dc.fd.model.entities.User;

/**
 * The Class UserConversor.
 */
public class UserConversor {

	/**
	 * Instantiates a new user conversor.
	 */
	private UserConversor() {
	}
	

	/**
	 * To user dto.
	 *
	 * @param user the user
	 * @return the user dto
	 */
	public static final UserDto toUserDto(User user) {
		System.out.println(user.getRole());
		return new UserDto(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getRole().toString(), user.getCountry(), user.getCity());
	}

	/**
	 * To user.
	 *
	 * @param userDto the user dto
	 * @return the user
	 */
	public static final User toUser(UserDto userDto) {
		
		User.RoleType role = null;
		
		if (userDto.getRole().toUpperCase().equals("ADMIN")){
			role = User.RoleType.ADMIN;
		}else role = User.RoleType.CLIENT;

		return new User(userDto.getLogin(), userDto.getFirstName(), userDto.getLastName(), userDto.getPassword(), userDto.getEmail(), role,
			 userDto.getCity(), userDto.getCountry());
	}

	/**
	 * To authenticated user dto.
	 *
	 * @param serviceToken the service token
	 * @param user         the user
	 * @return the authenticated user dto
	 */
	public static final AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, User user) {

		return new AuthenticatedUserDto(serviceToken, toUserDto(user));

	}

}

