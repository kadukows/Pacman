package com.company.field;

import com.company.Board;
import com.company.Vector2d.ConstVector2d;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;

public class TeleportingField extends AbstractField {
    ConstVector2d ownPos_;
    ConstVector2d target_;

    public TeleportingField(@NotNull Board board, ConstVector2d ownPos, ConstVector2d target) {
        super(board);
        ownPos_ = ownPos.copy();
        target_ = target.copy();
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {

    }

    @Override
    public void onPlayerEnter(Player player) {
        System.out.println("Player is on teleport field, ownPos: " + ownPos_ + ", player pos: " + player.getLocalCenter());
        if (player.getLocalCenter().equals(ownPos_)) {
            player.setLocalCenter(target_);
        }
    }
}
