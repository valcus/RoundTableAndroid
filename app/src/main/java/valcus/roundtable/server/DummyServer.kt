package valcus.roundtable.server

import valcus.roundtable.gameLogic.*
import valcus.roundtable.gameLogic.entities.*

/**
 * Created by Tim Shimp on 2/17/2018.
 */
class DummyServer : Server {

    override fun getGameList(): List<Game> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun joinGame(game: Game, me: Player) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun vote(myVote: Vote) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pick(pick: List<Player>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPickVotes(mission: Mission): List<Vote> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendMission(mission: Mission): List<MissionResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMissionPicks(leader: Player, mission: Mission): Mission {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAssassinGuess(assassin: Player?): Player {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gameEnded(winner: MissionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendInfo(players: List<Player>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}