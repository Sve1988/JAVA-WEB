package com.example.battleships.model.binding;

import javax.validation.constraints.NotNull;

public class HomeBindingModel {

    private Long attackerShip;
    private Long defenderShip;

    public HomeBindingModel() {
    }

    @NotNull
    public Long getAttackerShip() {
        return attackerShip;
    }

    public void setAttackerShip(Long attackerShip) {
        this.attackerShip = attackerShip;
    }

    @NotNull
    public Long getDefenderShip() {
        return defenderShip;
    }

    public void setDefenderShip(Long defenderShip) {
        this.defenderShip = defenderShip;
    }
}
