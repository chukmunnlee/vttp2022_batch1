package vttp2022.paf.day11;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import vttp2022.paf.day11.models.Comment;
import vttp2022.paf.day11.models.Game;
import vttp2022.paf.day11.repositories.GameRepository;
import vttp2022.paf.day11.services.GameService;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameSvc;

    // We are mocking gameRepo
    @MockBean
    private GameRepository gameRepo;

    @Test
    public void shouldReturn200() {
        int gid = 1;

        // Mock the GameRepository so this test will pass
        Game game = new Game();
        game.setName("abc");

        List<Comment> comments = new LinkedList<>();
        Comment c = new Comment();
        c.setGameId(gid);
        comments.add(c);

        Mockito.when(gameRepo.getGameByGid(gid))
            .thenReturn(Optional.of(game));
        Mockito.when(gameRepo.getCommentsByGid(gid))
            .thenReturn(comments);

        Optional<Game> opt = gameSvc.getComments(gid);

        assertTrue(opt.isPresent(), "Should found gid %d".formatted(gid));
    }
    
    @Test
    public void shoudlReturnOptionalEmpty() {
        int gid = -500;
        // The method to mock
        Mockito.when(gameRepo.getGameByGid(gid))
            .thenReturn(Optional.empty());

        // This will now use the mock
        Optional<Game> opt = gameSvc.getComments(gid);

        assertFalse(opt.isPresent(), "Should not find gid %d".formatted(gid));
    }
    
}
