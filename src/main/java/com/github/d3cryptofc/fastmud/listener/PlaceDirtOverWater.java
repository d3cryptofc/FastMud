package com.github.d3cryptofc.fastmud.listener;

import com.github.d3cryptofc.fastmud.constant.AroundBlockOffsets;
import com.github.d3cryptofc.fastmud.iterable.GetBlockAroundBlockIterator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceDirtOverWater implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        /*
         * When dirt is placed over water.
         */
        // Obtaining block placed.
        Block placedBlock = event.getBlock();

        // Exit if block is different of dirt.
        if (placedBlock.getType() != Material.DIRT) return;

        // Get player has permission.
        boolean playerHasPermission = event
            .getPlayer()
            .hasPermission("fastmud.event.place_dirt_over_water");

        // Player has not permission? Finish the function.
        if (!playerHasPermission) return;

        // Check contains any WATER around the placed block.
        if (
            new GetBlockAroundBlockIterator(
                placedBlock,
                AroundBlockOffsets.XZT,
                Material.WATER
            ).hasNext()
        ) placedBlock.setType(Material.MUD); // Set placed block to mud block.
    }
}
