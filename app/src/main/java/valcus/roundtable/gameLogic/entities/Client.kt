package valcus.roundtable.gameLogic.entities

/**
 * Created by Tim Shimp on 2/18/2018.
 */
interface Client {

    fun onVoteFinsihed(accepted: Boolean)

    fun onMissionFinished(passed: Boolean)

    fun onMission(): MissionResult

    fun getAccusation(): Player

    fun onGameEnd(goodWin: Boolean)

    fun onPick(picks: List<Player>): Vote

    fun getPick(): Mission

    fun setRole(role: Int, knowsAbout: List<Player>)
}