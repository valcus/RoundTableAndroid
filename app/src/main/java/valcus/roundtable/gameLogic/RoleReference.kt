package valcus.roundtable.gameLogic

/**
 * Created by Tim Shimp on 2/17/2018.
 */
const val ROLE_GOOD = 0
const val ROLE_EVIL = 1
const val ROLE_COMMANDER = 2
const val ROLE_ASSASSIN = 3

object RoleReference {
    fun knows(me: Int, them: Int) = when(me) {
        ROLE_GOOD -> false
        ROLE_EVIL, ROLE_ASSASSIN -> when(them) {
            ROLE_EVIL -> true
            ROLE_ASSASSIN -> true
            else -> false
        }
        ROLE_COMMANDER -> when(them) {
            ROLE_EVIL -> true
            ROLE_ASSASSIN -> true
            else -> false
        }
        else -> false
    }

    fun getAlignment(role: Int?) = when(role) {
        ROLE_GOOD, ROLE_COMMANDER -> ROLE_GOOD
        ROLE_EVIL, ROLE_ASSASSIN -> ROLE_EVIL
        else -> -1
    }
}