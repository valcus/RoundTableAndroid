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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Tim Shimp on 2/21/2018.
 */

public class GameLogicTests {

    private GameSettings settings;
    private Server server;
    private List<Vote> successVotes;
    private List<Vote> rejectVotes;
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

        rejectVotes = new ArrayList<>();
        rejectVotes.add(new Vote(false));
        rejectVotes.add(new Vote(false));
        rejectVotes.add(new Vote(false));
        rejectVotes.add(new Vote(false));
        rejectVotes.add(new Vote(false));

        successfulMission = new ArrayList<>();
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
        successfulMission.add(MissionResult.PASS);
    }

    @Test
    public void gameRunsToCompletionGoodWins() {
        when(server.getPickVotes(any(Mission.class))).thenReturn(successVotes);
        when(server.sendMission(any(Mission.class))).thenReturn(successfulMission);
        when(server.getMissionPicks(any(Player.class), any(Mission.class))).thenReturn(new Mission(2));

        Game game = setupBaseGame();

        verify(server, times(3)).getPickVotes(any(Mission.class));
        verify(server, times(3)).sendMission(any(Mission.class));
        verify(server, times(1)).gameEnded(MissionResult.PASS);
    }

    @Test
    public void gameEndByMissionReject() {
        when(server.getPickVotes(any(Mission.class))).thenReturn(rejectVotes);
        when(server.getMissionPicks(any(Player.class), any(Mission.class))).thenReturn(new Mission(2));

        Game game = setupBaseGame();

        verify(server, times(5)).getPickVotes(any(Mission.class));
        verify(server, times(1)).gameEnded(MissionResult.FAIL);
        verify(server, times(0)).sendMission(any(Mission.class));
    }

    private Game setupBaseGame() {
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

        return game;
    }
}
