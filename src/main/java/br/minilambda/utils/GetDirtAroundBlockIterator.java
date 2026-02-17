package br.minilambda.utils;

import br.minilambda.Constants;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GetDirtAroundBlockIterator implements Iterator<Block> {

    // Space for origin bloock.
    private Block fromBlock;

    // Space for the ahead block offset position - computed in hasNext().
    private int aheadBlockOffsetPosition = 0;
    // Space for the current block offset position - follow `aheadBlockOffsetPosition`.
    private int currentBlockOffsetPosition = 0;

    public GetDirtAroundBlockIterator(Block fromBlock) {
        // Receives the origin block.
        this.fromBlock = fromBlock;
    }

    @Override
    public boolean hasNext() {
        // Space for block that will be obtained in the iteration.
        Block block;
        // Space for array of integer offsets (X, Y, Z).
        int[] offsets;

        // Iterating around block offsets.
        for (
            // Starts iteration from current block offset position.
            int tempBlockOffsetPosition = this.currentBlockOffsetPosition;
            // Iterates while less than offets length.
            tempBlockOffsetPosition < Constants.AROUND_BLOCK_OFFSETS.length;
            // Increments temporary block offset position.
            tempBlockOffsetPosition++
        ) {
            // Get offsets from current offset position.
            offsets = Constants.AROUND_BLOCK_OFFSETS[tempBlockOffsetPosition];

            // Getting relative block using offsets.
            block = this.fromBlock.getRelative(
                offsets[0],
                offsets[1],
                offsets[2]
            );

            // Jump to next iteration if block is different of dirt.
            if (block.getType() != Material.DIRT) continue;

            // Is a dirt, then save the temporary block offset ahead.
            this.aheadBlockOffsetPosition = tempBlockOffsetPosition;

            // Has dirt, returns true.
            return true;
        }

        // No dirts found, returns false.
        return false;
    }

    @Override
    public Block next() {
        // Computed block offset position is ahead of current offset position or equal?
        if (this.aheadBlockOffsetPosition >= this.currentBlockOffsetPosition) {
            // Ensure offset position equality.
            this.currentBlockOffsetPosition = this.aheadBlockOffsetPosition;

            // Get around block offesets by current position offset.
            int[] offsets = Constants
                .AROUND_BLOCK_OFFSETS[this.currentBlockOffsetPosition];

            // Increment +1 to start computing from next offset position.
            this.currentBlockOffsetPosition++;

            // Returns the relative block.
            return this.fromBlock.getRelative(
                offsets[0],
                offsets[1],
                offsets[2]
            );
        }

        // No dirts available, returns null.
        return null;
    }
}
