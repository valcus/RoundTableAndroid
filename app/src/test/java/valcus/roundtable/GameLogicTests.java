package valcus.roundtable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import valcus.roundtable.Client.DummyClient;
import valcus.roundtable.gameLogic.Game;
import valcus.roundtable.gameLogic.Role;
import valcus.roundtable.gameLogic.entities.GameSettings;
import valcus.roundtable.gameLogic.entities.Mission;
import valcus.roundtable.gameLogic.entities.MissionResult;
import valcus.roundtable.gameLogic.entities.Player;
import valcus.roundtable.gameLogic.entities.Server;
import valcus.roundtable.gameLogic.entities.Vote;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Created by Tim Shimp on 2/21/2018.
 */

public class GameLogicTests {

    private GameSettings settings;
    private Server server;
    private List<Vote> successVotes;
    private List<MissionResult> successfulMission;

    @Before
    public void setUp() {
        server = Mockito.mock(Server.class);
        settings = new GameSettings();

        successVotes = new ArrayList<>();
        successVotes.add(new Vote(true));
        successVotes.add(new Vote(true));
        successVotes.add(new Vote(true));
        successVotes.add(new Vote(true));
        successVotes.add(new Vote(true));

        successfulMission = new ArrayList<>();
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
    }

    @Test
    public void gameRunsToCompletionGoodWins() {
        Mockito.when(server.getPickVotes(Mockito.any(Mission.class))).thenReturn(successVotes);
        Mockito.when(server.sendMission(Mockito.any(Mission.class))).thenReturn(successfulMission);
        Mockito.when(server.getMissionPicks(Mockito.any(Player.class), Mockito.any(Mission.class))).thenReturn(new Mission(2));

        List<Role> availableRoles = settings.getAvailableRoles();
        availableRoles.add(Role.GOOD);
        availableRoles.add(Role.GOOD);
        availableRoles.add(Role.GOOD);
        availableRoles.add(Role.GOOD);
        availableRoles.add(Role.EVIL);
        availableRoles.add(Role.EVIL);
        settings.setAvailableRoles(availableRoles);

        Game game = new Game(settings, server);

        game.addPlayer(new Player(new DummyClient()));
        game.addPlayer(new Player(new DummyClient()));
        game.addPlayer(new Player(new DummyClient()));
        game.addPlayer(new Player(new DummyClient()));
        game.addPlayer(new Player(new DummyClient()));
        game.addPlayer(new Player(new DummyClient()));

        Mockito.verify(server, times(3)).getPickVotes(Mockito.any(Mission.class));
        Mockito.verify(server, times(3)).sendMission(Mockito.any(Mission.class));
        Mockito.verify(server, times(1)).gameEnded(MissionResult.PASS);
    }

    @Test
    public void testAddition() {
        assertEquals(4, 2 + 2);
    }
}
