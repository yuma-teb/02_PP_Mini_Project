package controller;

import model.RowRepository;

public class RowController {
    private RowRepository rowRepository = new RowRepository();
    //get
    public int get(){
        return rowRepository.get();
    }

    //update
    public int setRow(int n){
        return rowRepository.setRow(n);
    }
}
