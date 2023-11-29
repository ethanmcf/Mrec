package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonGame implements Game {

    private final String ID;
    private final String genre;
    private final String difficulty;
    private final int maxRounds;
    private final int initialLives;
    private int currentLives;
    private int score;
    private final LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private final ArrayList<Round> allRounds = new ArrayList<>();

    public CommonGame(String ID, String genre, String difficulty, int maxRounds, int initialLives, int currentLives,
                      int score, LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.ID = ID;
        this.genre = genre;
        this.difficulty = difficulty;
        this.maxRounds = maxRounds;
        this.initialLives = initialLives;
        this.currentLives = currentLives;
        this.score = score;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public CommonGame(String genre, String difficulty, int maxRounds, int initialLives) {
        this(UUID.randomUUID().toString(), genre, difficulty, maxRounds, initialLives, initialLives,
                0, LocalDateTime.now(), null);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public int getInitialLives() {
        return initialLives;
    }

    @Override
    public int getMaxRounds() { return maxRounds; }

    @Override
    public int getCurrentLives() {
        return currentLives;
    }

    @Override
    public int getRoundsPlayed() {
        return allRounds.size();
    }

    @Override
    public int getScore() {return score;}
    @Override
    public Round getCurrentRound() {
        int roundsSize = allRounds.size();
        if (roundsSize == 0) {
            return null;
        }

        return allRounds.get(roundsSize - 1);
    }

    @Override
    public List<Round> getRounds() { return allRounds; }

    @Override
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public LocalDateTime getFinishedAt() { return finishedAt; }

    @Override
    public void setCurrentLives(int lives) { currentLives = lives; }

    @Override
    public void setScore(int score) { this.score = score; }

    @Override
    public void setCurrentRound(Round round) { allRounds.add(round); }
    @Override
    public void setFinishedAt(LocalDateTime finishedAt) { this.finishedAt = finishedAt; }
    @Override
    public boolean isGameOver() {
        boolean currentRoundIsFinished = getCurrentRound().isFinished();

        return (currentLives <= 0)
                || (allRounds.size() == maxRounds && currentRoundIsFinished)
                || (allRounds.size() > maxRounds)
                || (finishedAt != null);
    }

    @Override
    public void incrementScore() { this.score += 1; }

    @Override
    public void decrementLives() { this.currentLives -= 1; }
}
