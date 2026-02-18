package com.github.d3cryptofc.fastmud.iterable;

import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GetBlockAroundBlockIterator implements Iterator<Block> {

    // Space for origin block argument.
    private Block fromBlock;
    // Space for relative offsets argument.
    private int[][] relativeOffsets;
    // Space for expected materials argument.
    private Material[] expectedMaterials;

    // Space for the ahead block offset position - computed in hasNext().
    private int aheadBlockOffsetPosition = 0;
    // Space for the current block offset position - follow `aheadBlockOffsetPosition`.
    private int currentBlockOffsetPosition = 0;

    public GetBlockAroundBlockIterator(
        Block fromBlock,
        int[][] relativeOffsets,
        Material... expectedMaterials
    ) {
        // Receives the origin block.
        this.fromBlock = fromBlock;
        // Receives the relative offsets.
        this.relativeOffsets = relativeOffsets;
        // Receives the expected materials.
        this.expectedMaterials = expectedMaterials;
    }

    private boolean containsExpectedMaterial(Material material) {
        // Iterating every expected materials.
        for (Material expectedMaterial : this.expectedMaterials) {
            // If material is the expected.
            if (material == expectedMaterial) {
                // Has expected material, returns true.
                return true;
            }
        }
        // No material matched.
        return false;
    }

    @Override
    public boolean hasNext() {
        // Space for block that will be obtained in the iteration.
        Material blockMaterial;
        // Space for array of integer offsets (X, Y, Z).
        int[] offsets;

        // Iterating around block offsets.
        for (
            // Starts iteration from current block offset position.
            int tempBlockOffsetPosition = this.currentBlockOffsetPosition;
            // Iterates while less than offets length.
            tempBlockOffsetPosition < this.relativeOffsets.length;
            // Increments temporary block offset position.
            tempBlockOffsetPosition++
        ) {
            // Get offsets from current offset position.
            offsets = this.relativeOffsets[tempBlockOffsetPosition];

            // Getting relative block material using offsets.
            blockMaterial = this.fromBlock.getRelative(
                offsets[0],
                offsets[1],
                offsets[2]
            ).getType();

            if (
                // Expected materials passed.
                this.expectedMaterials.length > 0 &&
                // But no contains expected material.
                !this.containsExpectedMaterial(blockMaterial)
            ) {
                // Jump to next block.
                continue;
            }

            // Expected material, then save the temporary block offset ahead.
            this.aheadBlockOffsetPosition = tempBlockOffsetPosition;
            // Expected material, then returns true.
            return true;
        }

        // No expected materials found, returns false.
        return false;
    }

    @Override
    public Block next() {
        // Computed block offset position is ahead of current offset position or equal?
        if (this.aheadBlockOffsetPosition >= this.currentBlockOffsetPosition) {
            // Ensure offset position equality.
            this.currentBlockOffsetPosition = this.aheadBlockOffsetPosition;

            // Get around block offesets by current position offset.
            int[] offsets =
                this.relativeOffsets[this.currentBlockOffsetPosition];

            // Increment +1 to start computing from next offset position.
            this.currentBlockOffsetPosition++;

            // Returns the relative block.
            return this.fromBlock.getRelative(
                offsets[0],
                offsets[1],
                offsets[2]
            );
        }

        // No expected materials available, returns null.
        return null;
    }
}
