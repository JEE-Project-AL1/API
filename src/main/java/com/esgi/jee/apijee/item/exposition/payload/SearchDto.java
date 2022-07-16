package com.esgi.jee.apijee.item.exposition.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

@AllArgsConstructor
@Getter
public class SearchDto {
    private ItemDto item;

    private int complexity;

    public static Comparator<SearchDto> ComparatorDifference = new Comparator<SearchDto>() {

        @Override
        public int compare(SearchDto o1, SearchDto o2) {
            return (int) (o1.getComplexity() - o2.getComplexity());
        }

    };
}
