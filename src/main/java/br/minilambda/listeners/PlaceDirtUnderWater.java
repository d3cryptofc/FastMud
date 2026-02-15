package br.minilambda.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceDirtUnderWater implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        /*
         * When dirt is placed under water.
         */
        // Getting block placed.
        Block block = event.getBlock();

        // Exit if block is different of dirt.
        if (block.getType() != Material.DIRT) {
            return;
        }

        // Getting top block.
        Block topBlock = block.getRelative(0, 1, 0);

        // Exit if top block is water.
        if (topBlock.getType() != Material.WATER) {
            return;
        }

        // Set block type to mud.
        block.setType(Material.MUD);
    }
}
