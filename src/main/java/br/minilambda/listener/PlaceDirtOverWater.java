package br.minilambda.listener;

import br.minilambda.constant.AroundBlockOffsets;
import br.minilambda.utils.GetBlockAroundBlockIterator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceDirtOverWater implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        /*
         * When dirt is placed under water.
         */
        // Getting block placed.
        Block placedBlock = event.getBlock();

        // Exit if block is different of dirt.
        if (placedBlock.getType() != Material.DIRT) {
            return;
        }

        // Check contains any WATER around the placed block.
        if (
            new GetBlockAroundBlockIterator(
                placedBlock,
                AroundBlockOffsets.XZT,
                Material.WATER
            ).hasNext()
        ) {
            // Set placed block to mud block.
            placedBlock.setType(Material.MUD);
        }
    }
}
