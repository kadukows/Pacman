package com.company;

import com.company.Vector2d.ConstVector2d;
import com.company.entities.*;
import com.company.field.AbstractField;
import com.company.field.WalkableField;
import com.company.fieldmatrixfactory.IFieldMatrixFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.stream.StreamSupport;

/**
 * Main class of a game, encompasses update-draw-sleep loop of a game and
 * integrates with java swing ui.
 */
public class Board extends JPanel {
    private static final int BLOCK_SIZE = 35;
    private static final Font FONT = new Font("Purisa", Font.PLAIN, 13);

    private final Matrix<AbstractField> fieldMatrix_;
    private final Player player_;
    private Blinky blinky_;
    private Pinky pinky_;
    private Clyde clyde_;
    private Inky inky_;
    private final KeyboardManager keyboardManager_;
    private final HighscoreManager highscoreManager_;
    private final String levelName_;

    private final Timer timer_;

    private Boolean gameWon_ = null;
    private int score_ = 0;

    Thread threadBlinky;
    Thread threadPinky;
    Thread threadClyde;
    Thread threadInky;


    /**
     * Default constructor for a class.
     *
     * @param fieldMatrixFactory factory for fieldMatrix
     */
    public Board(IFieldMatrixFactory fieldMatrixFactory, KeyboardManager keyboardManager, HighscoreManager highscoreManager, String levelName) {
        fieldMatrix_ = fieldMatrixFactory.create(this);
        keyboardManager_ = keyboardManager;
        highscoreManager_ = highscoreManager;
        levelName_ = levelName;
        double deltaTime = 1 / 60.0;
        player_ = new Player(2, 2, this, deltaTime);

        String jsonName = fieldMatrixFactory.getFilename();
        String s1 = "test1.json";
        String s2 = "test2.json";

        if(jsonName.equals(s1)) {
            blinky_ = new Blinky(9, 9, 255, 0, 0, this, Direction.left, player_, deltaTime);
            Thread threadBlinky = new Thread(blinky_);
        }
        else if(jsonName.equals(s2)){
            blinky_ = new Blinky(15,11,255,0,0,this,  Direction.left, player_,deltaTime);
            Thread threadBlinky = new Thread(blinky_);
        }

        if(jsonName.equals(s1)) {
            pinky_ = new Pinky(8, 11, 243, 0, 255, this, Direction.left, player_, deltaTime);
            Thread threadPinky = new Thread(pinky_);
        }
        else if(jsonName.equals(s2)){
            pinky_ = new Pinky(17,8,243,0,255,this,  Direction.left, player_, deltaTime);
            Thread threadPinky = new Thread(pinky_);
        }

        if(jsonName.equals(s1)) {
            clyde_ = new Clyde(9, 12, 255, 104, 0, this,  Direction.up, player_, deltaTime);
            Thread threadClyde = new Thread(clyde_);
        }
        else if(jsonName.equals(s2)){
            clyde_ = new Clyde(14,8,255,104,0,this,  Direction.right, player_, deltaTime);
            Thread threadClyde = new Thread(clyde_);
        }

        if(jsonName.equals(s1)) {
            inky_ = new Inky(10, 11, 62, 166, 238, this,  Direction.left, player_,deltaTime);
            Thread threadInky = new Thread(inky_);
        }
        else if(jsonName.equals(s2)){
            inky_ = new Inky(15,9,62,166,238,this,  Direction.right, player_, deltaTime);
            Thread threadInky = new Thread(inky_);
        }


        player_.addPlayerMoveListener(this::onPacmanMoved);

        setPreferredSize(new Dimension(
                BLOCK_SIZE * (fieldMatrix_.getWidth() - 2),
                BLOCK_SIZE * (fieldMatrix_.getHeight() - 2)));

        setFocusable(true);

//        threadBlinky.start();
//        threadPinky.start();
//        threadClyde.start();


        timer_ = new Timer(1000 / 60, (ActionEvent avt) -> this.update(deltaTime));
        timer_.start();
    }

    /**
     * Main draw function
     *
     * @param g graphics object used to draw
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0,
                BLOCK_SIZE * fieldMatrix_.getWidth(),
                BLOCK_SIZE * fieldMatrix_.getHeight());

        AffineTransform oldTransform = g2d.getTransform();
        g2d.scale(BLOCK_SIZE, BLOCK_SIZE);
        g2d.translate(-1, -1);

        for (int y = 0; y < fieldMatrix_.getHeight(); ++y) {
            for (int x = 0; x < fieldMatrix_.getWidth(); ++x) {
                fieldMatrix_.get(y, x).draw(g2d, x, y);
            }
        }

        player_.draw(g2d);

        try {
            blinky_.draw(g2d);
        }catch (Exception e){
            System.out.println("Blinky not created");
        }

        try {
            pinky_.draw(g2d);
        }catch (Exception e){
            System.out.println("Pinky not created");
        }

        try {
            clyde_.draw(g2d);
        }catch (Exception e){
            System.out.println("Clyde not created");
        }

        try {
            inky_.draw(g2d);
        }catch (Exception e){
            System.out.println("Inky not created");
        }

        g2d.setTransform(oldTransform);

        /////////////////////////////////////////

        g2d.setFont(FONT);
        g2d.setColor(Color.WHITE);

        g2d.drawString("Score: " + score_, 20, 70);
        g2d.drawString("Highscore: " + highscoreManager_.get(levelName_), 20, 90);

        if (hasGameEnded()) {
            String s = "You have " + (gameWon_ ? "won!" : "lost.");
            g2d.drawString(s, 20, 30);

        }

    }

    /**
     * Helper function for checking if coordinates are in boundaries of matrix.
     *
     * @param point ordinates to check
     * @return true if are on matrix, false otherwise
     */
    private boolean isInBoundaries(ConstVector2d point) {
        return 0 <= point.getX() && point.getX() < fieldMatrix_.getWidth()
                && 0 <= point.getY() && point.getY() < fieldMatrix_.getHeight();
    }

    /**
     * Method used to return field that pacman is on.
     *
     * @param point middle point of pacman
     * @return field if pacman is on matrix, null otherwise
     */
    public AbstractField getField(ConstVector2d point) {
        if (!isInBoundaries(point)) {
            return null;
        }

        return fieldMatrix_.get((int) Math.round(Math.floor(point.getY())), (int) Math.round(Math.floor(point.getX())));
    }

    /**
     * Callback from AbstractFields objects, called when pacman "eats" a consumable.
     * This also checks, if any consumable left, and if not, ends the game with "Win" state.
     *
     * @param f from which field this is called
     */
    public void playerConsumed(AbstractField f) {
        score_ += 1;

        boolean allConsumableSpent = StreamSupport.stream(fieldMatrix_.spliterator(), false).allMatch(field -> {
            if (field instanceof WalkableField) {
                return !((WalkableField) field).hasConsumable();
            }

            return true;
        });

        if (allConsumableSpent) {
            if (highscoreManager_.get(levelName_) < score_) {
                highscoreManager_.put(levelName_, score_);
                highscoreManager_.saveToFile();
            }

            gameWon_ = true;
            timer_.stop();
        }
    }

    /**
     * Callback for when pacman have moved in update - unclear if this should stay.
     */
    public void onPacmanMoved() {

    }

    /**
     * Update step of update-draw-wait loop
     *
     * @param dt delta time
     */
    public void update(double dt) {

        if (!hasGameEnded()) {
            player_.update();
            blinky_.algorithmBlinky();
            pinky_.algorithmPinky();
            clyde_.algorithmClyde();
          inky_.algorithmInky();
            repaint();
        }
    }

    /**
     * Observer for Keyboard Manager
     *
     * @return current Keyboard Manager
     */
    public KeyboardManager getKeyboardManager() {
        return keyboardManager_;
    }

    /**
     * @return true if game ended, false otherwise
     */
    public boolean hasGameEnded() {

        if (Math.abs(player_.getLocalCenter().getX() - blinky_.getLocalCenter().getX()) < 0.1 &&
                Math.abs(player_.getLocalCenter().getY() - blinky_.getLocalCenter().getY()) < 0.1) {
            timer_.stop();
            return gameWon_ = false;
        }
        else if (Math.abs(player_.getLocalCenter().getX() - inky_.getLocalCenter().getX()) < 0.1 &&
                Math.abs(player_.getLocalCenter().getY() - inky_.getLocalCenter().getY()) < 0.1) {
            timer_.stop();
            return gameWon_ = false;
        }
        else if (Math.abs(player_.getLocalCenter().getX() - pinky_.getLocalCenter().getX()) < 0.1 &&
                Math.abs(player_.getLocalCenter().getY() - pinky_.getLocalCenter().getY()) < 0.1) {
            timer_.stop();
            return gameWon_ = false;
        }
        else if (Math.abs(player_.getLocalCenter().getX() - clyde_.getLocalCenter().getX()) < 0.1 &&
                Math.abs(player_.getLocalCenter().getY() - clyde_.getLocalCenter().getY()) < 0.1) {
            timer_.stop();
            return gameWon_ = false;
        }
        else{
            return gameWon_ != null;
        }
    }

    /**
     * Force stops timer and game.
     */
    void forceStop() {
        timer_.stop();
        gameWon_ = false;
    }
}