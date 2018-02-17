package valcus.roundtable.gameLogic;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Tim Shimp on 2/17/2018.
 */

public class Game {

    private UUID gameId;
    private List<Player> players;
    private List<Mission> missions;
    private List<Integer> roles;

    public Game() {
        gameId = UUID.randomUUID();
    }

    public void setup(List<Integer> roles) {
        //todo broadcast playercount and roles
        this.roles = roles;
    }

    public void assignRoles(List<Player> players) {
        Random rand = new Random();
        for (Player p : players) {
            int roleIndex = rand.nextInt(roles.size());
            p.setRole(roles.get(roleIndex));
            roles.remove(roleIndex);
        }
    }
}
