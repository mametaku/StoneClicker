package red.man10.stoneclicker.Inventory

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import red.man10.stoneclicker.StoneClicker.Companion.count

object ClickInventory : Listener {
    @EventHandler
    fun onItemClick(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        val q = e.view.title
        if (!q.contains("§e§l[§d§lStoneClicker§e§l]§f")) return
        val slot = e.currentItem?.type
        if (slot == Material.COBBLESTONE){
            val count = count[p]
            count?.plus(1L)
            UpdateInventory.UpdateInventory(p, count!!)
            e.isCancelled
        }
        e.isCancelled
    }
}