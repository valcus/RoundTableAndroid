package valcus.roundtable.gameLogic;

/**
 * Created by Tim Shimp on 2/17/2018.
 */

public class Player {

    long playerId;
    int role;
    String displayName;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
