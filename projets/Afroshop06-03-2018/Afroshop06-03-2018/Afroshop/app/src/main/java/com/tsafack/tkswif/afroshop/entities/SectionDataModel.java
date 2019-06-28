package com.tsafack.tkswif.afroshop.entities;

import java.util.ArrayList;

/**
 * Created by Stephane on 7/22/2017.
 */

public class SectionDataModel {
    private String headerTitle;
    private ArrayList<gridViewModel> allItemsInSection;

    public SectionDataModel() {
        super();
    }


    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<gridViewModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<gridViewModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
