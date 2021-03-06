package ua.kobzev.theatre.repository;

import org.junit.Before;
import org.junit.Test;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.impl.inmemory.UserRepositoryImpl;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 
 * @author kkobziev
 *
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class UserRepositoryTest {

	private UserRepository userRepository;

	private User user;

	private static final String NAME = "user";
	private static final String EMAIL = "smth@smth.com";

	@Before
	public void setUp() {
		userRepository = new UserRepositoryImpl();
		user = new User(EMAIL, NAME, LocalDateTime.now());
	}

	@Test
	public void shouldReturnTrueWhenRegisterUnexistingUser() {
		assertEquals(true, userRepository.register(user));
	}

	@Test
	public void shouldReturnFalseWhenRemoveNullUser() {
		assertEquals(false, userRepository.remove(null));

	}

	@Test
	public void shouldReturnFalseWhenRemoveUnexistinglUser() {
		assertEquals(false, userRepository.remove(user));

	}

	@Test
	public void shouldReturnTrueWhenRemoveExistingUser() {
		userRepository.register(user);
		assertEquals(true, userRepository.remove(user));

	}

	@Test
	public void shouldReturnNullWhenGivenUnexistingId() {
		assertNull(userRepository.getById(0));
	}

	@Test
	public void shouldReturnUserWhenGivenExistingId() {
		userRepository.register(user);
		assertEquals(user, userRepository.getById(user.getId()));
	}

	@Test
	public void shoulReturnNullWhenGivenUnexistingEmail() {
		assertNull(userRepository.getUserByEmail(""));
	}

	@Test
	public void shouldReturnUserWhenGivenExistingEmail() {
		userRepository.register(user);
		assertEquals(user, userRepository.getUserByEmail(EMAIL));
	}

	@Test
	public void shouldReturnEmptyListWhenGivenUnxistingName() {
		assertEquals(0, userRepository.getUsersByName("unExistingName").size());

	}

	@Test
	public void shouldReturnNotEmptyListWhenGivenExistingName() {
		userRepository.register(user);
		assertEquals(1, userRepository.getUsersByName(NAME).size());
	}

	@Test
	public void getBookedTickets() {
		// TODO Auto-generated method stub
		// List<Ticket> getBookedTickets(User user);
		assertNull(null);
	}

}
