package interface_adapter.finish_round;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverState;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.finish_round.FinishRoundOutputBoundary;
import use_case.finish_round.FinishRoundOutputData;

public class FinishRoundPresenter implements FinishRoundOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameOverViewModel gameOverViewModel;
    private final RoundViewModel roundViewModel;
    private final FinishRoundViewModel finishRoundViewModel;
    public FinishRoundPresenter(ViewManagerModel viewManagerModel,
                                GameOverViewModel gameOverViewModel,
                                RoundViewModel roundViewModel,
                                FinishRoundViewModel finishRoundViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameOverViewModel = gameOverViewModel;
        this.roundViewModel = roundViewModel;
        this.finishRoundViewModel = finishRoundViewModel;
    }

    @Override
    public void prepareGameOverView(FinishRoundOutputData outputData) {
        GameOverState state = this.gameOverViewModel.getState();
        state.setScore(outputData.getScore());
        this.gameOverViewModel.setState(state);

        viewManagerModel.setActiveView(gameOverViewModel.getViewName());
        gameOverViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNextRoundView(FinishRoundOutputData outputData) {
        RoundState roundState = this.roundViewModel.getState();
        roundState.setCurrentRoundNumber(roundState.getCurrentRoundNumber() + 1);
        roundState.setGenre(outputData.getGenre());
        roundState.setCurrentLives(outputData.getLives());
        roundState.setUserAnswer("");
roundState.setMultipleChoiceOptions(createGameOutputData.getMultipleChoiceAnswers());

        this.roundViewModel.setState(roundState);

        finishRoundViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
    }
}
