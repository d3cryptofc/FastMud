package br.minilambda.listeners;

import br.minilambda.utils.GetDirtAroundBlockGenerator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class WaterBucketOverDirt implements Listener {

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        /*
         * When player uses water bucket.
         */
        // Exit if not using water bucket.
        if (event.getBucket() != Material.WATER_BUCKET) {
            return;
        }

        // Get block occupied by water.
        Block blockOccupiedByWater = event.getBlock();

        // To get dirt blocks around.
        GetDirtAroundBlockGenerator dirtBlocks =
            new GetDirtAroundBlockGenerator(blockOccupiedByWater);
        Block block;

        // Iterating dirt blocks around.
        while ((block = dirtBlocks.next()) != null) {
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
