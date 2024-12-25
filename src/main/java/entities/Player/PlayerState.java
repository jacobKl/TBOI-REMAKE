package entities.Player;

import entities.SpritesheetPart;

interface PlayerState {
    public SpritesheetPart[] getSpritesheetParts(double x, double y, double deltaTime);

    public void setPlayerAttributes(PlayerActivity activity);
}
