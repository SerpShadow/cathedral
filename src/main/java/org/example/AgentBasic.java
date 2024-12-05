package org.example;

import de.fhkiel.ki.cathedral.ai.Agent;
import de.fhkiel.ki.cathedral.game.*;

import java.util.*;

public class AgentBasic implements Agent {

    @Override
    public Optional<Placement> calculateTurn(Game game, int i, int i1) {

        /** Player */
        Color currentPlayer = game.getCurrentPlayer();
        Color currentPlayerOwned = currentPlayer.subColor();

        /** Opponent */
        Color opponent = currentPlayer.opponent();
        Color opponentOwned = opponent.subColor();

        Board board = game.getBoard();
        List<Building> placeableBuildings = game.getPlacableBuildings();
        System.out.println("placeableBuildings: " + placeableBuildings);


        System.out.println(Arrays.deepToString(board.getField()));
        System.out.println();

        Optional rememberPlacement;
        long ogOwned = countFieldsPerColor(board, currentPlayerOwned);
//        Rating ratingCurrent = getRating(board, currentPlayer, new Rating());

        rememberPlacement = game.getPlacableBuildings().stream()
                .flatMap(building -> building.getPossiblePlacements(game).stream())
                .map(placement -> {
                    board.placeBuilding(placement);
                    game
                    Turn turn = new Turn(ogOwned, placement, , board)


                    return placement;
                })
                .findFirst();

//        for (Building building : placeableBuildings) {
//            Set<Placement> possiblePlacements = building.getPossiblePlacements(board);
//
//            for (Placement placement : possiblePlacements) {
//                System.out.println(placement);
//
//                Board boardCopy = board.copy();
//
//                boolean buildingPlaced = boardCopy.placeBuilding(placement);
//                if (buildingPlaced) {
//                    Rating rating = getRating(boardCopy, currentPlayer, ratingCurrent);
//                    System.out.println("scoreCalc: " + score);
//                    if (score >= scoreHigh) {
//                        rememberPlacement = placement;
//                        scoreHigh = score;
//                    }
//                }
//            }
//        }



        if (rememberPlacement == null) {
            return Optional.empty();
        }
        return Optional.of(rememberPlacement);
    }

    private Optional xxx(Game game) {
        game.getPlacableBuildings().stream()
                .flatMap(building -> building.getPossiblePlacements(game).stream())
                .map(placement -> {
                    game.takeTurn(placement);
                    return true;
                })
                .findFirst();
    }



    private long countFieldsPerColor(Board board, Color color) {
        return Arrays.stream(board.getField())
                .flatMap(Arrays::stream)
                .filter(c -> c == color)
                .count();
    }

    private long countOwned(Board board, Color player) {
        return Arrays.stream(board.getField())
                .flatMap(Arrays::stream)
                .filter(color -> color == player.subColor())
                .count();
    }

    record Turn (long claimedArea,Placement placement, long newClaimedArea, Board board) {};

    private Rating getRating(Board boardCopy, Color currentPlayer, Score scoreCurrent) {
        Color[][] field = boardCopy.getField();

        Score score = new Score();
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
//                System.out.print(field[x][y]);
                if (field[x][y] == currentPlayer) {
                    score.increasePlacements();
                } else if (field[y][x] == currentPlayer.subColor()) {
                    // größerer einschluss bewerten
                    score.increaseInclusions();
                }
            }
//            System.out.println();
        }
        return new Rating(scoreCurrent, score);
    }

    private double calcRating(Score scoreCurrent, Score scoreNew) {
        int placements = scoreNew.getPlacements() - scoreCurrent.getPlacements();
        double inclusions = scoreNew.getInclusions() - scoreCurrent.getInclusions();

        if (inclusions >=3) {
            inclusions *= 2;
        } else if (inclusions >= 2) {
            inclusions *= 1.5;
        }


        return placements + inclusions ;
    }
}


//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field[x].length; y++) {
//                if (field[x][y] == Color.None) {
//                    // try every building
//                    for (Building building : placeableBuildings) {
//                        switch (building.getTurnable()) {
//                            case Full:
//                                // test 270, 180
//                            case Half:
//                                // test 90
//                            case No:
//                                //test 0
//                                break;
//                            default:
//                                // error
//                                break;
//
//                        }
//                    }
//                }
//            }
//        }

