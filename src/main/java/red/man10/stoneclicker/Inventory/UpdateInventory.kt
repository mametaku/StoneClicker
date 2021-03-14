package red.man10.stoneclicker.Inventory

import me.nikl.nmsutilities.NmsUtility
import org.bukkit.entity.Player
import red.man10.stoneclicker.StoneClicker.Companion.prefix

object UpdateInventory{
    private val nms : NmsUtility? = null
    fun UpdateInventory(p : Player,LongStoneCount : Long ){
        nms?.updateInventoryTitle(p,"$prefix ${LongStoneCount}個")
    }
}