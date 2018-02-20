package valcus.roundtable.gameLogic

/**
 * Created by Tim Shimp on 2/17/2018.
 */

enum class Role {
    GOOD, EVIL, COMMANDER, ASSASSIN;

    companion object {
        fun knows(me: Role, them: Role) = when (me) {
            GOOD -> false
            EVIL, ASSASSIN -> when (them) {
                EVIL -> true
                ASSASSIN -> true
                else -> false
            }
            COMMANDER -> when (them) {
                EVIL -> true
                ASSASSIN -> true
                else -> false
            }
        }

        fun getAlignment(role: Role) = when (role) {
            GOOD, COMMANDER -> GOOD
            EVIL, ASSASSIN -> EVIL
        }
    }
}