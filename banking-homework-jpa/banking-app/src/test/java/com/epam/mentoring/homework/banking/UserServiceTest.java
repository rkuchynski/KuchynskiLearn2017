package com.epam.mentoring.homework.banking;

import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.repository.embedded.api.IUserRepository;
import com.epam.mentoring.homework.banking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

/**
 * Test class for {@link UserService}.
 * <p/>
 * Date: 06/30/2017
 *
 * @author Raman Kuchynski
 */
@RunWith(PowerMockRunner.class)
public class UserServiceTest {

    private static final long USER_ID = 1L;
    private static final String USER_NAME = "User";

    private IUserRepository userRepository;
    private UserService userService;
    private User user;

    @Before
    public void init() {
        userRepository = PowerMock.createMock(IUserRepository.class);
        userService = new UserService();
        Whitebox.setInternalState(userService, "userRepository", userRepository);
        user = createUser();
    }

    @Test
    public void testRead() {
        expect(userRepository.read(USER_ID)).andReturn(user).once();
        replayAll();
        User result = userService.read(USER_ID);
        verifyAll();
        assertEquals(user, result);
    }

    @Test
    public void testReadIfNotFound() {
        expect(userRepository.read(USER_ID)).andReturn(null).once();
        replayAll();
        User result = userService.read(USER_ID);
        verifyAll();
        assertNull(result);
    }

    @Test
    public void testStore() {
        expect(userRepository.store(user)).andReturn(user).once();
        replayAll();
        User result = userService.store(user);
        verifyAll();
        assertEquals(user, result);
    }

    @Test
    public void testUpdate() {
        expect(userRepository.update(user)).andReturn(user).once();
        replayAll();
        User result = userService.update(user);
        verifyAll();
        assertEquals(user, result);
    }

    @Test
    public void testDelete() {
        expect(userRepository.delete(USER_ID)).andReturn(user).once();
        replayAll();
        User result = userService.delete(USER_ID);
        verifyAll();
        assertEquals(user, result);
    }

    private User createUser() {
        User result = new User();
        result.setUserId(USER_ID);
        result.setName(USER_NAME);
        return result;
    }
}
