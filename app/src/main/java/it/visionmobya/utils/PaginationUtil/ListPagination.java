package it.visionmobya.utils.PaginationUtil;

import java.util.List;

public class ListPagination <T>  {

    private List<T> list;
    private Paginatiable paginatiable;
    private int currentPosition = 0;

    public ListPagination(List<T> list, Paginatiable paginatiable, int currentPosition) {
        this.list = list;
        this.paginatiable = paginatiable;
        this.currentPosition = currentPosition;
        checkNavigationConditions();

    }

    public void invalidate(int currentPosition){
        this.currentPosition = currentPosition;
        checkNavigationConditions();
    }

    private void checkNavigationConditions(){
        if(list!=null){
            if(list.size()<=1){
                paginatiable.hideBoth();
            }
            else{
                if(currentPosition<=0){
                    paginatiable.onFirstIndex();
                }
                else if(currentPosition == list.size()-1){
                    paginatiable.onLastIndex();
                }
                else {
                    paginatiable.showBoth();
                }
            }

        }
    }
}
