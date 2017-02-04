package com.airone.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager
{
    private Stack<State> stateStack;

    public GameStateManager(){
        stateStack = new Stack<State>();
    }

    public void push(State state){
        stateStack.push(state);
    }

    public void pop(){
        stateStack.pop().dispose();
    }

    public void set(State state){
        stateStack.pop().dispose();
        stateStack.push(state);
    }

    public void update(float delta){
        stateStack.peek().Update(delta);
    }

    public void render(SpriteBatch sb){
        stateStack.peek().render(sb);
    }
}
