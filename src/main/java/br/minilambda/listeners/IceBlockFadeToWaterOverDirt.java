package br.minilambda.listeners;

import br.minilambda.utils.GetDirtAroundBlockIterable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class IceBlockFadeToWaterOverDirt implements Listener {

    @EventHandler
    public void onBlockFadeEvent(BlockFadeEvent event) {
        /*
         * When block fade to water (ex: ice block).
         */
        // Getting fadded block.
        Block fadedBlock = event.getBlock();
        // Exit if new block state is different of water.
        if (event.getNewState().getType() != Material.WATER) {
            return;
        }

        // Iterating dirts around the block.
        for (Block block : new GetDirtAroundBlockIterable(fadedBlock)) {
            // Set block type to mud block.
            block.setType(Material.MUD);
        }
    }
}
