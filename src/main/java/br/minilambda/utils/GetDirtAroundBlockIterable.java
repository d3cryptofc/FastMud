package br.minilambda.utils;

import java.util.Iterator;
import org.bukkit.block.Block;

public class GetDirtAroundBlockIterable implements Iterable<Block> {

    private Block fromBlock;

    public GetDirtAroundBlockIterable(Block fromBlock) {
        this.fromBlock = fromBlock;
    }

    public Iterator<Block> iterator() {
        return new GetDirtAroundBlockGenerator(this.fromBlock);
    }
}
