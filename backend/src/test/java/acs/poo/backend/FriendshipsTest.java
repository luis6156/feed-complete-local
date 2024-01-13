package acs.poo.backend;

import acs.poo.backend.controllers.FriendshipController;
import acs.poo.backend.errors.FriendshipNotFoundError;
import acs.poo.backend.repositories.FriendshipRepository;
import acs.poo.backend.services.FriendshipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(FriendshipController.class)
public class FriendshipsTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FriendshipService friendshipService;

    @MockBean
    FriendshipRepository friendshipRepository;

    @Test
    void sendFriendRequestTest() throws Exception {
        mockMvc.perform(post("/friendships")
                .param("sender", "abc")
                .param("receiver", "def"));
        verify(friendshipService).createFriendship(any(), any());
    }

    @Test
    void acceptFriendRequestTest() throws Exception {
        doThrow(FriendshipNotFoundError.class).when(friendshipService).acceptFriendship(anyString(), anyString());
        mockMvc.perform(patch("/friendships")
                .param("sender", anyString())
                .param("receiver", anyString()));
        assertThrows(FriendshipNotFoundError.class, () -> friendshipService.acceptFriendship(anyString(), anyString()));
    }
}
