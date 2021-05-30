package com.company.field;

import com.company.Board;
import com.company.Vector2d.ConstVector2d;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * Class responsible for teleporting Player
 */
public class TeleportingField extends AbstractField {
    ConstVector2d ownPos_;
    ConstVector2d target_;

    /**
     * Constructor for class.
     *
     * @param board Board this field is assigned to
     * @param ownPos center of this field - this is used to determine when player should be teleported
     * @param target point ot which teleport player
     */
    public TeleportingField(@NotNull Board board, ConstVector2d ownPos, ConstVector2d target) {
        super(board);
        ownPos_ = ownPos.copy();
        target_ = target.copy();
    }

    /**
     * Allows player to walk onto this field.
     *
     * @return true
     */
    @Override
    public boolean isWalkable() {
        return true;
    }

    /**
     * This field should not be visible to player, so this draws nothing.
     */
    @Override
    public void draw(Graphics2D g, int x, int y) {

    }

    /**
     * Teleports when Player is fully on this field
     *
     * @param player Player that is on this specific field
     */
    @Override
    public void onPlayerEnter(Player player) {
        if (player.getLocalCenter().equals(ownPos_)) {
            player.setLocalCenter(target_);
        }
    }
}
