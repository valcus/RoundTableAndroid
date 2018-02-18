package valcus.roundtable.gameLogic.entities

/**
 * Created by Tim Shimp on 2/18/2018.
 */
interface Client {

    fun onVoteFinsihed(accepted: Boolean)

    fun onMissionFinished(passed: Boolean)

    fun onPick(picks: List<Player>)
}