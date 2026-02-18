package com.github.d3cryptofc.fastmud.listener;

import com.github.d3cryptofc.fastmud.constant.AroundBlockOffsets;
import com.github.d3cryptofc.fastmud.iterable.GetBlockAroundBlockIterable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class WaterBucketOverDirt implements Listener {

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        /*
         * When player uses water bucket over dirt.
         */
        // Exit if not using water bucket.
        if (event.getBucket() != Material.WATER_BUCKET) return;

        // Get block occupied by water.
        Block blockOccupiedByWater = event.getBlock();

        // Iterating dirts around the block.
        for (Block block : new GetBlockAroundBlockIterable(
            blockOccupiedByWater,
            AroundBlockOffsets.XZB,
            Material.DIRT
        ))
            block.setType(Material.MUD); // Set block type to mud block.
    }
}
