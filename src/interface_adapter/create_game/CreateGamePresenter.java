package interface_adapter.create_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.create_game.CreateGameOutputBoundary;
import use_case.create_game.CreateGameOutputData;
import view.RoundView;

public class CreateGamePresenter implements CreateGameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;

    public CreateGamePresenter (ViewManagerModel viewManagerModel, RoundViewModel roundViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
    }

    @Override
    public void prepareFirstRoundView(CreateGameOutputData createGameOutputData) {

        RoundState roundState = roundViewModel.getState();

        roundState.setGameId(createGameOutputData.getGameId());

        roundState.setCurrentRoundNumber(1); // assuming counting starts at 1
        roundState.setMaxRounds(createGameOutputData.getRounds());
        roundState.setGenre(createGameOutputData.getGenre());
        roundState.setCurrentLives(createGameOutputData.getLives());
        roundState.setInitialLives(createGameOutputData.getLives());

        viewManagerModel.setActiveView(roundViewModel.getViewName());

        roundViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
