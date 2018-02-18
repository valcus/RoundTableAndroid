package valcus.roundtable.server

import valcus.roundtable.gameLogic.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */
class DummyServer : Server {
    override fun connect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGameList(): MutableList<Game> {
        val gameList : MutableList<Game> = ArrayList<Game>()
        val roleList : MutableList<Int> = ArrayList<Int>()
        roleList.add(ROLE_EVIL)
        roleList.add(ROLE_ASSASSIN)
        roleList.add(ROLE_GOOD)
        roleList.add(ROLE_GOOD)
        roleList.add(ROLE_COMMANDER)
        gameList.add(Game(5, roleList))
        return gameList
    }

    override fun joinGame(game: Game, me: Player) {
        game.addPlayer(me)
    }

    override fun vote(myVote: Vote) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pick(pick: List<Player>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMissionResult(): List<Vote> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMissionPicks(): List<Player>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRole(): List<Player> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}