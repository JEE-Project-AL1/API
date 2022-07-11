package com.esgi.jee.apijee.apiComment.apiJee.Item.dto;

import com.esgi.jee.apijee.apiComment.apiJee.Item.domain.Item;

import java.util.Comparator;

public class SearchDto {
    private Item item;
    private int difference;

    public SearchDto(Item item, int difference) {
        this.item = item;
        this.difference = difference;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public static Comparator<SearchDto> ComparatorDifference = new Comparator<SearchDto>() {

        @Override
        public int compare(SearchDto o1, SearchDto o2) {
            return (int) (o1.getDifference() - o2.getDifference());
        }

    };
}
