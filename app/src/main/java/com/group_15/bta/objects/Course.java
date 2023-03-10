package com.group_15.bta.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for Courses object
 * used to store the course id, description, and sections that are available for this course
 */
public class Course implements Serializable {

    private final String ID; //course id
    private String Description;//course description
    protected ArrayList<Section> sections; //sections for this course
    private final String title;
    private String associatedCategory;
    private String associatedDegree;
    HashMap<String, String> degrees;
    private int creditHours;
    private double tuition;

    //constructor
    public Course(String ID, String title) {
        this.title = title;
        this.ID = ID;
    }

    public Course(String courseID, String courseTitle, String courseDescription, int credit, String associatedCategory, double tuition, String associatedDegree) {
        sections = new ArrayList<>();
        ID = courseID;
        title = courseTitle;
        Description = courseDescription;
        this.associatedCategory = associatedCategory;
        creditHours = credit;
        this.tuition = tuition;
        this.associatedDegree = associatedDegree;
        String[] tempDeg = associatedDegree.split(",");

        degrees = new HashMap<>();
        for (String s : tempDeg) {
            degrees.put(s.trim(), s.trim());
        }
    }

    //constructor
    public Course(String ID, String title, String Description, ArrayList<Section> sections) {
        this.ID = ID;
        this.title = title;
        this.Description = Description;
        this.sections = sections;
    }

    //adds section to this course
    public void addSection(String sectionNumber, Section.availableSectionDays[] Days, Section.availableSectionTimes Time, int CAP) {
        Section S = new Section(sectionNumber, Days, Time, CAP);
        sections.add(S);
    }

    public void addSection(Section newSection) {
        sections.add(newSection);
    }

    //getters
    public String getAssociatedDegree() {
        return associatedDegree;
    }

    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public String getID() {
        return this.ID;
    }

    public String getAssociatedCategory() {
        return associatedCategory;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public double getTuition() { return tuition; }

    /**
     * associatedWithDegree
     * checks if this course is associated with a particular degree
     * @param newDegree - degree to check
     * @return - true if this course is associated with the degree, false if not
     */
    public boolean associatedWithDegree(String newDegree)
    {
        return degrees.get(newDegree) != null;
    }

    //equals
    @Override
    public boolean equals(final Object o)
    {
        return ID.equals(((Course)o).ID);

    }

}