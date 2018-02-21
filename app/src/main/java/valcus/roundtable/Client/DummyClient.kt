package valcus.roundtable.Client

import valcus.roundtable.gameLogic.entities.*

/**
 * Created by Tim Shimp on 2/20/2018.
 */
class DummyClient : Client {
    override fun onVoteFinsihed(accepted: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMissionFinished(passed: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMission(): MissionResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccusation(): Player {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGameEnd(goodWin: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPick(picks: List<Player>): Vote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPick(): Mission {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRole(role: Int, knowsAbout: List<Player>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}